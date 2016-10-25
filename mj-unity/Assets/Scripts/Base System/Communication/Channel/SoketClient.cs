#define	USE_THREAD_RECEIVE_MSG
using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Net;
using System.IO;
using System.Threading;
using UnityEngine;
using ProtoBuf;

#if UNITY_EDITOR
using UnityEditor;
#endif

namespace Communication
{
	class ByteBuffer
	{
		private MemoryStream leftRecvMsgBuf;
		
		public ByteBuffer (int maxSize)
		{
			this.leftRecvMsgBuf = new MemoryStream (maxSize);
		}
		
		public void Put (byte[] data, int length)
		{
			leftRecvMsgBuf.Write (data, 0, length);
		}
		
		public void Put (byte[] data)
		{
			leftRecvMsgBuf.Write (data, 0, data.Length);
		}
		
		public void Get (byte[] data)
		{
			leftRecvMsgBuf.Read (data, 0, data.Length);
		}

		public int GetInt()
		{
			int result = 0;
			result |= ((byte) leftRecvMsgBuf.ReadByte()) << 24;
			result |= ((byte)leftRecvMsgBuf.ReadByte()) << 16;
			result |= ((byte)leftRecvMsgBuf.ReadByte()) << 8;
			result |= (byte)leftRecvMsgBuf.ReadByte();
			return result;
		}

		public byte[] GetBuffer()
		{
			return leftRecvMsgBuf.GetBuffer();
		}
		
		public void Flip ()
		{
			this.leftRecvMsgBuf.Seek (0, SeekOrigin.Begin);
		}
		
		public void Compact ()
		{
			if (leftRecvMsgBuf.Position == 0) {
				return;
			}
			
			long _remaining = this.Remaining ();
			if (_remaining <= 0) {
				this.Clear ();
				return;
			}
			
			byte[] _leftData = new byte[_remaining];
			this.Get (_leftData);
			this.Clear ();
			this.Put (_leftData);            
		}
		
		public void Clear ()
		{
			leftRecvMsgBuf.Seek (0, SeekOrigin.Begin);
			leftRecvMsgBuf.SetLength (0);
		}
		
		public long Remaining ()
		{
			return leftRecvMsgBuf.Length - leftRecvMsgBuf.Position;
		}
		
		public Boolean HasRemaining ()
		{
			return leftRecvMsgBuf.Length > leftRecvMsgBuf.Position;
		}
		
		public long Position ()
		{
			return leftRecvMsgBuf.Position;
		}
		
		public long Length ()
		{
			return leftRecvMsgBuf.Length;
		}
		
		public void SetPosition (long position)
		{
			leftRecvMsgBuf.Seek (position, SeekOrigin.Begin);
		}

		public void Jump (long offset)
		{
			leftRecvMsgBuf.Seek (leftRecvMsgBuf.Position + offset, SeekOrigin.Begin);
		}
	}
	
	public enum EClientConnectState
	{
		CONNECT_STATE_NONE,
		CONNECT_STATE_FAILED,
		
		CONNECT_STATE_CAN_RECONNECT,
		
		CONNECT_STATE_TRY_CONNECT,
		CONNECT_STATE_CONNECTED,
		CONNECT_STATE_DO_TRY_CONNECT
	}
	
/**
     *
     */
	struct IPaddressWrapper
	{
		public IPEndPoint ipPoint;
		public bool isTried;
	};
	
	/**
     *
     */
	class MsgReceiveHelper
	{
		public Socket socket = null;
		public byte[] buffer = null;
	}

	public interface IDataDecoder
	{
		object Decode(byte[] bytes, int offset, int length);
	}
	
	/**
     *  socket Client
     */
	public class SocketClient : IChannel
	{
		private const int MAX_MSG_PER_LOOP = 16;

		public volatile EClientConnectState ConnectState = EClientConnectState.CONNECT_STATE_NONE;
				
		/** */
		private IPaddressWrapper[] _ipAddressArry;
		/** socket Client */
		private Socket _socketClient;
		/**  */
		private Queue<object> _recMsgs;
		private Queue<object> _msgCopy;
		private ByteBuffer _recMsgBuf;
		private IDataDecoder _decoder;

		/**  */
		const int DEFAULT_RECEIVE_SIZE = 64 * 1024;
		const int DEFAULT_SEND_SIZE = 32 * 1024;

		//
		#if USE_THREAD_RECEIVE_MSG
		Thread	mRecvThread = null;
		bool	mThreadWork = false;
		#endif
		//
		bool	m_bSecurityPolicy = false;
		//
		static	private	object		_ErrorLock = new object ();
		static	private	int			_ShowErrorIndex = 0;
		static	private	int			_ErrorCode = 0;
		static	private	SocketError	_SocketError;

