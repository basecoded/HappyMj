using System.Collections;
using Communication;
using Message;
using System;
using System.IO;
using ProtoBuf;

public class CommunicationCenter
{

    public static string SERVER_ADDRESS = "192.168.1.120";

    public static string SERVER_PORT = "10001";

    private SocketClient socket;
    private CommunicationManager communicationManager;
    private EClientConnectState connectState = EClientConnectState.CONNECT_STATE_NONE;

    public void TryConnect()
    {
        if (socket == null)
        {
            socket = new SocketClient(SERVER_ADDRESS, SERVER_PORT, new ProtobufDecoder(), new ProtobufEncoder());
            connectState = socket.ConnectState;

            socket.TryConnect();
        }
    }

    public void SendMessage(Request request)
    {
        socket.SendMessage(request);
    }

}
