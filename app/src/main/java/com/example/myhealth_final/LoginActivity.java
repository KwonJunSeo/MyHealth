package com.example.myhealth_final;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class LoginActivity extends AppCompatActivity {

    int version = 1;

    ImageButton btnLogin;
    //Button btn;   //메인가기 버튼 btn2_main

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (ImageButton) findViewById(R.id.btnLogin);

        //DB 파일 존재 여부 검증
        String filePath = "/data/data/com.example.myhealth_final/databases/";
        File file = new File(filePath + "Users");


        if(file.exists()){
            //skip
            if(dataExists()) {
                Toast toast = Toast.makeText(LoginActivity.this, "환영합니다.", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(LoginActivity.this, "회원가입 화면으로 이동합니다.", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    boolean dataExists() {
        DBHelper_login dbLogin = new DBHelper_login(this, "Users", null, 1);
        SQLiteDatabase database = dbLogin.getReadableDatabase();

        Cursor cursor = null;
        String checkQuery = "SELECT * from Users";
        cursor= database.rawQuery(checkQuery,null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}

