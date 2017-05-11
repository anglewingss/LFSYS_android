package com.wangby.www.lfsys_android.connect;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NetWorking {

    private SocketChannel channel;
    private SocketAddress socketAddress;
    
    public NetWorking(String serverAddress,int port)
    {
    	socketAddress=new InetSocketAddress(serverAddress,port);
    	try {
			channel=SocketChannel.open();
			channel.configureBlocking(true);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }
    
    public DataPackage request(DataPackage dataPackage) throws IOException
    {
		ByteBuffer headerBuffer=ByteBufferTools.getNewByteBuffer(4);
		ByteBuffer portBuffer=ByteBufferTools.getNewByteBuffer(4);
		headerBuffer.putInt(dataPackage.getDataSize());
		headerBuffer.flip();
        portBuffer.putInt(dataPackage.getPort());
        portBuffer.flip();
        ByteBuffer data=dataPackage.getData();
        data.rewind();
        if(!channel.isConnected())
        	channel.connect(socketAddress);
        int num=channel.write(headerBuffer);
        //System.out.println(channel.getLocalAddress()+" "+num);
        num=channel.write(portBuffer);
        //System.out.println(channel.getLocalAddress()+" "+num);
        num=channel.write(data);
        //System.out.println(channel.getLocalAddress()+" "+num);
        headerBuffer.clear();
        portBuffer.clear();
        num=channel.read(headerBuffer);
        //System.out.println(channel.getLocalAddress()+"read"+num);
        headerBuffer.flip();
        int dataSize=headerBuffer.getInt();
        ByteBuffer dataBuffer=ByteBufferTools.getNewByteBuffer(dataSize);
        num=channel.read(portBuffer);
        //System.out.println(channel.getLocalAddress()+"read"+num);
        portBuffer.flip();
        num=channel.read(dataBuffer);
        //System.out.println(channel.getLocalAddress()+"read"+num);
        dataPackage=new DataPackage(portBuffer.getInt(),dataBuffer);
        channel.finishConnect();
    	return dataPackage;	
    }
    
}
