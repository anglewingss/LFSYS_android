package com.wangby.www.lfsys_android.connect;

import java.io.UnsupportedEncodingException;




public class DataPackage {

	private int port;
	private byte[] data;
	
	public DataPackage(int port, byte[] data) {
		this.port=port;
		this.data =data;
	}
	
	public DataPackage(int port,String data) throws UnsupportedEncodingException {
		this.port=port;
		writeString(data);
	}
	
	public String getString() throws UnsupportedEncodingException
	{
	    String str=new String(data,"UTF-8");
	    return str;
	}
	
	public void writeString(String data) throws UnsupportedEncodingException
	{
		this.data=data.getBytes("UTF-8");
	}
	
	public void setData(byte[] data)
	{
		this.data=data;
	}
	
	public byte[] getData()
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
		return data.length;
	}

}
