package com.example.externaldemo

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var helper = DbHelper(applicationContext)
        var db: SQLiteDatabase =helper.writableDatabase

        btnlogin.setOnClickListener {
            if(userid.text.toString() != "" && password.text.toString() != "") {
                var etid = userid.text.toString()
                var etpw = password.text.toString()
                var check = arrayOf(etid, etpw)
                var rs: Cursor = db.rawQuery("SELECT * FROM LOGIN WHERE USERID=? AND PASSWORD=?", check)
                rs.requery()
                if (rs.moveToNext()) {
                    var intent = Intent(this, AddTeacherDetails::class.java)
                    intent.putExtra("USER", etid)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "USER NOT FOUND", Toast.LENGTH_LONG).show()
                    userid.setText("")
                    password.setText("")
                    userid.requestFocus()
                }
            }
            else if(userid.text.toString() == "" || password.text.toString() == "")
            {
                Toast.makeText(this,"Enter All Credentials", Toast.LENGTH_LONG).show()
                userid.setText("")
                password.setText("")
                userid.requestFocus()
            }
            else
            {
                Toast.makeText(this,"Invalid Credentials", Toast.LENGTH_LONG).show()
                userid.setText("")
                password.setText("")
                userid.requestFocus()
            }
        }

        btnsignup.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
        }
    }
}