package com.wangby.www.lfsys_android.Tool;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HAO on 2017/3/24.
 */

public class SqlMode extends SQLiteOpenHelper{

    /**
     * 初次建立数据库
     * @param context
     */
    public SqlMode(Context context){
        super(context,"SqlDatabase",null,1);
    }

    public SqlMode(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     * 初次建表
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (stuNum integer,password varchar(20),name varchar(20),phone varchar(20),oredit integer);");
        db.execSQL("create table lost(type integer,id integer,goodsName varchar(50),subKindID integer,subKindName varchar(50),place varchar(50),time TimeStamp,decp varchar(50),datail varchar(50),stuNum integer,isClash integer,publishTime integer,remark varchar(50),photo varchar(50));");
        db.execSQL("create table found(type integer,id integer,goodsName varchar(50),subKindID integer,subKindName varchar(50),place varchar(50),time TimeStamp,decp varchar(50),datail varchar(50),stuNum integer,isClash integer,publishTime integer,remark varchar(50),photo varchar(50));");
     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
