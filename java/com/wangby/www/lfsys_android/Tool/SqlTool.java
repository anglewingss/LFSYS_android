package com.wangby.www.lfsys_android.Tool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wangby.www.lfsys_android.Object.Goods;
import com.wangby.www.lfsys_android.connect.Post;
import com.wangby.www.lfsys_android.connect.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王炳炎 on 2017/3/24.
 */

public class SqlTool {
    private SqlMode sqlMode;


    public SqlTool(Context context){
        sqlMode = new SqlMode(context);
    }

    public void delect(String object){
        SQLiteDatabase db = sqlMode.getReadableDatabase();
        db.delete(object,null,null);
        db.close();
    }

    public void saveUser(User user){
        SQLiteDatabase db = sqlMode.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("stuNum",user.getStuNum());
        values.put("password",user.getPassword());
        values.put("name",user.getName());
        values.put("phone",user.getPhone());
        values.put("oredit",user.getOredit());
        db.insert("user",null,values);
        db.close();
    }

    public void saveGoods( List<Post> list, String type){
        SQLiteDatabase db = sqlMode.getReadableDatabase();
//        db.delete(type,null,null);
        for (Post c : list) {
            ContentValues values = new ContentValues();
            values.put("type",c.getType());
            values.put("id",c.getId());
            values.put("goodsName", c.getGoodsName());
            values.put("subKindID", c.getSubKindID());
            values.put("subKindName", c.getSubKindName());
            values.put("place", c.getPlace());
            values.put("time", c.getTime());
            values.put("decp", c.getDecp());
            values.put("datail", c.getDatail());
            values.put("stuNum", c.getStuNum());
            if(c.isClash())
                values.put("isClash", 1);
            else
                values.put("isClash", 0);
            values.put("publishTime", c.getPublishTime());
            values.put("remark", c.getRemark());
            values.put("photo", c.getPhoto());
            db.insert(type, null, values);
        }
        db.close();
    }

    public User getUser() {
        String sql = "select * from user";
        User user = new User();
        SQLiteDatabase db = sqlMode.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                user.setStuNum(c.getInt(0));
                user.setPassword(c.getString(1));
                user.setName(c.getString(2));
                user.setPhone(c.getString(3));
                user.setOredit(c.getInt(4));
            }
            db.close();
            c.close();
            return user;
        }
        db.close();
        c.close();
        return null;
    }


    public List<Post> searchGood(String key){
        String sql = "select * from lost,found where goodsName='"+key+"'";
        List<Post> list = new ArrayList<Post>();
        SQLiteDatabase db = sqlMode.getReadableDatabase();
        int a =10;
        long b =a;
        Cursor c = db.rawQuery(sql,null);
        if(c != null && c.getCount()>0){
            while(c.moveToNext()){
                Post d = new Post();
                d.setType(c.getInt(0));
                d.setId(c.getInt(1));
                d.setGoodsName(c.getString(2));
                d.setSubKindID(c.getInt(3));
                d.setSubKindName(c.getString(4));
                d.setPlace(c.getString(5));
                d.setTime(c.getInt(6));
                d.setDecp(c.getString(7));
                d.setDatail(c.getString(8));
                d.setStuNum(c.getInt(9));
                d.setClash(c.getInt(10)==1);
                d.setPublishTime(c.getInt(11));
                d.setRemark(c.getString(12));
                d.setPhoto(c.getString(13));
                list.add(d);
            }
            db.close();
            c.close();
            return list;
        }
        db.close();
        c.close();
        return null;
    }

    public List<Post> getGood(String type){
        String sql = "select * from "+type;
        List<Post> list = new ArrayList<Post>();
        SQLiteDatabase db = sqlMode.getReadableDatabase();
        int a =10;
        long b =a;
        Cursor c = db.rawQuery(sql,null);
        if(c != null && c.getCount()>0){
            while(c.moveToNext()){
                Post d = new Post();
                d.setType(c.getInt(0));
                d.setId(c.getInt(1));
                d.setGoodsName(c.getString(2));
                d.setSubKindID(c.getInt(3));
                d.setSubKindName(c.getString(4));
                d.setPlace(c.getString(5));
                d.setTime(c.getInt(6));
                d.setDecp(c.getString(7));
                d.setDatail(c.getString(8));
                d.setStuNum(c.getInt(9));
                d.setClash(c.getInt(10)==1);
                d.setPublishTime(c.getInt(11));
                d.setRemark(c.getString(12));
                d.setPhoto(c.getString(13));
                list.add(d);
            }
            db.close();
            c.close();
            return list;
        }
        db.close();
        c.close();
        return null;
    }


    public static ArrayList<Goods> getTextGoods() {
        ArrayList<Goods> list = new ArrayList<Goods>();
        for(int i = 0;i<10;i++){
            Goods d = new Goods();
            d.Gimg = "http://www.godzone.cn/lost1?action=lostpic&lnum=2";
            d.Gid= 2;
            d.Gname="雨伞";
            d.place="教学楼321";
            d.Uname="王广健";
            d.Uid="1414010616";
            d.des="花雨伞";
            d.time="2016-12-17 晚上6点";
            d.type="lost";
            list.add(d);
        }
        return list;
    }

}
