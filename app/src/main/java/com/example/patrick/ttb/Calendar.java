package com.example.patrick.ttb;

import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar extends AppCompatActivity implements View.OnClickListener {

    private CardView ntbtn;
    private CalendarView cv;
    private String date;
    private long mLastClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ntbtn = (CardView) findViewById(R.id.notebtn);
        cv = (CalendarView) findViewById(R.id.calendarView);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "." + (month+1) + "." + year;
            }
        });
        ntbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(SystemClock.elapsedRealtime() - mLastClicked < 1000) {
            return;
        }
        mLastClicked = SystemClock.elapsedRealtime();

        Intent i;
        switch (v.getId()) {
            case R.id.notebtn:
                i = new Intent(this, mainnotes.class);
                if(date == null) {
                    long val = cv.getDate();
                    Date date2 = new Date(val);
                    SimpleDateFormat df = new SimpleDateFormat("d.M.yyyy");
                    String dateText = df.format(date2);
                    date = dateText;
                }
                i.putExtra("date", date);
                startActivity(i);
        }
    }
}
