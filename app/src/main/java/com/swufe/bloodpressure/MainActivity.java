package com.swufe.bloodpressure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
}
