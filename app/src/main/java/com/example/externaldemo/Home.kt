package com.example.externaldemo

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_add_teacher_details.*
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var helper = DbHelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var intent = getIntent()
        var userid = intent.getStringExtra("USER")
        var check = arrayOf(userid)
        var rs: Cursor = db.rawQuery("SELECT * FROM TEACHER WHERE USERID=?",check)
        rs.requery()
        if(rs.moveToNext())
        {
            tname.text = rs.getString(1)
            tdesignation.text = rs.getString(2)
            tcourse.text = rs.getString(3)
            tmnumber.text = rs.getString(4)
            temail.text = rs.getString(5)
        }
        else {
            tname.text = "No Record"
            tdesignation.text = "No Record"
            tcourse.text = "No Record"
            tmnumber.text = "No Record"
            temail.text = "No Record"
        }

        btnallstud.setOnClickListener {
            var intent = Intent(this, AllStudent::class.java)
            intent.putExtra("USER", userid)
            startActivity(intent)
        }

        btnlogout.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        btnaddstud.setOnClickListener {
            var intent = Intent(this, AddStudentsDetails::class.java)
            intent.putExtra("USER", userid)
            startActivity(intent)
        }
    }
}