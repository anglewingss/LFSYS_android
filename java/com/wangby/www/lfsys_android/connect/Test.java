package com.wangby.www.lfsys_android.connect;

public class Test {
      public static void main(String args[])
      {
    	  for(int i=0;i<10000;i++)
    	  {
    		  TestNetWorking net=new TestNetWorking();
    		  net.start();
    	  }
      }
}
