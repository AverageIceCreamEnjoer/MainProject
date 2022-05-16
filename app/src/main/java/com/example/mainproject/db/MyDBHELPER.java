package com.example.mainproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHELPER extends SQLiteOpenHelper {
    public MyDBHELPER(@Nullable Context context){
        super(context, MyConstans.DB_NAME, null, MyConstans.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //db_substances
        sqLiteDatabase.execSQL(MyConstans.TABLE_STRUCTURE);
        insertToSDB("Вода", "H2O", 0, 1, sqLiteDatabase);
        insertToSDB("Углекислый газ", "CO2",1,1, sqLiteDatabase);
        insertToSDB("Аммиак", "NH3",2,1, sqLiteDatabase);
        insertToSDB("Соль","NaCl",3,1, sqLiteDatabase);
        insertToSDB("Гидрокарбонат аммония", "NH4HCO3", 5, null, sqLiteDatabase);
        insertToSDB("Сода", "NaHCO3", 6, null, sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(MyConstans.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void insertToSDB(String title, String formula, int unicode, Integer base, SQLiteDatabase sqLiteDatabase){
        ContentValues cv = new ContentValues();
        //название
        cv.put(MyConstans.TITLE, title);
        //формула
        cv.put(MyConstans.FORMULA, formula);
        //код для реакций
        cv.put(MyConstans.UNICODE, unicode);
        //базовый/созданый/ещё пустой
        cv.put(MyConstans.BASED, base);
        sqLiteDatabase.insert(MyConstans.TABLE_NAME, null, cv);
    }

}
