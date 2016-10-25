using System.Collections;
using Communication;
using Message;

public class CommunicationCenter  {

	public static string SERVER_ADDRESS = "192.168.1.120";

	public static string SERVER_PORT = "10001";

	private SocketClient socket;
	private CommunicationManager communicationManager;
	private EClientConnectState connectState = EClientConnectState.CONNECT_STATE_NONE;

	public void TryConnect() 
	{
		if (socket == null) 
		{
			socket = new SocketClient (SERVER_ADDRESS, SERVER_PORT, new ProtobufDecoder());
			connectState = socket.ConnectState;

			socket.TryConnect ();
		}
	}

	public void SendMessage(byte[] bytes) 
	{
		socket.SendMessage (bytes);
	}
}
