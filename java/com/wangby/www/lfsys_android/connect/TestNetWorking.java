package com.wangby.www.lfsys_android.connect;

import java.io.IOException;

import java.io.UnsupportedEncodingException;


public class TestNetWorking extends Thread{

	public static void main(String args[]) throws InterruptedException, IOException
	{
		NetWorking net=new NetWorking("10.10.60.58",12345);
		try {
			DataPackage data=new DataPackage(2048,"���!����");
			for(int i=0;i<3;i++)
			{
				DataPackage result=net.request(data);
				//DataPackage result=net.recive();
				System.out.println(result.getString());
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void run()
	{
		    int sum=0;
			NetWorking net = null;
			net = new NetWorking("10.10.60.58",12345);
			
			try {
				DataPackage data=new DataPackage(2048,"���!����");
				for(int i=0;i<1;i++)
				{
					DataPackage result=net.request(data);
					if(result.getString().equals(data.getString()))
						sum++;
					System.out.println(sum);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	
}
