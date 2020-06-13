package com.swufe.bloodpressure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BloodPressureModification extends AppCompatActivity {
    private static final String TAG = "BPModification";
    EditText newHighBP;
    EditText newLowBP;
    EditText newHeartRate;
    int position;

    String highBPModification;
    String lowBPModification;
    String heartRateModification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_modification);

        Log.i(TAG, "onCreate: position = " + getIntent().getIntExtra("position", 0));
        position = getIntent().getIntExtra("position",0);

        String highBP = getIntent().getStringExtra("highBP");
        String lowBP = getIntent().getStringExtra("lowBP");
        String heartRate = getIntent().getStringExtra("heartRate");

        ((EditText)findViewById(R.id.high_BP2)).setText(highBP);
        ((EditText)findViewById(R.id.low_BP2)).setText(lowBP);
        ((EditText)findViewById(R.id.heartRate2)).setText(heartRate);

        newHighBP = findViewById(R.id.high_BP2);
        newLowBP = findViewById(R.id.low_BP2);
        newHeartRate = findViewById(R.id.heartRate2);
    }

    public void modification(View btn){
        highBPModification = newHighBP.getText().toString();
        lowBPModification = newLowBP.getText().toString();
        heartRateModification = newHeartRate.getText().toString();

        if(highBPModification.length() > 0 && lowBPModification.length() > 0 && heartRateModification.length() > 0){
            highBPModification = newHighBP.getText().toString();
            lowBPModification = newLowBP.getText().toString();
            heartRateModification = newHeartRate.getText().toString();

            Intent modification = new Intent(this, listBP.class);
            Bundle bundle = new Bundle();
            bundle.putString("highBPModification", highBPModification);
            bundle.putString("lowBPModification", lowBPModification);
            bundle.putString("heartRateModification", heartRateModification);
            bundle.putInt("position", position);

            modification.putExtras(bundle);
            setResult(2, modification);

            finish();
        }else{
            Toast.makeText(this,"记录不完整，请检查",Toast.LENGTH_SHORT).show();
        }
    }
}

