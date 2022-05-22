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

    public SubstanceItem GetProduct(Integer unicode, boolean based){
        if (unicode==null)return null;
        String s = "unicode = ?";
        SubstanceItem substanceItem = null;
        if (based) {
            ContentValues cv = new ContentValues();
            cv.put(MyConstans.BASED, 0);
            db.update(MyConstans.TABLE_NAME, cv, MyConstans.UNICODE + " = ? AND " + MyConstans.BASED + " IS NULL", new String[]{Integer.toString(unicode)});
        }
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
    public boolean CreateProduct(String name, String formula){
        Cursor cursor = db.query(MyConstans.TABLE_NAME, new String[] {MyConstans.TITLE, MyConstans._ID}, MyConstans.TITLE + " = ? OR " + MyConstans.FORMULA+" = ?",new String[] {name, formula}, null, null, null);
        if (!cursor.moveToFirst()){
            cursor = db.query(MyConstans.TABLE_NAME, null,null,null,null,null,null);
            myDBHELPER.insertToSDB(name,formula,cursor.getCount()+1, 1, db);
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }
    public boolean isExist(String name){
        Cursor cursor = db.query(MyConstans.TABLE_NAME, new String[] {MyConstans.TITLE}, MyConstans.TITLE + " = ?" , new String[] {name},null,null,null);
        if (cursor.moveToFirst()){
            cursor.close();
            return true;
        } else{
            cursor.close();
            return true;
        }
    }
    public Integer getUnicode(String name){
        Cursor cursor = db.query(MyConstans.TABLE_NAME, null, MyConstans.TITLE + " = ? OR " + MyConstans.FORMULA+" = ?",new String[] {name,name}, null,null, null);
        if (cursor.moveToFirst()){
            Integer k = null;
            k = cursor.getInt(cursor.getColumnIndex(MyConstans.UNICODE));
            cursor.close();
            return k;
        } else {
            cursor.close();
            return null;
        }
    }
}
