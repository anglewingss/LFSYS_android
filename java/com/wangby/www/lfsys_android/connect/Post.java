package com.wangby.www.lfsys_android.connect;

import org.json.JSONException;
import org.json.JSONObject;


/*
 * 招领、失物信息类
 */
public class Post {
	private int type;				//失物/拾物
	private long id;				//物品编号
	private String goodsName;		//物品名称
	private int subKindID;			//子类ID
	private String subKindName;		//子类名称
	private String place;			//地点
	private long time;				//时间
	private String decp;			//特征
	private String datail;			//详细信息
	private int stuNum;				//发布者学号
	private boolean isClash;		//是否现金
	private long publishTime; 		//发布时间
	private String remark;			//备注
	private String status;			//认领状态
	private String photo;			//图片路径

	public Post(){
	}

	public Post(String jsonString){
		JSONObject json = null;
		try {
			json = new JSONObject(jsonString);

		this.type = json.getInt("type");
		this.id = json.getLong("id");
		this.goodsName = json.getString("goodsName");
		this.subKindID = json.getInt("subKindID");
		this.subKindName = json.getString("subKindName");
		this.place = json.getString("place");
		this.time = json.getLong("time");
		this.decp = json.getString("decp");
		this.datail = json.getString("datail");
		this.stuNum = json.getInt("stuNum");
		this.isClash = json.getBoolean("isClash");
		this.publishTime = json.getLong("publishTime");
		this.remark = json.getString("remark");
		this.photo = json.getString("photo");
		this.status = json.getString("status");
		} catch (JSONException e) {
		e.printStackTrace();
	}
	}

	public Post(JSONObject jsonObject){
		try {
			this.type = jsonObject.getInt("type");

		this.id = jsonObject.getLong("id");
		this.goodsName = jsonObject.getString("goodsName");
		this.subKindID = jsonObject.getInt("subKindID");
		this.subKindName = jsonObject.getString("subKindName");
		this.place = jsonObject.getString("place");
		this.time = jsonObject.getLong("time");
		this.decp = jsonObject.getString("decp");
		this.datail = jsonObject.getString("datail");
		this.stuNum = jsonObject.getInt("stuNum");
		this.isClash = jsonObject.getBoolean("isClash");
		this.publishTime = jsonObject.getLong("publishTime");
		this.remark = jsonObject.getString("remark");
		this.photo = jsonObject.getString("photo");
		this.status = jsonObject.getString("status");
		} catch (JSONException e) {
		e.printStackTrace();
	}
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		try {json.put("type", this.type);

			json.put("id", this.id);

		json.put("goodsName", this.goodsName);
		json.put("subKindID", this.subKindID);
		json.put("subKindName", this.subKindName);
		json.put("place", this.place);
		json.put("time", this.time);
		json.put("decp", this.decp);
		json.put("datail", this.datail);
		json.put("stuNum", this.stuNum);
		json.put("isClash", this.isClash);
		json.put("publishTime", this.publishTime);
		json.put("remark", this.remark);
		json.put("photo",this.photo);
		json.put("status", this.status);	} catch (JSONException e) {
		e.printStackTrace();
	}
		return json.toString();
	}


	/*
     * get/set方法
     */
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getSubKindID() {
		return subKindID;
	}
	public void setSubKindID(int subKindID) {
		this.subKindID = subKindID;
	}
	public String getSubKindName() {
		return subKindName;
	}
	public void setSubKindName(String subKindName) {
		this.subKindName = subKindName;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		if(place==null){
			this.place = "";
		}else{
			this.place = place;
		}
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		if(status==null){
			this.status = "待认领";
		}else{
			this.status = status;
		}
	}

	public String getDecp() {
		return decp;
	}
	public void setDecp(String decp) {
		if(decp==null){
			this.decp = "";
		}else{
			this.decp = decp;
		}
	}

	public String getDatail() {
		return datail;
	}
	public void setDatail(String datail) {
		if(datail==null){
			this.datail = "";
		}else{
			this.datail = datail;
		}
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public boolean isClash() {
		return isClash;
	}
	public void setClash(boolean isClash) {
		this.isClash = isClash;
	}
	public long getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		if(remark==null){
			this.remark = "";
		}else{
			this.remark = remark;
		}
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		if(photo==null){
			this.photo = "";
		}else{
			this.photo = photo;
		}
	}

	{
		this.id = 0;
		this.goodsName = "";
		this.subKindName = "";
		this.place = "";
		this.decp = "";
		this.datail = "";
		this.isClash = false;
		this.remark = "";
		this.photo = "";
		this.status = "";
	}
}