		public SocketClient (String serverIp, String serverPorts, IDataDecoder decoder)
		{
			_socketClient = new Socket (AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
			// Set to no blocking
			_socketClient.Blocking = false;
			_socketClient.ReceiveBufferSize = DEFAULT_RECEIVE_SIZE;
			_socketClient.SendBufferSize = DEFAULT_SEND_SIZE;
			_socketClient.ReceiveTimeout = 30000;
			_socketClient.SendTimeout = 30000;
			_recMsgs = new Queue<object> ();
			_msgCopy = new Queue<object> ();
			_decoder = decoder;

			this.InitIpAddressArry (serverIp, serverPorts);

			_recMsgBuf = new ByteBuffer (MessageConfig.BUFFER_SIZE);
			
			// Unity Network Security
			m_bSecurityPolicy = false;

		}
		
		public void Close ()
		{
			if (_socketClient == null) {
				return;
			}
			
			#if USE_THREAD_RECEIVE_MSG
			mThreadWork = false;
			#endif
			
			_socketClient.Close ();
		}

		//
		public void DoRetryConnect ()
		{
			ConnectState = EClientConnectState.CONNECT_STATE_DO_TRY_CONNECT;
		}
		
		/**
         *
         */
		private IPEndPoint GetServerAddress ()
		{
			for (int i = 0; i < _ipAddressArry.Length; i++) {
				IPaddressWrapper _wrapper = _ipAddressArry [i];
				//
				if (_wrapper.isTried) {
					continue;
				}
				_wrapper.isTried = true;
				// FIXME why
				_ipAddressArry [i] = _wrapper;
				Console.WriteLine ("Try to connect : " + _wrapper.ipPoint);
				return _wrapper.ipPoint;
			}
			
			return null;
		}
		
		/**
         *
         */
		public void ResetServerAddressStatus ()
		{
			for (int i = 0; i < _ipAddressArry.Length; i++) {
				IPaddressWrapper _wrapper = _ipAddressArry [i];
				_wrapper.isTried = false;
				_ipAddressArry [i] = _wrapper;
			}
			ConnectState = EClientConnectState.CONNECT_STATE_NONE;
		}
		
		/**
         * 
         */
		private void InitIpAddressArry (String serverIp, String ports)
		{
			IPAddress _ipAddress = IPAddress.Parse (serverIp);
			string[] _tempArray = ports.Split (',');
			int _portSize = _tempArray.Length;
			_ipAddressArry = new IPaddressWrapper[_portSize];
			for (int i = 0; i < _portSize; i++) {
				int _port = Convert.ToInt32 (_tempArray [i].Trim ());
				IPaddressWrapper _addressWrapper = new IPaddressWrapper ();
				_addressWrapper.ipPoint = new IPEndPoint (_ipAddress, _port);
				_addressWrapper.isTried = false;
				_ipAddressArry [i] = _addressWrapper;
			}
		}


		/// <summary>
		/// Tries the connect.
		/// </summary>
		/// <exception cref='Exception'>
		/// Represents errors that occur during application execution.
		/// </exception>
		public void TryConnect ()
		{
			try {
				IPEndPoint _ep = null;
				
				//
				if (m_bSecurityPolicy) {

				} else {
					_ep = this.GetServerAddress ();
					if (null == _ep) {
						ConnectState = EClientConnectState.CONNECT_STATE_FAILED;
						ClientLog.LogError ("Connect timeout : no valid server ip or port");
						return;
					}
				}

				ConnectState = EClientConnectState.CONNECT_STATE_TRY_CONNECT;
				_socketClient.BeginConnect (_ep, new AsyncCallback (ConnectCallback), _socketClient);
				ClientLog.Log ("Connect server : " + _ep.Address.ToString () + ":" + _ep.Port.ToString ());
			} catch (Exception ex)
			{
				ClientConnectState = EClientConnectState.CONNECT_STATE_FAILED;
				ClientLog.LogError (ex.ToString ());
			}
		}

		
		/**
         *
         */
		private void ConnectCallback (IAsyncResult ar)
		{
			try {
				Socket _socket = (Socket)ar.AsyncState;
				if (!_socket.Connected)
				{
					ConnectState = EClientConnectState.CONNECT_STATE_FAILED;
					ClientLog.LogError (_socket.LocalEndPoint + " connect failed!, try connect again");
//					this.DoRetryConnect ();
				} else {
					_socket.EndConnect (ar);
					this.StartRecevieMsg ();
					ConnectState = EClientConnectState.CONNECT_STATE_CONNECTED;
					ClientLog.Log(_socket.LocalEndPoint + " connect successful!");
				}
			} catch (Exception e) {
				
				ConnectState = EClientConnectState.CONNECT_STATE_FAILED;
				// do something
				Console.WriteLine (e.ToString ());
			}
			
		}
		
		/**
         *
         */
		public bool IsConnected ()
		{
			return (null != this._socketClient && this._socketClient.Connected);
		}
		
		// login ui can retry ?
		public bool CanTryConnect ()
		{
			return (!IsConnected () && ConnectState < EClientConnectState.CONNECT_STATE_CAN_RECONNECT);
		}
		
		/**
         *
         */
		public void SendMessage (byte[] bytes)
		{
//			ClientLog.Log("send msg");
			try {
				if (!this.IsConnected ()) {
					ClientLog.LogError ("server is not connected!");
				} else {
					_socketClient.BeginSend (bytes, 0, bytes.Length, SocketFlags.None, SendMsgCallback, _socketClient);
				}
			} catch (Exception ex) {
				ClientLog.LogError (ex.ToString ());
			}
		}
		
		//
		private void StartRecevieMsg ()
		{			
			#if USE_THREAD_RECEIVE_MSG
			if (null == mRecvThread)
				mRecvThread = new Thread (RecvThreadDoWork);
			//
			if (null != mRecvThread) {
				mThreadWork = true;
				mRecvThread.Start ();
			}
			#else
			MsgReceiveHelper receiveHelper = new MsgReceiveHelper ();
			receiveHelper.socket = this.socketClient;
			receiveHelper.buffer = new byte[DEFAULT_RECEIVE_SIZE];
			
			this.socketClient.BeginReceive (receiveHelper.buffer, 0, receiveHelper.buffer.Length, SocketFlags.None, new AsyncCallback (ReceiveMsgCallback), receiveHelper);
			#endif
		}
		
		//
		#if USE_THREAD_RECEIVE_MSG
		void	RecvThreadDoWork ()
		{
			byte[] _buffer = new byte[DEFAULT_RECEIVE_SIZE];
			while (mThreadWork) {
				try {
					int _recSize = this._socketClient.Receive (_buffer, DEFAULT_RECEIVE_SIZE, SocketFlags.None);
					if (_recSize > 0) {
						this.DecodeMsg (_buffer, _recSize);
					}
					// < 0, the remote socket is close...
					else {
						SetShowNetworkError (1, 0, SocketError.SocketError);					
						ClientLog.LogError ("Socket EndReceive failed, the size is 0. The remote socket is closed. Disconnect...");
						this.Close ();
						break;
					}
				} catch (SocketException se) {
					if (se.SocketErrorCode == SocketError.WouldBlock ||
						se.SocketErrorCode == SocketError.IOPending ||
						se.SocketErrorCode == SocketError.NoBufferSpaceAvailable) {
						// socket buffer is probably empty, wait and try again
						Thread.Sleep (50);
					} else {
						SetShowNetworkError (2, se.ErrorCode, se.SocketErrorCode);							
						ClientLog.LogError ("receive msg failed : " + se.ToString ());
						ClientLog.LogError ("Socket EndReceive Exception, ErrorCode = " + se.ErrorCode.ToString () + ", SocketErrorCode = " + se.SocketErrorCode.ToString ());
						ClientLog.LogError ("Socket fatal exception, disconnect...");
						this.Close ();
						break;
					}
				}
				
				Thread.Sleep (1);
			}
			
			//
			mRecvThread.Join ();
		}
		#endif
		
		/**
         *
         */
		private void ReceiveMsgCallback (IAsyncResult receiveRes)
		{
			if (!IsConnected ()) {
				ClientLog.LogError ("ReceiveMsgCallback : the socket is not connected!!!");
				return;
			}
			
			MsgReceiveHelper receiveHelper = null;
			try {
				int _recSize = this._socketClient.EndReceive (receiveRes);
				if (_recSize > 0) {
					receiveHelper = (MsgReceiveHelper)receiveRes.AsyncState;
					this.DecodeMsg (receiveHelper.buffer, _recSize);
				}
				// < 0, the remote socket is close...
				else {
					SetShowNetworkError (1, 0, SocketError.SocketError);					
					ClientLog.LogError ("Socket EndReceive failed, the size is 0. The remote socket is closed. Disconnect...");
					this.Close ();
					return;
				}
			}
			//
			catch (SocketException se) {
				SetShowNetworkError (2, se.ErrorCode, se.SocketErrorCode);				
				ClientLog.LogError ("receive msg failed : " + se.ToString ());
				ClientLog.LogError ("Socket EndReceive Exception, ErrorCode = " + se.ErrorCode.ToString () + ", SocketErrorCode = " + se.SocketErrorCode.ToString ());
				
				// Disconnect, WSAEWOULDBLOCK
				if (!se.SocketErrorCode.Equals (SocketError.WouldBlock)) {					
					ClientLog.LogError ("Socket fatal exception, disconnect...");
					this.Close ();
					return;
				}
			} catch (Exception e) {
				SetShowNetworkError (3, 0, SocketError.SocketError);				
				ClientLog.LogError ("receive msg failed : " + e.ToString ());
			}
			//
			finally {
				if (receiveHelper != null) {
					receiveHelper.socket.BeginReceive (receiveHelper.buffer, 0, receiveHelper.buffer.Length, SocketFlags.None, new AsyncCallback (ReceiveMsgCallback), receiveHelper);
				} else {
					this.StartRecevieMsg ();
				}
			}
		}
		
		/**
         *
         */
		private void SendMsgCallback (IAsyncResult sendRes)
		{
//			ClientLog.Log("send Message callback");
			try {
				Socket _socket = (Socket)sendRes.AsyncState;
				int nSentByte = _socket.EndSend (sendRes);
				
				if (nSentByte <= 0) {
					SetShowNetworkError (4, 0, SocketError.SocketError);				
					ClientLog.LogError ("send msg failed!");
				}
			} catch (Exception e) {
				ClientLog.LogError ("send msg failed : " + e.ToString ());
				//
				SocketException se = e as SocketException;
				if (null != se) {
					SetShowNetworkError (5, se.ErrorCode, se.SocketErrorCode);					
					ClientLog.LogError ("Socket EndSend Exception, ErrorCode = " + se.ErrorCode.ToString () + ", SocketErrorCode = " + se.SocketErrorCode.ToString ());
				}
			}
		}
		
		private byte[] encrytData (byte[] sendData)
		{
			return sendData;
		}
		
		private byte[] decrytData (byte[] receiveData)
		{
			return receiveData;
		}

		private void DecodeMsg (byte[] receiveBytes, int size)
		{
			_recMsgBuf.SetPosition (_recMsgBuf.Length ());
			_recMsgBuf.Put (receiveBytes, size);
			_recMsgBuf.Flip ();

			int minCheckLength = MessageConfig.BODY_SIZE_LENGTH;

			while (_recMsgBuf.Remaining() >= minCheckLength)
			{
				int bodyLen;
				Serializer.TryReadLengthPrefix (_recMsgBuf.GetBuffer(), 0, _recMsgBuf.GetBuffer().Length, PrefixStyle.Base128, out bodyLen);

				_recMsgBuf.SetPosition(minCheckLength - MessageConfig.BODY_SIZE_LENGTH);

				//int bodyLen = _recMsgBuf.GetInt();
				if (_recMsgBuf.Remaining() >= bodyLen)
				{
					_recMsgBuf.Flip ();

					long position = _recMsgBuf.Position();
					int dataLen = minCheckLength + bodyLen;
					object data = _decoder.Decode(_recMsgBuf.GetBuffer(), (int)position, dataLen);
//					byte[] data = new byte[minCheckLength + bodyLen];
//					_recMsgBuf.Get(data);
					_recMsgs.Enqueue(data);

					_recMsgBuf.SetPosition(position + dataLen);
					_recMsgBuf.Compact ();
					_recMsgBuf.Flip();
				}
				else
				{
					break ;
				}
			}
		}
        
		//		//
		public void HandleReceiveMsgs ()
		{
			lock (this._recMsgs) {
				int iMsgCount = Math.Min (this._recMsgs.Count, MAX_MSG_PER_LOOP);
				for (int iLoop = 0; iLoop < iMsgCount; ++iLoop) {
					_msgCopy.Enqueue (_recMsgs.Dequeue());
				}
			}
		}
		
		//
		public int GetHandleMsgCount ()
		{
			return _msgCopy.Count;
		}
		
		//
		public object PopHandleMsg ()
		{
			if (_msgCopy.Count > 0) 
			{
				return _msgCopy.Dequeue();
			}
			
			return null;
		}  
		
		//
		public EClientConnectState ClientConnectState {
			get{ return ConnectState; }
			set {
				ConnectState = value;
			}
		}

		static	private	void SetShowNetworkError (int iIndex, int iError, SocketError socketErr)
		{
			lock (_ErrorLock) {
				_ShowErrorIndex = iIndex;
				_ErrorCode = iError;
				_SocketError = socketErr;
			}
		}

		static public string GetNetworkErrorString()
		{
			return string.Format("ErrorIndex:{0}, errorCode:{1}, socketError:{2}", _ShowErrorIndex, _ErrorCode, _SocketError);
		}
	}
}

