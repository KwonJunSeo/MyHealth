package com.example.myhealth_final;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper_login extends SQLiteOpenHelper {

    public static final String tableName = "Users";

    public DBHelper_login(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("tag","db 생성_db가 없을때만 최초로 실행함");
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    public void createTable(SQLiteDatabase db){
        String sql = "CREATE TABLE " + tableName + "(id text, tall text, weight text)";
        try {
            db.execSQL(sql);
        }catch (SQLException e){
        }
    }

    public void insertUser(SQLiteDatabase db, String id, String tall, String weight){
        Log.i("tag","회원가입을 했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName + "(id, tall, weight)" + "values('"+ id +"', '"+ tall +"', '"+ weight +"')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }


    public void updateUser(SQLiteDatabase db, String id, String tall, String weight){
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    public Cursor getAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("SELECT * FROM " + tableName, null);
        return res;
    }

    public static void deleteUser(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    //https://crazykim2.tistory.com/648
    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0);
        }
        return result;
    }
}

