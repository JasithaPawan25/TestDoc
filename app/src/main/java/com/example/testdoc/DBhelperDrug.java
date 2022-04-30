package com.example.testdoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelperDrug extends SQLiteOpenHelper {

    public DBhelperDrug( Context context) {
        super(context, "drugdata.db", null, 1);
    }

    @Override
        public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Drugdetails(drugName TEXT primary key, drugQuntity TEXT, drugPrice TExT)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {

        DB.execSQL("drop Table if exists Drugdetails");

        }

        public boolean addDrug(String drugName, String drugQuntity, String drugPrice)
        {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("drugname",drugName);
            contentValues.put("drugquntity",drugQuntity);
            contentValues.put("drugprice",drugPrice);

            long result= DB.insert("Drugdetails",null,contentValues);
            if(result== -1){
                return false;

            }else{
                return true;
            }


        }




    public boolean updateDrug(String drugName, String drugQuntity, String drugPrice)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("drugquntity",drugQuntity);
        contentValues.put("drugprice",drugPrice);

        Cursor cursor = DB.rawQuery("Select * from Drugdetails where  drugName = ?", new String[]{drugName});

        if(cursor.getCount()>0) {

            long result = DB.update("Drugdetails", contentValues, "drugName=?", new String[]{drugName});
            if (result == -1) {
                return false;

            } else {
                return true;
            }

        }else {
            return false;
        }

    }


    public boolean deleteDrug(String drugName)
    {
        SQLiteDatabase DB = this.getWritableDatabase();



        Cursor cursor = DB.rawQuery("Select * from Drugdetails where drugName = ?", new String[]{drugName});

        if(cursor.getCount()>0) {

            long result = DB.delete("Drugdetails",  "drugName=?", new String[]{drugName});
            if (result == -1) {
                return false;

            } else {
                return true;
            }

        }else {

            return false;
        }

    }


    public Cursor getdataDrug()
    {
        SQLiteDatabase DB = this.getWritableDatabase();



        Cursor cursor = DB.rawQuery("Select * from Drugdetails",null);
        return cursor;



    }



    }











