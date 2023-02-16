package com.example.myhealth_final;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.TimerTask;

public class Timer extends AppCompatActivity {

    LinearLayout timeCountSettingLV, timeCountLV, breakCountSettingLV, breakCountLV;
    EditText minuteET, secondET, minuteET_break, secondET_break;
    TextView minuteTV, secondTV, finishTV, minuteTV_break, secondTV_break;
    ImageButton startBtn,startBtn2;
    int minute, second;
    int minute_break, second_break;
    int count_set = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timeCountSettingLV = (LinearLayout)findViewById(R.id.timeCountSettingLV);
        timeCountLV = (LinearLayout)findViewById(R.id.timeCountLV);
        breakCountSettingLV = (LinearLayout)findViewById(R.id.breakCountSettingLV);
        breakCountLV = (LinearLayout)findViewById(R.id.breakCountLV);

        minuteET = (EditText)findViewById(R.id.minuteET);
        secondET = (EditText)findViewById(R.id.secondET);
        minuteET_break = (EditText)findViewById(R.id.minuteET_break);
        secondET_break = (EditText)findViewById(R.id.secondET_break);

        minuteTV = (TextView)findViewById(R.id.minuteTV);
        secondTV = (TextView)findViewById(R.id.secondTV);
        minuteTV_break = (TextView)findViewById(R.id.minuteTV_break);
        secondTV_break = (TextView)findViewById(R.id.secondTV_break);


        finishTV = (TextView)findViewById(R.id.finishTV);

        startBtn = (ImageButton) findViewById(R.id.startBtn);
        startBtn2 = (ImageButton) findViewById(R.id.startBtn2);
        Button rstBtn = (Button)findViewById(R.id.reset_btn);



        Intent intent = getIntent();
        count_set = intent.getIntExtra("세트",0);
        finishTV.setText(count_set + " SET");

        rstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("세트",count_set);
                finish();
                startActivity(intent);
            }
        });

        // 시작버튼 이벤트 1처리
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeCountSettingLV.setVisibility(View.GONE);
                timeCountLV.setVisibility(View.VISIBLE);

                minuteTV.setText(minuteET.getText().toString());
                secondTV.setText(secondET.getText().toString());


                if(minuteET.getText().toString().equals("")){
                    minuteET.setText("0");
                }
                if(secondET.getText().toString().equals("")){
                    secondET.setText("0");
                }


                minute = Integer.parseInt(minuteET.getText().toString());
                second = Integer.parseInt(secondET.getText().toString());


                java.util.Timer timer = new java.util.Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        // 반복실행할 구문

                        // 0초 이상이면
                        if(second != 0) {
                            //1초씩 감소
                            second--;

                            // 0분 이상이면
                        } else if(minute != 0) {
                            // 1분 = 60초
                            second = 60;
                            second--;
                            minute--;
                        }

                        //분, 초가 10이하(한자리수) 라면
                        // 숫자 앞에 0을 붙인다 ( 8 -> 08 )
                        if(second <= 9){
                            secondTV.setText("0" + second);
                        } else {
                            secondTV.setText(Integer.toString(second));

                        }

                        if(minute <= 9){
                            minuteTV.setText("0" + minute);
                        } else {
                            minuteTV.setText(Integer.toString(minute));
                        }



                        // 시분초가 다 0이라면 toast를 띄우고 타이머를 종료한다..
                        if(minute == 0 && second == 0) {
                            timer.cancel();//타이머 종료
                            count_set++;
                            finishTV.setText(count_set + " SET");
                            Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                            vib.vibrate(1000);
                        }
                    }
                };


                //타이머를 실행
                timer.schedule(timerTask, 0, 1000); //Timer 실행
            }
        });

        startBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breakCountSettingLV.setVisibility(View.GONE);
                breakCountLV.setVisibility(View.VISIBLE);

                minuteTV_break.setText(minuteET_break.getText().toString());
                secondTV_break.setText(secondET_break.getText().toString());

                if(minuteET_break.getText().toString().equals("")){
                    minuteET_break.setText("0");
                }
                if(secondET_break.getText().toString().equals("")){
                    secondET_break.setText("0");
                }

                minute_break = Integer.parseInt(minuteET_break.getText().toString());
                second_break = Integer.parseInt(secondET_break.getText().toString());

                java.util.Timer timer2 = new java.util.Timer();
                TimerTask timerTask2 = new TimerTask() {
                    @Override
                    public void run() {
                        // 반복실행할 구문

                        // 0초 이상이면
                        if(second_break != 0) {
                            //1초씩 감소
                            second_break--;

                            // 0분 이상이면
                        } else if(minute_break != 0) {
                            // 1분 = 60초
                            second_break = 60;
                            second_break--;
                            minute_break--;
                        }

                        //분, 초가 10이하(한자리수) 라면
                        // 숫자 앞에 0을 붙인다 ( 8 -> 08 )
                        if(second_break <= 9){
                            secondTV_break.setText("0" + second_break);
                        } else {
                            secondTV_break.setText(Integer.toString(second_break));

                        }

                        if(minute_break <= 9){
                            minuteTV_break.setText("0" + minute_break);
                        } else {
                            minuteTV_break.setText(Integer.toString(minute_break));
                        }



                        // 시분초가 다 0이라면 toast를 띄우고 타이머를 종료한다..
                        if(minute_break == 0 && second_break == 0) {
                            timer2.cancel();//타이머 종료
                        }
                    }
                };

                timer2.schedule(timerTask2, 0, 1000); //Timer 실행

            }
        });




    }
}