package com.example.mainproject.db;

public class MyConstans {
    //db_substances
    public static final String TABLE_NAME = "based_substances";
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String FORMULA = "formula";
    public static final String DB_NAME = "my_db.db";
    public static final String UNICODE = "unicode";
    public final static String BASED = "based";
    public static int DB_VERSION = 4;
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT," + FORMULA + " TEXT," + UNICODE + " INTEGER," + BASED + " INTEGER)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    //db_reactions
    public static final String REACTION_TABLE_NAME = "reactions";
    public static final String REACTION_DB_NAME = "re_db.db";
    public static final String REACTION_ID = "_id";
    public static final String PRODUCT = "product";
    public static final String SUBJECTS = "subjects";
    public static final int REACTION_DB_VERSION = 1;
    public static final String REACTION_TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            REACTION_TABLE_NAME + " (" + REACTION_ID + " INTEGER PRIMARY KEY,"+
            PRODUCT + " INTEGER," + SUBJECTS + " TEXT)";
    public static final String REACTION_DROP_TABLE = "DROP TABLE IF EXISTS " + REACTION_TABLE_NAME;

}
