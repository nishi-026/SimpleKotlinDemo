package com.example.externaldemo

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btnsignup2.setOnClickListener {
            if (useridsignup.text.toString() == "" || passwordsignup.text.toString() == "" || passwordsignup2.text.toString() == "") {
                Toast.makeText(this, "Enter All Credentials", Toast.LENGTH_LONG).show()
                useridsignup.setText("")
                passwordsignup.setText("")
                passwordsignup2.setText("")
                useridsignup.requestFocus()
            } else if (passwordsignup.text.toString() != passwordsignup2.text.toString()) {
                Toast.makeText(this, "Both password must be same", Toast.LENGTH_LONG).show()
                passwordsignup.setText("")
                passwordsignup2.setText("")
                passwordsignup.requestFocus()
            } else {
                var helper = DbHelper(applicationContext)
                var db: SQLiteDatabase = helper.writableDatabase
                var check = arrayOf(useridsignup.text.toString())
                var rs: Cursor = db.rawQuery("SELECT * FROM LOGIN WHERE USERID=?", check)
                rs.requery()
                if (rs.moveToNext()) {
                    Toast.makeText(this, "User Already Exists!!", Toast.LENGTH_LONG).show()
                    useridsignup.setText("")
                    passwordsignup.setText("")
                    passwordsignup2.setText("")
                    useridsignup.requestFocus()
                } else {
                    var beforeinsert = rs.count
                    var cv = ContentValues()
                    cv.put("USERID", useridsignup.text.toString())
                    cv.put("PASSWORD", passwordsignup.text.toString())
                    db.insert("LOGIN", null, cv)
                    rs.requery()
                    var afterinsert = rs.count
                    if (afterinsert > beforeinsert) {
                        Toast.makeText(this, "User created!!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, "User not created!!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}