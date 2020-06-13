package com.swufe.bloodpressure;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class listBP extends ListActivity implements AdapterView.OnItemLongClickListener {

    BloodPressureManager manager = new BloodPressureManager(this);
    List<HashMap<String,String>> bloodPressureList = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter listItemAdapter;
    int positionUse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_b_p);

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
        getListView().setOnItemLongClickListener(this);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        positionUse = position;
        Log.i(TAG, "onItemLongClick: 长按" + position);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请问您想执行什么操作？");
        builder.setIcon(R.mipmap.ic_launcher_round);
        //点击对话框以外区域能消失
        builder.setCancelable(true);
        //清空列表
        builder.setNegativeButton("清空列表", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                manager.deleteAll();
                bloodPressureList.clear();
                listItemAdapter.notifyDataSetChanged();
                Log.i(TAG, "onClick: 已清空列表");
                Toast.makeText(getApplicationContext(),"已清空列表",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("删除当前记录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                manager.delete(bloodPressureList.get(position).get("date"));
                bloodPressureList.remove(position);
                listItemAdapter.notifyDataSetChanged();
                Log.i(TAG, "onClick: 已删除当前记录");
                Toast.makeText(getApplicationContext(),"已删除当前记录",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("修改当前记录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent modification = new Intent(getApplicationContext(), BloodPressureModification.class);
                //传递数据
                modification.putExtra("highBP", bloodPressureList.get(position).get("highBP"));
                modification.putExtra("lowBP", bloodPressureList.get(position).get("lowBP"));
                modification.putExtra("heartRate", bloodPressureList.get(position).get("heartRate"));
                modification.putExtra("position", positionUse);
                Log.i(TAG, "onClick: position = " + positionUse);
                Log.i(TAG, "onClick: 参数已传递");
                startActivityForResult(modification, 1);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 2){
            Bundle bundle = data.getExtras();
            //替换原有数据
            assert bundle != null;
            String dateKeep = bloodPressureList.get(bundle.getInt("position")).get("date");

            String highBPInput = bundle.getString("highBPModification");
            String lowBPInput = bundle.getString("lowBPModification");
            String heartRateInput = bundle.getString("heartRateModification");
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("highBP", highBPInput);
            map.put("lowBP", lowBPInput);
            map.put("heartRate", heartRateInput);
            map.put("date", dateKeep);
            Log.i(TAG, "onActivityResult: map = " + map);
            //更新数据库
            BloodPressureItem item = new BloodPressureItem(highBPInput, lowBPInput, heartRateInput, dateKeep);
            manager.update(item);
            //更新列表
            bloodPressureList.set(bundle.getInt("position"), map);
            listItemAdapter.notifyDataSetChanged();
            Log.i(TAG, "onActivityResult: bloodPressureList = " + bloodPressureList);
            Log.i(TAG, "onActivityResult: 修改");
            //修改完成的提示
            Toast.makeText(getApplicationContext(),"已修改当前记录",Toast.LENGTH_SHORT).show();

        }
    }
}