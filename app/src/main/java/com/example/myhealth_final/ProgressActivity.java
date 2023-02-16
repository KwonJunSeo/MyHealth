package com.example.myhealth_final;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.HashMap;
import java.util.Map;

/**
 * 精美进度风格
 * Created by huanghaibin on 2018/2/8.

 Copyright (C) 2013 huanghaibin_dev huanghaibin_dev@163.com

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.

 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 This file has been modified by KwonJunSeo to add support for foo and get faster baz processing.
 */

public class ProgressActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener {

    TextView mTextMonthDay;

    TextView mTextYear;

    TextView mTextCurrentDay;

    CalendarView mCalendarView;

    RelativeLayout mRelativeTool;
    private int mYear;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ProgressActivity.class));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_progress;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        mTextMonthDay = findViewById(R.id.tv_month_day);
        mTextYear = findViewById(R.id.tv_year);
        mRelativeTool = findViewById(R.id.rl_tool);
        mCalendarView = findViewById(R.id.calendarView);
        mTextCurrentDay = findViewById(R.id.tv_current_day);

        findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });
        mCalendarView.setOnCalendarSelectListener(this);
        mYear = mCalendarView.getCurYear();
        mTextYear.setText(String.valueOf(mYear));
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "월" + mCalendarView.getCurDay() + "일");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
    }

    @Override
    protected void initData() {
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();
        DbTest dbHelper = new DbTest(this, "db_cal", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM db_cal",null);

        Map<String, Calendar> map = new HashMap<>();
        while(cursor.moveToNext()){
            map.put(getSchemeCalendar(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), 0xFF40db25, cursor.getString(3)).toString(),
                    getSchemeCalendar(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), 0xFF40db25, cursor.getString(3)));
        }
        mCalendarView.setSchemeDate(map);
    }


    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);
        calendar.setScheme(text);
        return calendar;
    }


    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "월" + calendar.getDay() + "일");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mYear = calendar.getYear();
    }
}

