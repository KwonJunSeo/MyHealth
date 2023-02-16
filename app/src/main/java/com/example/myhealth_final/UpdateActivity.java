package com.example.myhealth_final;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    int version = 1;
    DBHelper_login helper;
    SQLiteDatabase database;

    EditText idUpdateText;
    EditText tallUpdateText;
    EditText weightUpdateText;

    ImageButton btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        idUpdateText = (EditText) findViewById(R.id.idUpdateText);
        tallUpdateText = (EditText) findViewById(R.id.tallUpdateText);
        weightUpdateText = (EditText) findViewById(R.id.weightUpdateText);

        btnUpdate = (ImageButton) findViewById(R.id.btnUpdate);


        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String id = idUpdateText.getText().toString();
                String tall = tallUpdateText.getText().toString();
                String weight = weightUpdateText.getText().toString();

                helper = new DBHelper_login(UpdateActivity.this, DBHelper_login.tableName, null, version);
                database = helper.getWritableDatabase();

                //빈칸 입력 방지 조건문
                if (idUpdateText.getText().toString().replace(" ", "").equals("")) {
                    AlertDialog.Builder myAlertBuilder =
                            new AlertDialog.Builder(UpdateActivity.this);
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
                else if (tallUpdateText.getText().toString().replace(" ", "").equals("")) {
                    AlertDialog.Builder myAlertBuilder =
                            new AlertDialog.Builder(UpdateActivity.this);
                    myAlertBuilder.setTitle("경고");
                    myAlertBuilder.setMessage("신장을 입력해주세요.");
                    myAlertBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int which){
                        }
                    });
                    myAlertBuilder.show();
                }
                else if (weightUpdateText.getText().toString().replace(" ", "").equals("")) {
                    AlertDialog.Builder myAlertBuilder =
                            new AlertDialog.Builder(UpdateActivity.this);
                    myAlertBuilder.setTitle("경고");
                    myAlertBuilder.setMessage("체중을 입력해주세요.");
                    myAlertBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int which){
                        }
                    });
                    myAlertBuilder.show();
                }
                else{
                    //수정 - 삭제 후 재생성
                    helper.deleteUser(database);
                    helper.createTable(database);

                    helper.insertUser(database,id,tall,weight);
                    Toast toast = Toast.makeText(UpdateActivity.this, "수정이 완료되었습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}