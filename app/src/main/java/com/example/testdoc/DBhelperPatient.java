package com.example.testdoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelperPatient extends SQLiteOpenHelper {
    public DBhelperPatient(@Nullable Context context) {
        super(context, "patientinfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Patientdetails(PatientName TEXT primary key, CheckedDate TEXT, Description TEXT, ApprovedDrug TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Patientdetails");

    }


    public boolean addPatient(String PatientName, String CheckedDate, String Description, String ApprovedDrug)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("patientName",PatientName);
        contentValues.put("checkedDate",CheckedDate);
        contentValues.put("Description",Description);
        contentValues.put("approvedDrug",ApprovedDrug);

        long result= DB.insert("Patientdetails",null,contentValues);
        if(result== -1){
            return false;

        }else{
            return true;
        }


    }



    public boolean deletePatient(String PatientName)
    {
        SQLiteDatabase DB = this.getWritableDatabase();



        Cursor cursor = DB.rawQuery("Select * from Patientdetails where PatientName = ?", new String[]{PatientName});

        if(cursor.getCount()>0) {

            long result = DB.delete("Patientdetails",  "PatientName=?", new String[]{PatientName});
            if (result == -1) {
                return false;

            } else {
                return true;
            }

        }else {

            return false;
        }

    }



    public Cursor viewpatientdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();



        Cursor cursor = DB.rawQuery("Select * from Patientdetails",null);
        return cursor;



    }


}
