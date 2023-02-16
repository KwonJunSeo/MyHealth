package com.example.myhealth_final;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    int version = 1;
    DBHelper_login helper;
    SQLiteDatabase database;

    EditText idEditText;
    EditText tallEditText;
    EditText weightEditText;
    ImageButton btnJoin;

    String sql;
    Cursor cursor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        idEditText = (EditText) findViewById(R.id.idEditText);
        tallEditText = (EditText) findViewById(R.id.tallEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);

        btnJoin = (ImageButton) findViewById(R.id.btnJoin);

        helper = new DBHelper_login(JoinActivity.this, DBHelper_login.tableName, null, version);
        database = helper.getWritableDatabase();

        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String id = idEditText.getText().toString();
                String tall = tallEditText.getText().toString();
                String weight = weightEditText.getText().toString();

                sql = "SELECT id FROM "+ helper.tableName + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);


                //공백 입력시 경고문 띄우는 조건문
                if (idEditText.getText().toString().replace(" ", "").equals("")) {
                    AlertDialog.Builder myAlertBuilder =
                            new AlertDialog.Builder(JoinActivity.this);
                    // alert의 title과 Messege 세팅
                    myAlertBuilder.setTitle("경고");
                    myAlertBuilder.setMessage("아이디를 입력해주세요.");
                    // 버튼 추가 (Ok 버튼과 Cancle 버튼 )
                    myAlertBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int which){
                            // OK 버튼을 눌렸을 경우
                        }
                    });
                    // Alert를 생성해주고 보여주는 메소드(show를 선언해야 Alert가 생성됨)
                    myAlertBuilder.show();
                }
                else if (tallEditText.getText().toString().replace(" ", "").equals("")) {
                    AlertDialog.Builder myAlertBuilder =
                            new AlertDialog.Builder(JoinActivity.this);
                    myAlertBuilder.setTitle("경고");
                    myAlertBuilder.setMessage("신장을 입력해주세요.");
                    myAlertBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int which){
                        }
                    });
                    myAlertBuilder.show();
                }
                else if (weightEditText.getText().toString().replace(" ", "").equals("")) {
                    AlertDialog.Builder myAlertBuilder =
                            new AlertDialog.Builder(JoinActivity.this);
                    myAlertBuilder.setTitle("경고");
                    myAlertBuilder.setMessage("체중을 입력해주세요.");
                    myAlertBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int which){
                        }
                    });
                    myAlertBuilder.show();
                }
                else{
                    if(cursor.getCount() != 0){
                        //존재하는 아이디입니다.
                        Toast toast = Toast.makeText(JoinActivity.this, "존재하는 아이디입니다.", Toast.LENGTH_SHORT);
                        toast.show();
                    }else{
                        helper.insertUser(database,id,tall,weight);
                        Toast toast = Toast.makeText(JoinActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}

