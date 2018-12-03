package pers.asparaw.fakeneteasecloudmusic.dbstarter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBTool {
    SQLiteDatabase db;

    public DBTool(SQLiteDatabase db) {
        this.db = db;
    }

    public boolean addData(String name, String password) {
        boolean isOK;
        isOK = selectData(name);
        if (isOK) {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("password", password);
            db.insert("user", null, values);
        }
        return isOK;
    }

    public void createInfoTable() {
        String sql = "create table if not exists user(id INTEGER PRIMARY KEY autoincrement,name varchar(100),password varchar(30))";
        db.execSQL(sql);
    }

    public boolean selectData(String name) {
        boolean isOK = true;
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String tmpName = cursor.getString(cursor.getColumnIndex("name"));
                if (tmpName.equals(name)) {
                    isOK = false;
                    break;
                }
            } while (cursor.moveToNext());
        }
        return isOK;
    }

    public int selectData(String name, String password) {
        int isOK = 2;//登录失败
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            isOK = 3;//无此用户
            do {
                String tmpName = cursor.getString(cursor.getColumnIndex("name"));
                String tmpPwd = cursor.getString(cursor.getColumnIndex("password"));
                if (tmpName.equals(name)) {
                    if (tmpPwd.equals(password)) {
                        isOK = 0;//登录成功
                        return isOK;
                    } else {
                        isOK = 1;//密码错误
                        return isOK;
                    }
                }
            } while (cursor.moveToNext());
        }
        return isOK;
    }
}
