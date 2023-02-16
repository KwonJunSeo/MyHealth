package com.example.myhealth_final;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HealthList extends AppCompatActivity {

    ListView health_list;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_list);

        TextView name = (TextView)findViewById(R.id.name);

        DBHelper_login helper2 = new DBHelper_login(HealthList.this, DBHelper_login.tableName, null, 1);
        SQLiteDatabase database2 = helper2.getWritableDatabase();

        Cursor cursor2 = database2.rawQuery("SELECT * FROM Users",null);
        cursor2.moveToFirst();

        name.setText(cursor2.getString(0));

        //여기까진 뻔하다
        health_list = (ListView)findViewById(R.id.health_list);
        btn = (Button)findViewById(R.id.btn);


        DBHelper_List helper = new DBHelper_List(this);
        SQLiteDatabase database = helper.getReadableDatabase();


        //Cursor라는 그릇에 목록을 담아주기
        Cursor cursor = database.rawQuery("SELECT * FROM health",null);


        //리스트뷰에 목록 채워주는 도구인 adapter준비
        ListViewAdapter adapter = new ListViewAdapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            adapter.addItemToList(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        health_list.setAdapter(adapter);

        health_list = (ListView)findViewById(R.id.health_list);

        health_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Viewholder holder = (Viewholder) adapter.getItem(position);
                if(holder.getSelected()){
                    holder.setSelected(false);
                    deleteSub(holder.getHealth_name());
                }else{
                    holder.setSelected(true);
                    insertSub(holder.getHealth_name(),holder.getPart());
                }
                adapter.notifyDataSetChanged();

            }
        });

        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HealthList.this, SelectList.class);
                startActivity(intent);
            }
        });

    }

    void insertSub(String name,String part){
        DBHelper_SelectList helper_sub = new DBHelper_SelectList((this));
        SQLiteDatabase database1 = helper_sub.getReadableDatabase();

        String qry = "INSERT INTO select_health(health_name,part) VALUES ('"+name+"','"+part+"')";
        database1.execSQL(qry);

    }

    void deleteSub(String name){
        DBHelper_SelectList helper_sub = new DBHelper_SelectList((this));
        SQLiteDatabase database1 = helper_sub.getReadableDatabase();

        String qry = "DELETE FROM select_health WHERE health_name= ('"+name+"')";
        database1.execSQL(qry);
    }


}