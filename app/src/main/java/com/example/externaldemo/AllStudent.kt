package com.example.externaldemo

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_all_student.*

class AllStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_student)

        var helper = DbHelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var intent = getIntent()
        var userid = intent.getStringExtra("USER")
        var check = arrayOf(userid)
        var rs: Cursor = db.rawQuery("SELECT * FROM STUDENT WHERE USERID=?",check)
        rs.moveToFirst()
        if(rs.moveToNext())
        {
            val rno = ArrayList<String>()
            val name = ArrayList<String>()
            rs.moveToFirst()
            while(rs.moveToNext())
            {
                rno.add(rs.getString(1))
                name.add(rs.getString(2))
            }
            var myadapter = CustomAdapter(this,rno,name)
            listview.adapter = myadapter
        }
        else
        {
            Toast.makeText(this, "No record found", Toast.LENGTH_LONG).show()
            var intent = Intent(this,AddStudentsDetails::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(1,101,1,"LOGOUT")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            101 -> startActivity(Intent(this,MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}