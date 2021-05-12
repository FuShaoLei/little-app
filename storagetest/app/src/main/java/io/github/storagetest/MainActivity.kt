package io.github.storagetest

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 1)
        // 创建数据库
        create_table.setOnClickListener {
            dbHelper.writableDatabase
        }
        // 增加数据
        add_data.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                put("name", "admin")
                put("author", "me")
                put("pages", 252)
                put("price", 16.96)
            }
            db.insert("Book", null, values1)
            val values2 = ContentValues().apply {
                put("name", "root")
                put("author", "you")
                put("pages", 454)
                put("price", 96.16)
            }
            db.insert("Book", null, values2)
        }
        // 更新数据
        update_data.setOnClickListener {
            Toast.makeText(this, "更新数据", Toast.LENGTH_SHORT).show()
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 19.99)
            db.update("Book", values, "name = ?", arrayOf("root"))
        }
        // 删除数据
        delete_data.setOnClickListener {
            Toast.makeText(this, "删除数据", Toast.LENGTH_SHORT).show()
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("400"))
        }
        // 查询数据
        query_data.setOnClickListener {
            Toast.makeText(this, "查询数据", Toast.LENGTH_SHORT).show()
            val db = dbHelper.writableDatabase

            val cursor = db.rawQuery("select * from Book", null)
            if (cursor.moveToNext()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getString(cursor.getColumnIndex("pages"))
                    val price = cursor.getString(cursor.getColumnIndex("price"))
                    Log.d("==>", "查询到的数据是：$name,$author,$pages,$price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
    }
}