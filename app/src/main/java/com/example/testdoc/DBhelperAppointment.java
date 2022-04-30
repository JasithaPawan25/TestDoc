package com.example.testdoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelperAppointment extends SQLiteOpenHelper {

    public static final String DBNAME = "appointmentdata.db";

    public DBhelperAppointment(@Nullable Context context) {
        super(context,  "appointmentdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Appointmentdetails(PatientName TEXT primary key, AppointmentNo TEXT, PatientContactNo TEXT , AppointmentTime TEXT, AppointmentDate TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Appointmentdetails");

    }


    public boolean addAppointment(String PatientName, String AppointmentNo, String PatientContactNo, String AppointmentTime, String AppointmentDate )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("patientname",PatientName);
        contentValues.put("appointmentNo",AppointmentNo);
        contentValues.put("patientContactNo",PatientContactNo);
        contentValues.put("appointmentTime",AppointmentTime);
        contentValues.put("appointmentDate",AppointmentDate);

        long result= DB.insert("Appointmentdetails",null,contentValues);
        if(result== -1){
            return false;

        }else{
            return true;
        }


    }



    public boolean updateAppointment(String PatientName, String AppointmentNo, String PatientContactNo, String AppointmentTime, String AppointmentDate )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("appointmentNo",AppointmentNo);
        contentValues.put("patientContactNo",PatientContactNo);
        contentValues.put("appointmentTime",AppointmentTime);
        contentValues.put("appointmentDate",AppointmentDate);

        Cursor cursor = DB.rawQuery("Select * from Appointmentdetails where  PatientName = ?", new String[]{PatientName});

        if(cursor.getCount()>0) {

            long result = DB.update("Appointmentdetails", contentValues, "PatientName=?", new String[]{PatientName});
            if (result == -1) {
                return false;

            } else {
                return true;
            }

        }else {
            return false;
        }

    }




    public boolean deleteAppointment(String PatientName)
    {
        SQLiteDatabase DB = this.getWritableDatabase();



        Cursor cursor = DB.rawQuery("Select * from Appointmentdetails where PatientName = ?", new String[]{PatientName});

        if(cursor.getCount()>0) {

            long result = DB.delete("Appointmentdetails",  "PatientName=?", new String[]{PatientName});
            if (result == -1) {
                return false;

            } else {
                return true;
            }

        }else {

            return false;
        }

    }


    public Cursor viewAppointment()
    {
        SQLiteDatabase DB = this.getWritableDatabase();



        Cursor cursor = DB.rawQuery("Select * from Appointmentdetails",null);
        return cursor;



    }


}
