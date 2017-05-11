package com.wangby.www.lfsys_android.connect;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * ������
 */
public class Argument {
	
	private long argumentID;		//���߱��
	private int stuNum;				//������ѧ��
	private String reason;			//����ԭ��
	private long goodsID;			//��Ʒ���
	private String gooodsName;		//��Ʒ����
	private long time;				//����ʱ��
	private String arguState;		//����״̬
	
	public Argument() {
	}

	public Argument(String jsonString) {
		JSONObject json = null;
		try {
			json = new JSONObject(jsonString);
			this.argumentID = json.getLong("argumentID");
			this.stuNum = json.getInt("stuNum");
			this.reason = json.getString("reason");
			this.goodsID = json.getLong("goodsID");
			this.gooodsName = json.getString("gooodsName");
			this.time = json.getLong("time");
			this.arguState = json.getString("arguState");



		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	public Argument(JSONObject jsonObject) {

		try {

			argumentID = jsonObject.getLong("argumentID");
			this.stuNum = jsonObject.getInt("stuNum");
			this.reason = jsonObject.getString("reason");
			this.goodsID = jsonObject.getLong("goodsID");
			this.gooodsName = jsonObject.getString("gooodsName");
			this.time = jsonObject.getLong("time");
			this.arguState = jsonObject.getString("arguState");


		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();

		try {
			json.put("argumentID", this.argumentID);
			json.put("stuNum", this.stuNum);
			json.put("reason", this.reason);
			json.put("goodsID", this.goodsID);
			json.put("gooodsName", this.gooodsName);
			json.put("time", this.time);
			json.put("arguState", this.arguState);






		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json.toString();
	}
	
	

/*
 * get/set����
 */	
	public long getArgumentID() {
		return argumentID;
	}
	public void setArgumentID(long argumentID) {
		this.argumentID = argumentID;
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public long getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(long goodsID) {
		this.goodsID = goodsID;
	}
	public String getGooodsName() {
		return gooodsName;
	}
	public void setGooodsName(String gooodsName) {
		this.gooodsName = gooodsName;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getArguState() {
		return arguState;
	}
	public void setArguState(String arguState) {
		this.arguState = arguState;
	}
	
	{
		this.argumentID = 0;		//���߱��
		this.stuNum = 0;			//������ѧ��
		this.reason = "";			//����ԭ��
		this.goodsID = 0;			//��Ʒ���
		this.gooodsName = "";		//��Ʒ����
		this.time = 0;				//����ʱ��
		this.arguState = "未处理";	//����״̬
	}
	
}
