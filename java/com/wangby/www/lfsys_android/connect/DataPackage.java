package com.wangby.www.lfsys_android.connect;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;



public class DataPackage {

	private int port;
	private ByteBuffer data;
	
	public DataPackage(int port, ByteBuffer data) {
		this.port=port;
		this.data =data;
	}
	
	public DataPackage(int port,String data) throws UnsupportedEncodingException {
		this.port=port;
		writeString(data);
	}
	
	public String getString() throws UnsupportedEncodingException
	{
	    String str=new String(data.array(),"UTF-8");
	    return str;
	}
	
	public void writeString(String data) throws UnsupportedEncodingException
	{
		byte[] temp=data.getBytes("UTF-8");
		ByteBuffer newBuffer=ByteBufferTools.toByteBuffer(temp);
		this.data=newBuffer;
	}
	
	public void setData(ByteBuffer data)
	{
		this.data=data;
	}
	
	public ByteBuffer getData()
	{
		return data;
	}
	
	public int getPort()
	{
		return port;
	}
	
	public void setPort(int port)
	{
		this.port=port;
	}
	
	public int getDataSize()
	{
		return data.limit();
	}

}
