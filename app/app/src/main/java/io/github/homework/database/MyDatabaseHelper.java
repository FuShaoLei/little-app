package io.github.homework.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import io.github.homework.MainActivity;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private String createMusic ="create table Music(" +
            "    id integer primary key autoincrement," +
            "    name text," +
            "    author text," +
            "    cover integer," +
            "    resrouce integer" +
            ")";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createMusic);
        Log.d("==>","创建数据库成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
