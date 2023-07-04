package com.example.externaldemo

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_students_details.*
import kotlinx.android.synthetic.main.activity_signup.*

class AddStudentsDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_students_details)

        var helper = DbHelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var intent = getIntent()
        var userid = intent.getStringExtra("USER")
        btnsave.setOnClickListener {
            var check = arrayOf(userid, rno.text.toString())
            var rs: Cursor = db.rawQuery("SELECT * FROM STUDENT WHERE USERID=? AND RNO=?", check)
            rs.moveToFirst()
            if (rs.moveToNext()) {
                Toast.makeText(this, "User Already Exists!!", Toast.LENGTH_LONG).show()
                rno.setText("")
                sname.setText("")
                rno.requestFocus()
            } else {
                var beforeinsert = rs.count
                var cv = ContentValues()
                cv.put("USERID", userid)
                cv.put("RNO", rno.text.toString())
                cv.put("Name", sname.text.toString())
                db.insert("STUDENT", null, cv)
                rs.requery()
                var afterinsert = rs.count
                if (afterinsert > beforeinsert) {
                    Toast.makeText(this, "User created!!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "User not created!!", Toast.LENGTH_LONG).show()
                }
            }
        }
        btnview.setOnClickListener {
            var intent = Intent(this, AllStudent::class.java)
            intent.putExtra("USER", userid)
            startActivity(intent)
        }

        logoutbtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}