package com.example.externaldemo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context):SQLiteOpenHelper(context,"STUDENT",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE LOGIN(USERID TEXT,PASSWORD TEXT)")
        db?.execSQL("CREATE TABLE TEACHER(USERID TEXT,NAME TEXT,DESIGNATION TEXT,COURSE TEXT,MOBILE TEXT,EMAIL TEXT)")
        db?.execSQL("CREATE TABLE STUDENT(USERID TEXT,RNO TEXT,NAME TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}