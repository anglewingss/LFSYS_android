package com.wangby.www.lfsys_android.connect;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * �û���
 */
public class User {

    private int stuNum;                //ѧ��
    private String password;        //����
    private String name;            //����
    private String phone;            //�绰
    private int oredit;                //����

    public User() {
    }

    public User(String jsonString) {
        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
            this.stuNum = json.getInt("stuNum");
            this.password = json.getString("password");
            this.name = json.getString("name");
            this.phone = json.getString("phone");
            this.oredit = json.getInt("oredit");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public User(JSONObject jsonObject) {
        try {
            this.stuNum = jsonObject.getInt("stuNum");
            this.password = jsonObject.getString("password");
            this.name = jsonObject.getString("name");
            this.phone = jsonObject.getString("phone");
            this.oredit = jsonObject.getInt("oredit");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("stuNum", this.stuNum);
            json.put("password", this.password);
            json.put("name", this.name);
            json.put("phone", this.phone);
            json.put("oredit", this.oredit);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }


    /*
     * get/set����
     */
    public int getStuNum() {
        return stuNum;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getOredit() {
        return oredit;
    }

    public void setOredit(int oredit) {
        this.oredit = oredit;
    }

    {
        name = "";            //����
        phone = "";            //�绰
        oredit = -1;        //����
    }
}
