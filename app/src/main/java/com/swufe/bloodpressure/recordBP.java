package com.swufe.bloodpressure;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class recordBP extends AppCompatActivity {
    private static final String TAG = "recordBP";
    EditText high_BP;
    EditText low_BP;
    EditText heart_Rate;
    Date datenow = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_b_p);

        high_BP = (EditText)findViewById(R.id.high_BP);
        low_BP = (EditText)findViewById(R.id.low_BP);
        heart_Rate = (EditText)findViewById(R.id.heartRate);

    }

    public void record(View btn){
        String highBP = high_BP.getText().toString();
        String lowBP = low_BP.getText().toString();
        String heartRate = heart_Rate.getText().toString();
        String date = sdf.format(datenow);

        if(highBP.length() > 0 && lowBP.length() > 0 && heartRate.length() > 0){
            Log.i(TAG, "record: highBP = " + highBP);
            Log.i(TAG, "record: lowBP = " + lowBP);
            Log.i(TAG, "record: heartRate = " + heartRate);
            Log.i(TAG, "record: time = " + sdf.format(datenow));
            //写入记录
            List<BloodPressureItem> bloodPressure = new ArrayList<BloodPressureItem>();
            bloodPressure.add(new BloodPressureItem(highBP, lowBP, heartRate, date));

            BloodPressureManager manager = new BloodPressureManager(this);
//            manager.deleteAll();
            manager.add(bloodPressure.get(0));

            Log.i(TAG, "record: 写入完毕");

            Toast.makeText(this,"已记录",Toast.LENGTH_SHORT).show();
            finish();

        }else{
            Toast.makeText(this,"记录不完整，请检查",Toast.LENGTH_SHORT).show();
        }
    }
}