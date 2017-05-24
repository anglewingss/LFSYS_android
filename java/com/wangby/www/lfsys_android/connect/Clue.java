package com.wangby.www.lfsys_android.connect;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * ������
 */
public class Clue {
	private long clueID;				//�������
	private long goodsID;				//��Ʒ���
	private String goodsName;			//��Ʒ����
	private int providerID;				//�ṩ��ѧ��
	private String content;				//��������
	private long time;					//�����ṩʱ��
	
	public Clue(){
	}
	
	public Clue(String jsonString){

		JSONObject json = null;
		try {
			json = new JSONObject(jsonString);
		this.clueID = json.getLong("clueID");
		this.goodsID = json.getLong("goodsID");
		this.goodsName = json.getString("goodsName");
		this.providerID = json.getInt("providerID");
		this.content = json.getString("content");
		this.time = json.getLong("time");

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public Clue(JSONObject jsonObject){
		try {
			this.clueID = jsonObject.getLong("clueID");

		this.goodsID = jsonObject.getLong("goodsID");
		this.goodsName = jsonObject.getString("goodsName");
		this.providerID = jsonObject.getInt("providerID");
		this.content = jsonObject.getString(content);
		this.time = jsonObject.getLong("time");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString(){
		JSONObject json = new JSONObject();
		try {
			json.put("clueID", this.clueID);

		json.put("goodsID", this.goodsID);
		json.put("goodsName", this.goodsName);
		json.put("providerID", this.providerID);
		json.put("content", this.content);
		json.put("time", this.time);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	
	
		
	
/*
 * get/set����
 */
	public long getClueID() {
		return clueID;
	}
	public void setClueID(long clueID) {
		this.clueID = clueID;
	}
	public long getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(long goodsID) {
		this.goodsID = goodsID;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getProviderID() {
		return providerID;
	}
	public void setProviderID(int providerID) {
		this.providerID = providerID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	{
		clueID = 0;				
		goodsID = 0;			
		goodsName = "";			
		providerID=0;				
		content = "";				
		time = 0;	
	}
	
}
