package com.wangby.www.lfsys_android.Tool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wangby.www.lfsys_android.Object.Goods;
import com.wangby.www.lfsys_android.connect.User;

import java.util.ArrayList;

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
    public void saveGoods(ArrayList<Goods> list, String type){
        SQLiteDatabase db = sqlMode.getReadableDatabase();
        for (Goods newsBean : list) {
            ContentValues values = new ContentValues();
            values.put("Gimg", newsBean.Gimg);
            values.put("Gid", newsBean.Gid);
            values.put("Gname", newsBean.Gname);
            values.put("place", newsBean.place);
            values.put("Uname", newsBean.Uname);
            values.put("Uid", newsBean.Uid);
            values.put("des", newsBean.des);
            values.put("time", newsBean.time);
            values.put("type", newsBean.type);
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
//                user.name = c.getString(0);
//                user.password = c.getString(1);
            }
            db.close();
            c.close();
            return user;
        }
        db.close();
        c.close();
        return null;
    }

    public ArrayList<Goods> getGood(String type){
        String sql = "select * from "+type;
        ArrayList<Goods> list = new ArrayList<Goods>();
        SQLiteDatabase db = sqlMode.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        if(c != null && c.getCount()>0){
            while(c.moveToNext()){
                Goods d = new Goods();
                d.Gimg = c.getString(0);
                d.Gid=c.getInt(1);
                d.Gname=c.getString(2);
                d.place=c.getString(3);
                d.Uname=c.getString(4);
                d.Uid=c.getString(5);
                d.des=c.getString(6);
                d.time=c.getString(7);
                d.type=c.getString(8);
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
