package com.example.myhealth_final;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int version = 1;
    DBHelper_login helper;
    SQLiteDatabase database;

    ImageButton setting,cal,food,health;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btn1 = (ImageButton) findViewById(R.id.btn1);
        ImageButton btn2 = (ImageButton) findViewById(R.id.btn2);
        ImageButton btn3 = (ImageButton) findViewById(R.id.btn3);
        TextView tall = (TextView)findViewById(R.id.tall);
        TextView weight = (TextView)findViewById(R.id.weight);
        TextView BMI = (TextView)findViewById(R.id.BMI);
        TextView name = (TextView) findViewById(R.id.name);

        cal = (ImageButton)findViewById(R.id.cal);
        food = (ImageButton)findViewById(R.id.food);
        health = (ImageButton)findViewById(R.id.health);
        setting = (ImageButton) findViewById(R.id.setting);

        helper = new DBHelper_login(MainActivity.this, DBHelper_login.tableName, null, version);
        database = helper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM Users",null);
        cursor.moveToFirst();

        name.setText(cursor.getString(0));
        tall.setText(cursor.getString(1));
        weight.setText(cursor.getString(2));
        float tall_value = cursor.getFloat(1)/100;
        float weight_value = cursor.getFloat(2);
        int BMI_value = (int)(weight_value/(tall_value*tall_value));
        BMI.setText(String.valueOf(BMI_value));


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectList.class);
                startActivity(intent);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Food.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ProgressActivity.class));
            }
        });

        setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UpdateActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ProgressActivity.class);
                startActivity(intent);
            }
        });



        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SelectList.class);
                startActivity(intent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Food.class);
                startActivity(intent);
            }
        });

    }
}