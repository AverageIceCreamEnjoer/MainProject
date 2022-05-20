package com.example.mainproject.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RDBManager {
    private RDBHelper rdbHelper;
    private SQLiteDatabase sqLiteDatabase;
    public RDBManager(Context context){
        rdbHelper = new RDBHelper(context);
    }
    public void openRDB(){
        sqLiteDatabase = rdbHelper.getWritableDatabase();}
    public void closeRDB(){rdbHelper.close();}
    public Integer GetReaction(String other_subjects){
        int reactionItems =0;
        String selection = "subjects = ?";
        String[] str = new String[] {other_subjects},str2 = new String[] {MyConstans.PRODUCT};
        Cursor cursor = sqLiteDatabase.query(MyConstans.REACTION_TABLE_NAME,str2,selection, str,null, null, null);
        if (cursor.moveToFirst()){
            int product = cursor.getInt(cursor.getColumnIndex(MyConstans.PRODUCT));
            reactionItems = product;
        } else return null;
        cursor.close();
        return reactionItems;
    }
    public void createReaction(String reogents, int product){
        rdbHelper.insertToRDB(reogents, product,sqLiteDatabase);
    }
}
