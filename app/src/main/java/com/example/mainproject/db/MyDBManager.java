package com.example.mainproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDBManager {
    private MyDBHELPER myDBHELPER;
    private SQLiteDatabase db;
    public MyDBManager(Context context) {
        myDBHELPER = new MyDBHELPER(context);
    }
    public void openDB(){
        db = myDBHELPER.getWritableDatabase();
        //очистка таблицы
        //db.delete(MyConstans.TABLE_NAME, null,null);
    }

    public void closeDB(){myDBHELPER.close();}

    public void clearCreated(){
        ContentValues cv = new ContentValues();
        cv.put(MyConstans.BASED, (Integer) null);
        db.update(MyConstans.TABLE_NAME, cv, MyConstans.BASED + " = ?", new String[] {"0"});
    }

    public SubstanceItem GetProduct(Integer unicode){
        if (unicode==null)return null;
        String s = "unicode = ?";
        SubstanceItem substanceItem = null;
        ContentValues cv = new ContentValues();
        cv.put(MyConstans.BASED, 0);
        db.update(MyConstans.TABLE_NAME, cv, MyConstans.UNICODE + " = ?",new String[] {Integer.toString(unicode)});
        Cursor cursor = db.query(MyConstans.TABLE_NAME,null, s,new String[]{Integer.toString(unicode)},null,null,null,null);
        while(cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(MyConstans.TITLE));
            String formula = cursor.getString(cursor.getColumnIndex(MyConstans.FORMULA));
            int unicode1 = cursor.getInt(cursor.getColumnIndex(MyConstans.UNICODE));
            Integer base = cursor.getInt(cursor.getColumnIndex(MyConstans.BASED));
            substanceItem = new SubstanceItem(title,formula, unicode1,base);
        }
        cursor.close();
        return substanceItem;
    }
    public ArrayList<SubstanceItem> GetBasedFromDB(){
        ArrayList<SubstanceItem> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstans.TABLE_NAME, null, MyConstans.BASED + " = ?", new String[] {"1"}, null, null, null);
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(MyConstans.TITLE));
            String formula = cursor.getString(cursor.getColumnIndex(MyConstans.FORMULA));
            int unicode = cursor.getInt(cursor.getColumnIndex(MyConstans.UNICODE));
            Integer base = cursor.getInt(cursor.getColumnIndex(MyConstans.BASED));

            tempList.add(new SubstanceItem(title, formula, unicode, base));
        }
        cursor.close();
        return tempList;
    }
    public ArrayList<SubstanceItem> GetCreatedFromDB(){
        ArrayList<SubstanceItem> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstans.TABLE_NAME, null,MyConstans.BASED + " = ?", new String[] {"0"},null,null,null);
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(MyConstans.TITLE));
            String formula = cursor.getString(cursor.getColumnIndex(MyConstans.FORMULA));
            int unicode = cursor.getInt(cursor.getColumnIndex(MyConstans.UNICODE));
            Integer base = cursor.getInt(cursor.getColumnIndex(MyConstans.BASED));

            tempList.add(new SubstanceItem(title, formula, unicode, base));
        }
        cursor.close();
        return tempList;
    }
}
