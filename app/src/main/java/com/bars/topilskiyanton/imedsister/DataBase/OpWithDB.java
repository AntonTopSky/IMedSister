package com.bars.topilskiyanton.imedsister.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bars.topilskiyanton.imedsister.ListWards.ItemWard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Topilskiy Anton on 01.03.2018.
 */

public class OpWithDB {
    DB db;
    SQLiteDatabase dbCNKT;
    private static OpWithDB instance;

    private OpWithDB() {

    }

    private OpWithDB(Context context) {
        db = DB.getInstance(context);
        dbCNKT = db.getWritableDatabase();
    }

    public static synchronized OpWithDB getInstance(Context context) {
        if (instance == null) {
            instance = new OpWithDB(context);
        }
        return instance;
    }

    private String getId(String nameTable, String namefield, String value) {
        Cursor c = dbCNKT.query(nameTable, null, namefield + " = " + value, null, null, null, null);
        c.moveToFirst();
        int idWardColIndex = c.getColumnIndex("id");
        String id = String.valueOf(c.getInt(idWardColIndex));
        c.close();
        return id;
    }


    public boolean checkOfUniqueness(String nameTable, String namefield, String value) {
        Cursor c = dbCNKT.query(nameTable, null, namefield + " = " + value, null, null, null, null);
        boolean result = !c.moveToFirst();
        c.close();
        return result;
    }

    public boolean updateField(String nameTable, ContentValues cv, String firstEdit) {
        if (nameTable.equals("Wards")) {
            if (checkOfUniqueness(nameTable, "number", cv.get("number").toString()) || firstEdit.equals(cv.get("number").toString())) {
                String id = getId(nameTable, "number", firstEdit);
                dbCNKT.update(nameTable, cv,"id =" + id, null);
                return true;
            }
        }
        return false;
    }

    public boolean addField(String nameTable, ContentValues cv) {

        if (nameTable.equals("Wards")) {
            if (checkOfUniqueness(nameTable, "number", cv.get("number").toString())) {
                dbCNKT.insert(nameTable, null, cv);
                return true;
            }
        }
        return false;
    }

    public boolean removeField(String nameTable, String namefield, String value) {
        dbCNKT.delete(nameTable, namefield + " = " + value, null);
        return true;
    }

    public List<Object> getAllFieldFromTable(String nameTable) {
        String orderBy = "";
        return getAllFieldFromTable(nameTable, orderBy);

    }

    public List<Object> getAllFieldFromTable(String nameTable, String orderBy) {
        List<Object> listObject = new ArrayList<Object>();
        Cursor c;

        if (nameTable.equals("Wards")) {
            ItemWard itemWard;
            List<Object> listItemWards = new ArrayList<>();
            if (orderBy.isEmpty()) {
                c = dbCNKT.query(nameTable, null, null, null, null, null, null);
            } else {
                c = dbCNKT.query(nameTable, null, null, null, null, null, orderBy);
            }
            if (c.moveToFirst()) {

                int numberWardColIndex = c.getColumnIndex("number");
                int quanityColIndex = c.getColumnIndex("quanity");
                int genderColIndex = c.getColumnIndex("gender");
                int capacityColIndex = c.getColumnIndex("capacity");
                do {
                    listItemWards.add(new ItemWard(String.valueOf(c.getInt(numberWardColIndex)),
                            String.valueOf(c.getInt(quanityColIndex)),
                            String.valueOf(c.getInt(capacityColIndex)),
                            c.getString(genderColIndex)));
                } while (c.moveToNext());
                c.close();
            }
            return listItemWards;
        }


        return listObject;
    }

    public void close() {
        db.closeDB();
    }


}
