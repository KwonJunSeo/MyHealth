package com.example.myhealth_final;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper_List extends SQLiteOpenHelper {

    final static String DB_NAME = "health.db";  //DB이름
    final static int DB_VERSION = 2; //DB버전


    //생성자
    public DBHelper_List(@Nullable Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //테이블의 구조는 여기서 설계
        String qry = "CREATE TABLE health(health_id INTEGER PRIMARY KEY AUTOINCREMENT,health_name TEXT NOT NULL,part TEXT)";
        sqLiteDatabase.execSQL(qry);


        //없으면 썰렁하니 아무 데이터라도 넣어주기
        qry = "INSERT INTO health(health_name,part) VALUES('런닝','유산소')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('걷기','유산소')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('사이클','유산소')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('플랭크','코어')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('할로우','코어')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('브릿지','코어')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('스쿼트','하체')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('런지','하체')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('레그 프레스','하체')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('레그 컬','하체')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('팔굽혀펴기','가슴')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('벤치프레스','가슴')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('인클라인벤치프레스','가슴')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('덤벨프레스','가슴')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('체스트프레스','가슴')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('밀리터리프레스','어꺠')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('덤벨숄더프레스','어꺠')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('덤벨프론트레이즈','어꺠')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('사이드레터럴레이즈','어꺠')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('벤트오버레터럴레이즈','어꺠')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('풀업(턱걸이)','등')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('렛풀다운','등')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('케이블 로우','등')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('바벨로우','등')";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO health(health_name,part) VALUES('데드리프트','등')";
        sqLiteDatabase.execSQL(qry);



    }


    //버전 업데이트 될때마다 호출 되는데 마지막에 onCreate도 같이 실행되기 때문에 여기서 먼저 DB에 존재하는 테이블들을 지워줘야함.
    //한마디로 초기화역할
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String qry = "DROP TABLE IF EXISTS health";
        sqLiteDatabase.execSQL(qry);

        onCreate(sqLiteDatabase);

    }
}
