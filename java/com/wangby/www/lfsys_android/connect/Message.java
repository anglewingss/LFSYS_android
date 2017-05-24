package com.wangby.www.lfsys_android.connect;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {
	private int type;
	private String data;
	
	public Message(int type,String data){
		this.type = type;
		this.data = data;
	}
	
	public int getType() {
		return type;
	}

	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		try {
			json.put("type", this.type);

		json.put("data", this.data);
	} catch (JSONException e) {
		e.printStackTrace();
	}
		return json.toString();
	}
}
