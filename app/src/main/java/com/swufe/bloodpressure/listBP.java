package com.swufe.bloodpressure;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class listBP extends ListActivity {

    private SimpleAdapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_b_p);

        List<HashMap<String,String>> bloodPressureList = new ArrayList<HashMap<String, String>>();

        BloodPressureManager manager = new BloodPressureManager(this);
        for(BloodPressureItem item: manager.listAll()){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("highBP", item.getHighBP());
            map.put("lowBP", item.getLowBP());
            map.put("heartRate", item.getHeartRate());
            map.put("date", item.getDate());
            Log.i(TAG, "run: map = " + map);

            bloodPressureList.add(map);
            Log.i(TAG, "run: bloodPressureList = " + bloodPressureList);
        }

        listItemAdapter = new SimpleAdapter(listBP.this, bloodPressureList,
                R.layout.list_item,
                new String[]{"highBP", "lowBP", "heartRate", "date"},
                new int[]{R.id.showHighBP, R.id.showLowBP, R.id.showHeartRate, R.id.showDate}
        );
        setListAdapter(listItemAdapter);
        this.setListAdapter(listItemAdapter);
        Log.i(TAG, "run: 展示完毕");

    }








}