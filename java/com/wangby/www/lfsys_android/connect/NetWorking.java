package com.wangby.www.lfsys_android.connect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;


public class NetWorking {

    private Socket socket;
    private SocketAddress socketAddress;
    
    public NetWorking(String serverAddress,int port) 
    {
    	socketAddress=new InetSocketAddress(serverAddress,port);
    	try {
			socket=createSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }
    
    private Socket createSocket() throws SocketException  {
		Socket socket=new Socket();
		socket.setSoTimeout(15000);
		return socket;
	}

	public DataPackage request(DataPackage dataPackage) throws IOException
    {
    	DataPackage recvPackage=null;
    	try{
    	    connectServer();
    	    send(dataPackage);
    	    recvPackage=receive();
    	}catch(IOException e)
    	{
    		disconnect();
    		throw e;
    	}
    	disconnect();
    	return recvPackage;
    }

	private void disconnect() {
		if(!socket.isClosed())
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private DataPackage receive() throws IOException {
		int header=0;
		int port=0;
		byte[] data=null;
		DataInputStream inputStream=new DataInputStream(socket.getInputStream());
		header=inputStream.readInt();
		port=inputStream.readInt();
		data=new byte[header];
		inputStream.read(data,0,header);
		return new DataPackage(port,data);
	}

	private void send(DataPackage dataPackage) throws IOException {
		DataOutputStream outputStream=new DataOutputStream(socket.getOutputStream());
		outputStream.writeInt(dataPackage.getDataSize());
		outputStream.writeInt(dataPackage.getPort());
		outputStream.write(dataPackage.getData(),0,dataPackage.getDataSize());
	}

	private void connectServer() throws IOException  {
		if(socket.isClosed())
			socket=createSocket();
		if(!socket.isConnected())
			socket.connect(socketAddress);
	}
    
}
