package com.example.testdoc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Patient extends AppCompatActivity {

    EditText pname,checkeddate,descrip,approvedDrug;
    Button btnadd, btndelete, btnview;
    DBhelperPatient DB;
    Calendar calendar=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        pname =findViewById(R.id.txtPinfoname);
        checkeddate =findViewById(R.id.txtPinfodate);
        descrip  =findViewById(R.id.txtPinfoDescrip);
        approvedDrug =findViewById(R.id.txtPinfosDrugs);
        btnadd =findViewById(R.id.btnPinfoAdd);
        btndelete =findViewById(R.id.btnPinfodelete);
        btnview =findViewById(R.id.btnPinfoView);
        DB = new DBhelperPatient(this);


        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH,month);
                    calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                    GetSelectDate();
            }
        };

        checkeddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Patient.this,listener,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });








        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String patientNameTXT = pname.getText().toString();
                String checkedDateTXT = checkeddate.getText().toString();
                String descriptionTXT = descrip.getText().toString();
                String approvedDrugTXT = approvedDrug.getText().toString();

                Boolean checkaddpatient= DB.addPatient(patientNameTXT,checkedDateTXT,descriptionTXT,approvedDrugTXT);

                if(checkaddpatient==true){

                    Toast.makeText(Patient.this,"New Patient Added", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Patient.this,"New Patient not Added", Toast.LENGTH_SHORT).show();
                }


            }
        });


        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String patientNameTXT = pname.getText().toString();

                Boolean checkdeletepatient = DB.deletePatient(patientNameTXT);
                if(checkdeletepatient==true){
                    Toast.makeText(Patient.this,"Patient Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Patient.this,"Patient not Deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = DB.viewpatientdata();

                if(res.getCount() == 0){

                    Toast.makeText(Patient.this,"No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Patient Name :" + res.getString(0)+"\n");
                    buffer.append("Checked Date :" + res.getString(1)+"\n");
                    buffer.append("Descripton :" + res.getString(2)+"\n");
                    buffer.append("Approved Drugs :" + res.getString(3)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Patient.this);
                builder.setCancelable(true);
                builder.setTitle("Patient Infomation");
                builder.setMessage(buffer.toString());
                builder.show();

            }


        });


    }


    private void GetSelectDate()
    {
        String date="dd/MM/yyyy";
        SimpleDateFormat format=new SimpleDateFormat(date, Locale.UK);
        checkeddate.setText(format.format(calendar.getTime()));
    }
}