package com.swufe.bloodpressure;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateUse = new Date();
    String date = simpleDateFormat.format(dateUse);
    int i = 0;
    final String today_string = simpleDateFormat.format(dateUse);
    String hint;
    TextView welcomeText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("hints", Activity.MODE_PRIVATE);
        SharedPreferences spOpen = PreferenceManager.getDefaultSharedPreferences(this);
        String change = sharedPreferences.getString("date", "");
        welcomeText = findViewById(R.id.welcomeText);

        if(!today_string.equals(change)){

            SharedPreferences sp = getSharedPreferences("hints", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("0", "如有身体不适，请不要认为忍忍就能过去，尽快就医。");
            editor.putString("1","服药请谨遵医嘱。");
            editor.putString("2", "饮食上注意少油少盐，不要吃得过饱。");
            editor.putString("date", date);
            editor.apply();

            Log.i(TAG, "onCreate: 刷新条目");
            String get = Integer.toString(i);
            Log.i(TAG, "onCreate: get = " + get);
            hint = sharedPreferences.getString(get, "");
            welcomeText.setText(hint);
            i = (i + 1) % 3;
        }else{
            Log.i(TAG, "onCreate: 不刷新条目");
            String get = Integer.toString(i);
            hint = sharedPreferences.getString(get, "");
            welcomeText.setText(hint);
        }

        SharedPreferences sp = getSharedPreferences("hints", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("0", "如有身体不适，请不要认为忍忍就能过去，尽快就医。");
        editor.putString("1","服药请谨遵医嘱。");
        editor.putString("2", "饮食上注意少油少盐，不要吃得过饱。");
        editor.putString("date", date);
        editor.apply();

    }

    public void openRecord(View btn){
        Intent recordBP = new Intent(this, recordBP.class);
        startActivity(recordBP);
    }
    public void openList(View btn){
        Intent listBP = new Intent(this, listBP.class);
        startActivity(listBP);
    }
    public void searchForHospital(View btn){
        Intent searchForHospital = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.map.so.com/m/search/map_list/t=map&src=onebox&new=1&dspall=0&c=%E6%88%90%E9%83%BD&keyword=%E5%8C%BB%E9%99%A2"));
        startActivity(searchForHospital);
    }
}
