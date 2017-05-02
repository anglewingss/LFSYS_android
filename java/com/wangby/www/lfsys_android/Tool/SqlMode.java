package com.wangby.www.lfsys_android.Tool;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HAO on 2017/3/24.
 */

public class SqlMode extends SQLiteOpenHelper{


    public SqlMode(Context context){
        super(context,"SqlDatabase",null,1);

    }

    public SqlMode(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (name varchar(20),password varchar(20));");
        db.execSQL("create table lost(Gimg varchar(50),Gid integer,Gname varchar(20),place varchar(50),Uname varchar(20),Uid varchar(20),des varchar(50),time varchar(50),type varchar(20));");
        db.execSQL("create table found(Gimg varchar(50),Gid integer,Gname varchar(20),place varchar(50),Uname varchar(20),Uid varchar(20),des varchar(50),time varchar(50),type varchar(20));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
