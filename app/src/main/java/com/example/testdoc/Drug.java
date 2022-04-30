package com.example.testdoc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Drug extends AppCompatActivity {

    EditText dname, dquntity, dprice;
    Button viewbtn,addbtn,editbtn,deletebtn;
    DBhelperDrug DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);

        dname =findViewById(R.id.txtdrName);
        dquntity =findViewById(R.id.txtdQuntity);
        dprice =findViewById(R.id.txtdrprice);
        viewbtn =findViewById(R.id.btnview);
        addbtn =findViewById(R.id.btnadd);
        deletebtn =findViewById(R.id.btndelete);
        editbtn =findViewById(R.id.btnedit);
        DB = new DBhelperDrug(this);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String drugNameTXT = dname.getText().toString();
                String drugQuntityTXT = dquntity.getText().toString();
                String drugPriceTXT = dprice.getText().toString();

                Boolean checkadddrug = DB.addDrug(drugNameTXT,drugQuntityTXT,drugPriceTXT);

                if(checkadddrug==true){

                    Toast.makeText(Drug.this,"New Drug Added", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Drug.this,"New Drug not Added", Toast.LENGTH_SHORT).show();
                }


            }
        });


        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String drugNameTXT = dname.getText().toString();
                String drugQuntityTXT = dquntity.getText().toString();
                String drugPriceTXT = dprice.getText().toString();

                Boolean checkupdatedrug = DB.updateDrug(drugNameTXT,drugQuntityTXT,drugPriceTXT);
                if(checkupdatedrug==true){
                    Toast.makeText(Drug.this,"Drug Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Drug.this,"Drug not Updated", Toast.LENGTH_SHORT).show();
                }


            }
        });



        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String drugNameTXT = dname.getText().toString();


                Boolean checkdeletedrug = DB.deleteDrug(drugNameTXT);
                if(checkdeletedrug==true){
                    Toast.makeText(Drug.this,"Drug Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Drug.this,"Drug not Deleted", Toast.LENGTH_SHORT).show();
                }


            }
        });


        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdataDrug();

                if(res.getCount() == 0){

                 Toast.makeText(Drug.this,"No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Drug Name :" + res.getString(0)+"\n");
                    buffer.append("Drug Quntity :" + res.getString(1)+"\n");
                    buffer.append("Drug Price :" + res.getString(2)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Drug.this);
                builder.setCancelable(true);
                builder.setTitle("Drugs and Quntity ");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });




    }
}