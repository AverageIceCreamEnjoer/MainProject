package com.example.mainproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDBManager {
    private Context context;
    private MyDBHELPER myDBHELPER;
    private SQLiteDatabase db;
    public MyDBManager(Context context) {
        this.context = context;
        myDBHELPER = new MyDBHELPER(context);
    }
    public void openDB(){
        db = myDBHELPER.getWritableDatabase();
        //очистка таблицы
        //db.delete(MyConstans.TABLE_NAME, null,null);
        //создание таблицы
        createDB();
    }
    public void createDB(){
        Cursor cursor = db.query(MyConstans.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.getCount()<1){
            insertToDB("Вода", "H2O");
            insertToDB("Углекислый газ", "CO2");
            insertToDB("Аммиак", "NH3");
            insertToDB("Соль","NaCl");
        }
        cursor.close();
    }
    public void insertToDB(String title, String formula){
        ContentValues cv = new ContentValues();
        cv.put(MyConstans.TITLE, title);
        cv.put(MyConstans.FORMULA, formula);
        db.insert(MyConstans.TABLE_NAME, null, cv);
    }
    public void closeDB(){myDBHELPER.close();}
    public ArrayList<SubstanceItem> GetFromDB(){
        ArrayList<SubstanceItem> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstans.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(MyConstans.TITLE));
            String formula = cursor.getString(cursor.getColumnIndex(MyConstans.FORMULA));
            tempList.add(new SubstanceItem(title, formula));
        }
        cursor.close();
        return tempList;
    }
}
