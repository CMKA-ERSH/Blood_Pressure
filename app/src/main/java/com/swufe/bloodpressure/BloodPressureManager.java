package com.swufe.bloodpressure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public BloodPressureManager(Context context){
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(BloodPressureItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("highbp", item.getHighBP());
        values.put("lowbp", item.getLowBP());
        values.put("heartrate", item.getHeartRate());
        values.put("date", item.getDate());
        db.insert(TBNAME, null, values);
        db.close();
    }
    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, null, null);
        db.close();
    }
    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void update(BloodPressureItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("highbp", item.getHighBP());
        values.put("lowbp", item.getLowBP());
        values.put("heartrate", item.getHeartRate());
        values.put("date", item.getDate());
        db.update(TBNAME, values, "ID=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }
    public List<BloodPressureItem> listAll(){
        List<BloodPressureItem> bloodPressureList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null,null,null);
        if(cursor != null){
            bloodPressureList = new ArrayList<BloodPressureItem>();
            while(cursor.moveToNext()){
                BloodPressureItem item = new BloodPressureItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setHighBP(cursor.getString(cursor.getColumnIndex("HIGHBP")));
                item.setLowBP(cursor.getString(cursor.getColumnIndex("LOWBP")));
                item.setHeartRate(cursor.getString(cursor.getColumnIndex("HEARTRATE")));
                item.setDate(cursor.getString(cursor.getColumnIndex("DATE")));

                bloodPressureList.add(item);
            }
            cursor.close();
        }
        db.close();
        return bloodPressureList;
    }

}
