package com.example.mainproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RDBHelper extends SQLiteOpenHelper {
    public RDBHelper(@Nullable Context context) {
        super(context, MyConstans.REACTION_DB_NAME, null, MyConstans.REACTION_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //db_reactions
        sqLiteDatabase.execSQL(MyConstans.REACTION_TABLE_STRUCTURE);
        insertToRDB(".0..1..2.", 5, sqLiteDatabase);
        insertToRDB(".3..5.", 6, sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(MyConstans.REACTION_DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void insertToRDB(String subjects, int product, SQLiteDatabase sqLiteDatabase){
        ContentValues cv = new ContentValues();
        //вещества на поле(в порядке по возрастонию из уникодов в общий стринг)
        cv.put(MyConstans.SUBJECTS, subjects);
        //продукт
        cv.put(MyConstans.PRODUCT, product);
        sqLiteDatabase.insert(MyConstans.REACTION_TABLE_NAME, null, cv);
    }
}
