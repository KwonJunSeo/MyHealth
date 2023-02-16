package com.example.myhealth_final;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SelectList extends AppCompatActivity {


    ListView select_health_list;
    Button btn;

    int number = 1;
    float max = 0;
    float count = 0;

    Date ct = Calendar.getInstance().getTime();
    SimpleDateFormat week = new SimpleDateFormat("EE", Locale.getDefault());
    SimpleDateFormat day = new SimpleDateFormat("dd", Locale.getDefault());
    SimpleDateFormat month = new SimpleDateFormat("MM", Locale.getDefault());
    SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.getDefault());

    String weekDay = week.format(ct);
    String dayDay = day.format(ct);
    String monthDay = month.format(ct);
    String yearDay = year.format(ct);


    ImageButton timer,plus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "운동전에는 스트레칭이 필수!", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_select_list);

        TextView name = (TextView)findViewById(R.id.name);

        DBHelper_login helper2 = new DBHelper_login(SelectList.this, DBHelper_login.tableName, null, 1);
        SQLiteDatabase database2 = helper2.getWritableDatabase();

        Cursor cursor2 = database2.rawQuery("SELECT * FROM Users",null);
        cursor2.moveToFirst();

        name.setText(cursor2.getString(0));

        //여기까진 뻔하다
        select_health_list = (ListView)findViewById(R.id.select_health_list);
        btn = (Button)findViewById(R.id.btn);
        timer = (ImageButton)findViewById(R.id.timer);
        plus = (ImageButton)findViewById(R.id.plus);

        DBHelper_SelectList helper = new DBHelper_SelectList(this);
        SQLiteDatabase database = helper.getReadableDatabase();


        //Cursor라는 그릇에 목록을 담아주기
        Cursor cursor = database.rawQuery("SELECT * FROM select_health",null);


        //리스트뷰에 목록 채워주는 도구인 adapter준비
        ListViewAdapter adapter = new ListViewAdapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            adapter.addItemToList(number,cursor.getString(1),cursor.getString(2));
            number++;
            max++;
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        select_health_list.setAdapter(adapter);

        select_health_list = (ListView)findViewById(R.id.select_health_list);


        select_health_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Viewholder holder = (Viewholder) adapter.getItem(position);
                if(holder.getSelected()){
                    holder.setSelected(false);
                    count--;
                }else{
                    holder.setSelected(true);
                    count++;
                }
                adapter.notifyDataSetChanged();

            }
        });

        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                float percent = ((count/max) * 100);
                int per = (int)percent;
                String value = String.valueOf(per);
                delete_select_health();
                Intent intent = new Intent(SelectList.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Toast.makeText(SelectList.this, "오운완!", Toast.LENGTH_SHORT).show();
                if(dataExists()){
                    updatePercent(value);
                }else{
                    insertSub(value);
                }
                startActivity(intent);
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectList.this,Timer.class);
                Toast.makeText(SelectList.this, "시간을 설정해주세요", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectList.this,HealthList.class);
                startActivity(intent);
            }
        });

    }

    void delete_select_health(){
        DBHelper_SelectList helper_sub = new DBHelper_SelectList((this));
        SQLiteDatabase database1 = helper_sub.getReadableDatabase();

        String qry = "DELETE FROM select_health";
        database1.execSQL(qry);
    }

    void insertSub(String percent){
        DbTest dbTest = new DbTest(this,"db_cal",null,1);
        SQLiteDatabase database = dbTest.getReadableDatabase();

        String qry = "INSERT INTO db_cal('year','month','day','percent') " +
                "VALUES ('"+yearDay+"','"+monthDay+"','"+dayDay+"','"+percent+"')";
        database.execSQL(qry);
    }

    boolean dataExists() {
        DbTest dbTest = new DbTest(this,"db_cal",null,1);
        SQLiteDatabase database = dbTest.getReadableDatabase();

        Cursor cursor = null;
        String checkQuery = "SELECT day from db_cal WHERE day= " + dayDay;
        cursor= database.rawQuery(checkQuery,null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    void updatePercent(String percent) {
        DbTest dbTest = new DbTest(this,"db_cal",null,1);
        SQLiteDatabase database = dbTest.getWritableDatabase();

        String qry = "UPDATE db_cal SET percent = " + "'" + percent + "'" + " WHERE day = " + "'"+ Integer.parseInt(dayDay) + "'";
        database.execSQL(qry);
    }



}