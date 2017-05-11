package com.wangby.www.lfsys_android.connect;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferTools {
	
	public static  ByteBuffer getNewByteBuffer(int size)
	{
		ByteBuffer buffer=ByteBuffer.allocate(size);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		return buffer;
	}
	
	public static byte[] toByteArray(ByteBuffer buffer)
	{
		byte[] array=new byte[buffer.limit()];
		buffer.rewind();
		buffer.get(array);
		return array;
	}
	
	public static ByteBuffer toByteBuffer(byte[] array)
	{
		ByteBuffer buffer=getNewByteBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
}
