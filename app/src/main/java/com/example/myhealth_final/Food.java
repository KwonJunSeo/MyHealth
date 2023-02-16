package com.example.myhealth_final;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Food extends AppCompatActivity {

    ImageButton setting,cal,home,health;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        TextView name = (TextView)findViewById(R.id.name);
        TextView BMI = (TextView)findViewById(R.id.BMI);
        TextView food_tv = (TextView)findViewById(R.id.food_tv);
        cal = (ImageButton)findViewById(R.id.cal);
        home = (ImageButton)findViewById(R.id.home);
        health = (ImageButton)findViewById(R.id.health);
        setting = (ImageButton) findViewById(R.id.setting);

        DBHelper_login helper = new DBHelper_login(Food.this, DBHelper_login.tableName, null, 1);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM Users",null);
        cursor.moveToFirst();

        name.setText(cursor.getString(0));
        float tall_value = cursor.getFloat(1)/100;
        float weight_value = cursor.getFloat(2);
        int BMI_value = (int)(weight_value/(tall_value*tall_value));
        BMI.setText(String.valueOf(BMI_value));

        if(BMI_value>30){
            food_tv.setText("당신의 체중은 과체중입니다\n당신은 식사를 규칙적으로 수행하고 식사량을 줄일 필요가 있습니다\n포만감이 높은 단백질,섬유실 식사를 추천합니다.\n튀기는음식보단 굽는음식를 추천합니다.\nex) 베리류,우유,지방이 적은 고기류");
        }else if(BMI_value>18){
            food_tv.setText("당신의 체중은 정상입니다\n당신의 체중에 권장되는 식사는 영양소가 고르게 분포되어있는 일반식입니다.\nex) 통곡물,잡곡밥,고기류,등푸른생선,각종 채소와 과일류");
        }else{
            food_tv.setText("당신의 체중은 저체중입니다\n당신은 식사주기를 하루에 5~6회로 늘릴필요가 있습니다\n견과류를 간식으로 종종 섭취하는것을 추천합니다.\nex) 통곡물,견과류,고기류,각종 채소와 과일류");
        }

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

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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


    }
}