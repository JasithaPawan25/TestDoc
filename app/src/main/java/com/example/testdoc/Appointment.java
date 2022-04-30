package com.example.testdoc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Appointment extends AppCompatActivity {

    EditText Pname, PIDno,PContact,Appotime,Appodate;
    Button btnadd, btnview,btndelete,btnupdate ;
    DBhelperAppointment DB;
    Calendar calendar=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        Pname = findViewById(R.id.txtappoPname);
        PIDno = findViewById(R.id.txtappoPNum);
        PContact = findViewById(R.id.txtappoPphone);
        Appotime = findViewById(R.id.txtappoPtime);
        Appodate = findViewById(R.id.txtappoPDate);
        btnadd = findViewById(R.id.btnappoAdd);
        btnupdate = findViewById(R.id.btnappoUpdate);
        btnview = findViewById(R.id.btnappoView);
        btndelete = findViewById(R.id.btnappoDelete);
        DB = new DBhelperAppointment(this);




        DatePickerDialog.OnDateSetListener listener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                GetSelectDate();

            }
        };

        Appodate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Appointment.this,listener,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        Appotime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute= calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;

                timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Appotime.setText(hourOfDay +":"+minute);
                    }
                },hour,minute,true);
                timePickerDialog.setTitle("Select Appointment time");
                timePickerDialog.show();




            }
        });






        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String patientNameTXT = Pname.getText().toString();
                String appointmentIDTXT = PIDno.getText().toString();
                String patientContactNoTXT = PContact.getText().toString();
                String appointmenttimeTXT = Appotime.getText().toString();
                String appointmentdateTXT = Appodate.getText().toString();

                Boolean checkaddAppointment = DB.addAppointment(patientNameTXT,appointmentIDTXT,patientContactNoTXT,appointmenttimeTXT,appointmentdateTXT);

                if(checkaddAppointment==true){

                    Toast.makeText(Appointment.this,"New Appointment Added", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Appointment.this,"New Appointment not Added", Toast.LENGTH_SHORT).show();
                }



            }
        });


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String patientNameTXT = Pname.getText().toString();
                String appointmentIDTXT = PIDno.getText().toString();
                String patientContactNoTXT = PContact.getText().toString();
                String appointmenttimeTXT = Appotime.getText().toString();
                String appointmentdateTXT = Appodate.getText().toString();

                Boolean checkupdateAppointment = DB.updateAppointment(patientNameTXT,appointmentIDTXT,patientContactNoTXT,appointmenttimeTXT,appointmentdateTXT);

                if(checkupdateAppointment==true){

                    Toast.makeText(Appointment.this," Appointment Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Appointment.this," Appointment not Updated", Toast.LENGTH_SHORT).show();
                }


            }
        });


        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientNameTXT = Pname.getText().toString();

                Boolean checkdeleteAppointment = DB.deleteAppointment(patientNameTXT);

                if(checkdeleteAppointment==true){

                    Toast.makeText(Appointment.this," Appointment Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Appointment.this," Appointment not Deleted", Toast.LENGTH_SHORT).show();
                }




            }
        });


        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = DB.viewAppointment();

                if(res.getCount() == 0){

                    Toast.makeText(Appointment.this,"No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Patient Name :" + res.getString(0)+"\n");
                    buffer.append("Appoinment No :" + res.getString(1)+"\n");
                    buffer.append("Patient Contact No :" + res.getString(2)+"\n");
                    buffer.append("Appoinment Time :" + res.getString(3)+"\n");
                    buffer.append("Appoinment Date :" + res.getString(4)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Appointment.this);
                builder.setCancelable(true);
                builder.setTitle("Appointments");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }

    private void GetSelectDate()
    {
        String date="dd/MM/yyyy";
        SimpleDateFormat format=new SimpleDateFormat(date, Locale.UK);
        Appodate.setText(format.format(calendar.getTime()));
    }
}