package com.bars.topilskiyanton.imedsister.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Topilskiy Anton on 22.02.2018.
 */

public class DB extends SQLiteOpenHelper {

    final String LOG_TAG = "myLogs";

    private static DB instance;

    private DB() {
        super(null, null, null, 1);
    }

    private DB(Context context) {
        // конструктор суперкласса

        super(context, "myDB", null, 1);
    }

    public static synchronized DB getInstance(Context context) {
        if (instance == null) {
            instance = new DB(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Создание таблицы палат
        sqLiteDatabase.execSQL("create table Wards ("
                + "id integer primary key autoincrement,"
                + "number integer,"
                + "quanity integer,"
                + "capacity integer,"
                + "gender text" + ");");

        // Создание таблицы пациентов
        sqLiteDatabase.execSQL("create table Patients ("
                + "id integer primary key autoincrement,"
                + "surname text,"
                + "name text,"
                + "secname text,"
                + "dateOfBirth NUMERIC,"
                + "adress text,"
                + "gender text,"
                + "diagnosis text,"
                + "doc integer,"
                + "docObservers integer,"
                + "procedures integer,"
                + "status text,"
                + "history integer,"
                + "information text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void update(String nameTable) {

    }

    public boolean add(String nameTable, ContentValues cv) {
        Log.d("myLogs", "ddd" + cv.get("number").toString());
        SQLiteDatabase dbCNKT = getWritableDatabase();
        dbCNKT.insert(nameTable, null, cv);
        close();
        return true;
    }

    public void closeDB() {
        close();
        instance = null;
    }
}
