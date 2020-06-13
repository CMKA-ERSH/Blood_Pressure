package com.swufe.bloodpressure;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
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

    public void record(View btn) throws InterruptedException {
        String highBP = high_BP.getText().toString();
        String lowBP = low_BP.getText().toString();
        String heartRate = heart_Rate.getText().toString();
        String date = sdf.format(datenow);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

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



            Toast.makeText(this,"已记录",Toast.LENGTH_SHORT).show();
            if(Integer.parseInt(highBP) > 130 && Integer.parseInt(lowBP) > 80){
                builder.setTitle("注意").setMessage("您目前处于高血压状态，若身体不适或长期处于此状态下请立刻就医");
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }else if(Integer.parseInt(highBP) < 90 && Integer.parseInt(lowBP) < 60){
                builder.setTitle("注意").setMessage("您目前处于低血压状态，若身体不适或长期处于此状态下请立刻就医");
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }else if(Integer.parseInt(heartRate) > 100){
                builder.setTitle("注意").setMessage("您目前心率过快，若身体不适或长期处于此状态下请立刻就医");
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }else if(Integer.parseInt(heartRate) < 60){
                builder.setTitle("注意").setMessage("您目前心率过慢，若身体不适或长期处于此状态下请立刻就医");
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                finish();
            }
            Log.i(TAG, "record: 写入完毕");
        }else{
            Toast.makeText(this,"记录不完整，请检查",Toast.LENGTH_SHORT).show();
        }
    }
}