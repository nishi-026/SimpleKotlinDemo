package com.example.externaldemo

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_teacher_details.*

class AddTeacherDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_teacher_details)

        var helper = DbHelper(applicationContext)
        var db:SQLiteDatabase = helper.writableDatabase
        var useridupdate = intent.getStringExtra("USERUPDATE")
        var userid1 = intent.getStringExtra("USER1")
        var intent = getIntent()
        var userid = intent.getStringExtra("USER")
        var check = arrayOf(userid)
        var rs:Cursor = db.rawQuery("SELECT * FROM TEACHER WHERE USERID=?",check)
        rs.requery()
        if(rs.moveToNext())
        {
            name.setText(rs.getString(1).toString())
            designation.setText(rs.getString(2))
            course.setText(rs.getString(3))
            mnumber.setText(rs.getString(4))
            email.setText(rs.getString(5))
        }
        else
        {
            name.setHint("Enter Name")
            designation.setHint("Enter Designation")
            course.setHint("Enter Course")
            mnumber.setHint("Enter Mobile Number")
            email.setHint("Enter email")
        }

        btnupdate.setOnClickListener {
            if(name.text.toString() != "" && designation.text.toString() != "" && course.text.toString() != "" && mnumber.text.toString() != "" && email.text.toString() != "")
            {
                var cv = ContentValues()
                cv.put("USERID",userid)
                cv.put("NAME",name.text.toString())
                cv.put("DESIGNATION",designation.text.toString())
                cv.put("COURSE",course.text.toString())
                cv.put("MOBILE",mnumber.text.toString())
                cv.put("EMAIL",email.text.toString())
                db.insert("TEACHER",null,cv)
                rs.requery()
                Toast.makeText(this, "Teacher Information Updated", Toast.LENGTH_LONG).show()
                var intentupdate = Intent(this,MainActivity::class.java)
                intent.putExtra("USERUPDATE",useridupdate)
                startActivity(intentupdate)
            }
            else
            {
                Toast.makeText(this, "Enter all details", Toast.LENGTH_LONG).show()
            }
        }

        btnhome.setOnClickListener {
            var intent1 = Intent(this,MainActivity::class.java)
            intent1.putExtra("USER1",userid1)
            startActivity(intent1)
        }

        btnaddstud.setOnClickListener {
            var intent = Intent(this,AddStudentsDetails::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent)
        }
    }
}