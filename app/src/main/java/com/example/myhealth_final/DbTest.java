package com.example.myhealth_final;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbTest extends SQLiteOpenHelper {

    public DbTest(Context context, String name, SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION) {
        super(context, name, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists db_cal (year Integer,month Integer,day Integer, percent TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }




}
