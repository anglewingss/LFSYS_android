package com.wangby.www.lfsys_android.Tool;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
	private static SimpleDateFormat hasYearDate=new SimpleDateFormat("yyyy年MM月dd日");
	private static SimpleDateFormat date=new SimpleDateFormat("MM月dd日");
	private static SimpleDateFormat moment=new SimpleDateFormat("hh:mm");
	private static String wee="凌晨";
	private static String morning="早晨";
	private static String forenoon="上午";
	private static String noon="中午";
	private static String afternoon="下午";
	private static String nightfall="傍晚";
	private static String evening="晚上";
	private static String midnight="深夜";
	private static long dayLength=3600*1000*24;
	private static long hourLength=3600*1000;
	private static String today="";
	private static String yesterday="昨天";
	private static String dayAfterYesterday="前天";


	public static String getQuantum(long timeStamp)
	{
		int hour=getHour(timeStamp);
		if(hour>=2&&hour<5)
			return wee;
		else if(hour<8)
			return morning;
		else if(hour<12)
			return forenoon;
		else if(hour<14)
			return noon;
		else if(hour<17)
			return afternoon;
		else if(hour<19)
			return nightfall;
		else if(hour<22)
			return evening;
		else
			return midnight;
	}

	public static int getHour(long timeStamp)
	{
		return ((int) (timeStamp%dayLength/hourLength)+8)%24;
	}

	public static int getDay(long timeStamp)
	{
		return (int) (timeStamp/dayLength);
	}

	public static String getDate(long timeStamp)
	{
		long present=new Date().getTime();
		int presentDay=getDay(present);
		int day=getDay(timeStamp);
		if(day-presentDay==0)
			return today;
		else if(day-presentDay==1)
			return yesterday;
		else if(day-presentDay==2)
			return dayAfterYesterday;
		else
			return hasYearDate.format(timeStamp);
	}

	public static String format(long timeStamp)
	{
		return getDate(timeStamp);
	}

	private static String getMoment(long timeStamp) {
		// TODO Auto-generated method stub
		return moment.format(new Date(timeStamp));
	}

	public static void main(String args[])
	{
		System.out.println(format(System.currentTimeMillis()));
	}

}
