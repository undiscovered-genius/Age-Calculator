package com.example.agecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.util.Calendar.*;

public class MainActivity extends AppCompatActivity {

    private SwitchCompat mode;
    private TextView dob, todaysdate;
    private TextView day1, day2, day3, day4, day5;
    private TextView daySpend, monthSpend, yearSpend;
    private TextView nxtBday;
    private Button calculate;
    public String dobDate, nxtbday,nxtbday0, nxtbday1, nxtbday2, nxtbday3, nxtbday4, nxtbday5;
    public LocalDate localDate1, localDate2;
    public Period period;
    DatePickerDialog.OnDateSetListener setListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mode = findViewById(R.id.modeSwitch);
        dob = findViewById(R.id.dob);
        todaysdate = findViewById(R.id.todaydate);
        calculate = findViewById(R.id.calculate);
        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day5 = findViewById(R.id.day5);
        daySpend = findViewById(R.id.spendDate);
        monthSpend = findViewById(R.id.spendMonth);
        yearSpend = findViewById(R.id.spendYear);
        nxtBday = findViewById(R.id.nxtBday);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            mode.setChecked(true);
        }

        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        Calendar calendar = getInstance();
        final int yyyy = calendar.get(YEAR);
        final  int mm = calendar.get(MONTH);
        final int dd = calendar.get(DAY_OF_MONTH);

        String aaj;

        if(mm<10){
            todaysdate.setText(""+dd+"-0"+(mm+1)+"-"+yyyy);
            aaj = ""+dd+"-0"+(mm+1)+"-"+yyyy;
        }else{
            todaysdate.setText(""+dd+"-"+(mm+1)+"-"+yyyy);
            aaj = ""+dd+"-"+(mm+1)+"-"+yyyy;
        }

        final String today = aaj;

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,setListner,2000,00,01);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String DATE = null;
//                DATE = dayOfMonth+"-"+month+"-"+year;

                if (month < 10 && dayOfMonth >= 10){
                    DATE = dayOfMonth+"-0"+month+"-"+year;
                    nxtbday0 = dayOfMonth+"-0"+month+"-"+(yyyy);
                    nxtbday = dayOfMonth+"-0"+month+"-"+(yyyy+1);
                    nxtbday1 = dayOfMonth+"-0"+month+"-"+(yyyy+1);
                    nxtbday2 = dayOfMonth+"-0"+month+"-"+(yyyy+2);
                    nxtbday3 = dayOfMonth+"-0"+month+"-"+(yyyy+3);
                    nxtbday4 = dayOfMonth+"-0"+month+"-"+(yyyy+4);
                    nxtbday5 = dayOfMonth+"-0"+month+"-"+(yyyy+5);
                }else  if (month < 10 && dayOfMonth < 10){
                    DATE = "0"+dayOfMonth+"-0"+month+"-"+year;
                    nxtbday0 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy);
                    nxtbday = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+1);
                    nxtbday1 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+1);
                    nxtbday2 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+2);
                    nxtbday3 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+3);
                    nxtbday4 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+4);
                    nxtbday5 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+5);
                }else if (dayOfMonth < 10 && month >= 10){
                    DATE = "0"+dayOfMonth+"-"+month+"-"+year;
                    nxtbday0 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+1);
                    nxtbday = "0"+dayOfMonth+"-"+month+"-"+(yyyy+1);
                    nxtbday1 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+1);
                    nxtbday2 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+2);
                    nxtbday3 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+3);
                    nxtbday4 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+4);
                    nxtbday5 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+5);
                }
                dobDate = DATE;

                dob.setText(DATE);
            }
        };

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String check = dob.getText().toString().trim();
                if (check.isEmpty()){
                    dob.setError("Enter DOB");
                }else{
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        Intent getV = new Intent(getApplicationContext(),MainActivity.class);
                        Log.d("Today Date Error : ", today);
                        Log.d("Dob Date error : ", dobDate);
                        Date d1 = simpleDateFormat.parse(today);
                        Date d2 = simpleDateFormat.parse(dobDate);
                        Date nd = simpleDateFormat.parse(nxtbday);
                        Date nd0 = simpleDateFormat.parse(nxtbday0);
                        Date nd1 = simpleDateFormat.parse(nxtbday1);
                        Date nd2 = simpleDateFormat.parse(nxtbday2);
                        Date nd3 = simpleDateFormat.parse(nxtbday3);
                        Date nd4 = simpleDateFormat.parse(nxtbday4);
                        Date nd5 = simpleDateFormat.parse(nxtbday5);
//                        Log.d("Next BDay : ", String.valueOf(nd));
//                        Log.d("Next BDay 1 : ", String.valueOf(nd1));
//                        Log.d("Next BDay 2 : ", String.valueOf(nd2));
//                        Log.d("Next BDay 3 : ", String.valueOf(nd3));
//                        Log.d("Next BDay 4 : ", String.valueOf(nd4));
//                        Log.d("Next BDay 5 : ", String.valueOf(nd5));
//
                        long diff_in_time = nd.getTime() - d1.getTime();
                        long diff_in_time0 = nd0.getTime() - d1.getTime();
                        long diff_in_time1 = nd1.getTime() - d1.getTime();
                        long diff_in_time2 = nd2.getTime() - d1.getTime();
                        long diff_in_time3 = nd3.getTime() - d1.getTime();
                        long diff_in_time4 = nd4.getTime() - d1.getTime();
                        long diff_in_time5 = nd5.getTime() - d1.getTime();
//                        int days = (int) (diff_in_time / (1000*60*60*24));
//                        long diff_in_month = (TimeUnit.MILLISECONDS.toDays(diff_in_time) % 365) / 30;
                        long diff_in_day = (TimeUnit.MILLISECONDS.toDays(diff_in_time));
                        long diff_in_day0 = (TimeUnit.MILLISECONDS.toDays(diff_in_time0));
                        long diff_in_day1 = (TimeUnit.MILLISECONDS.toDays(diff_in_time1));
                        long diff_in_day2 = (TimeUnit.MILLISECONDS.toDays(diff_in_time2));
                        long diff_in_day3 = (TimeUnit.MILLISECONDS.toDays(diff_in_time3));
                        long diff_in_day4 = (TimeUnit.MILLISECONDS.toDays(diff_in_time4));
                        long diff_in_day5 = (TimeUnit.MILLISECONDS.toDays(diff_in_time5));

//                        long diff_in_year = TimeUnit.MILLISECONDS.toDays(diff_in_time) / 365;

                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.setTimeInMillis(d1.getTime());
//                        Log.d("Calendar 1 : ", String.valueOf(calendar1));

                        Calendar calendar2 = Calendar.getInstance();
                        calendar2.setTimeInMillis(d2.getTime());
//                        Log.d("Calendar 2 : ", String.valueOf(calendar2));

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            localDate1 = LocalDate.of(calendar1.get(Calendar.YEAR),calendar1.get(Calendar.MONTH )+1,calendar1.get(Calendar.DAY_OF_MONTH));
                            localDate2 = LocalDate.of(calendar2.get(Calendar.YEAR),calendar2.get(Calendar.MONTH )+1,calendar2.get(Calendar.DAY_OF_MONTH));
//                            Log.d("LocatDate 1 : ", String.valueOf(localDate1));
//                            Log.d("LocalDate 2 : ", String.valueOf(localDate2));

                            if (localDate1 != null && localDate2 != null){
                                period = Period.between(localDate2,localDate1);
                                final  String bday = period.getDays()+"-"+period.getMonths()+"-"+period.getYears();
//                                Log.d("Final Date : ", bday);
                                if (period.getYears() < 10){
                                    yearSpend.setText("0"+period.getYears());
                                }else{
                                    yearSpend.setText(""+period.getYears());
                                }
                                if (period.getMonths() < 10){
                                    monthSpend.setText("0"+period.getMonths());
                                }else{
                                    monthSpend.setText(""+period.getMonths());
                                }
                                if (period.getDays() < 10){
                                    daySpend.setText("0"+period.getDays());
                                }else {
                                    daySpend.setText(""+period.getDays());
                                }
                            }
                        }else{
                            Log.d("ERROR1 : ", "VERSION PROBLEM");
                        }

                        String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
                        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                        if (diff_in_day0 < 0){
                            nxtBday.setText(" "+diff_in_day+" Days...");
                            day1.setText(""+diff_in_day1+" Days, ("+nd1.getDate()+"-"+months[nd1.getMonth()]+"-20"+(nd1.getYear() - 100)+","+days[nd1.getDay()]+")");
                            day2.setText(""+diff_in_day2+" Days, ("+nd1.getDate()+"-"+months[nd2.getMonth()]+"-20"+(nd2.getYear() - 100)+" ,"+days[nd2.getDay()]+")");
                            day3.setText(""+diff_in_day3+" Days, ("+nd1.getDate()+"-"+months[nd3.getMonth()]+"-20"+(nd3.getYear() - 100)+" ,"+days[nd3.getDay()]+")");
                            day4.setText(""+diff_in_day4+" Days, ("+nd1.getDate()+"-"+months[nd4.getMonth()]+"-20"+(nd4.getYear() - 100)+" ,"+days[nd4.getDay()]+")");
                            day5.setText(""+diff_in_day5+" Days, ("+nd1.getDate()+"-"+months[nd5.getMonth()]+"-20"+(nd5.getYear() - 100)+" ,"+days[nd5.getDay()]+")");
                        }else{
                            nxtBday.setText(" "+diff_in_day0+" Days...");
                            day1.setText(""+diff_in_day0+" Days, ("+nd1.getDate()+"-"+months[nd0.getMonth()]+"-20"+(nd0.getYear() - 100)+" ,"+days[nd0.getDay()]+")");
                            day2.setText(""+diff_in_day1+" Days, ("+nd1.getDate()+"-"+months[nd1.getMonth()]+"-20"+(nd1.getYear() - 100)+","+days[nd1.getDay()]+")");
                            day3.setText(""+diff_in_day2+" Days, ("+nd1.getDate()+"-"+months[nd2.getMonth()]+"-20"+(nd2.getYear() - 100)+" ,"+days[nd2.getDay()]+")");
                            day4.setText(""+diff_in_day3+" Days, ("+nd1.getDate()+"-"+months[nd3.getMonth()]+"-20"+(nd3.getYear() - 100)+" ,"+days[nd3.getDay()]+")");
                            day5.setText(""+diff_in_day4+" Days, ("+nd1.getDate()+"-"+months[nd4.getMonth()]+"-20"+(nd4.getYear() - 100)+" ,"+days[nd4.getDay()]+")");
                        }

//                        daySpend.setText(String.valueOf(diff_in_day));
//                        monthSpend.setText(String.valueOf(diff_in_month));
//                        yearSpend.setText(String.valueOf(diff_in_year));


                    }catch (Exception e){
                        Log.d("ERROR : ", String.valueOf(e));
                        Toast.makeText(MainActivity.this,"ERROR : "+e,Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}