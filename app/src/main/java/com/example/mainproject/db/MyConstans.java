package com.example.mainproject.db;

public class MyConstans {
    public static final String TABLE_NAME = "substances";
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String FORMULA = "formula";
    public static final String DB_NAME = "my_db.db";
    public static int DB_VERSION = 2;
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT," + FORMULA + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
