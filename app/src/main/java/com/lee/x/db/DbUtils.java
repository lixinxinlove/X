package com.lee.x.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;

/**
 * Created by android on 2017/4/18.
 */
public class DbUtils {

    private SQLiteDatabase db;

    public void openDB() {

        File file = new File("/data/data/com.lee.x/databases");
        if (file.exists()) {
            db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.lee.x/databases/stu.db", null);
        } else {
            file.mkdir();
            db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.lee.x/databases/stu.db", null);
        }
    }

    public void createTable() {


        String stu_table = "create table user(id integer primary key autoincrement,name text,number text)";
        //执行SQL语句
        db.execSQL(stu_table);



    }

    public void insent() {

        String sql = "insert into user(name,number) values('李鑫鑫','01000')";
        db.execSQL(sql);
        db.close();
    }

    public String select() {
        String str = "";
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            str += username;
            str += password;
        }
        cursor.close();
        db.close();
        return str;
    }
}
