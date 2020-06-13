package com.swufe.bloodpressure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
