package com.example.testdoc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Billchecker extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> data1 = new ArrayList<String>();
    private ArrayList<String> data2 = new ArrayList<String>();
    private ArrayList<String> data3 = new ArrayList<String>();

    private TableLayout table;



    EditText dname,dquntity,dprice, bsTotal,bAdditional,bTotal;
    Button btnadddrug,btntotal,btnforget;
    DBhelperDrug DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billchecker);


        dname = findViewById(R.id.txtbdrugname);
        dquntity = findViewById(R.id.txtbdrugquntity);
        dprice = findViewById(R.id.txtbdrugprice);
        bsTotal = findViewById(R.id.txtbsubbill);
        bAdditional = findViewById(R.id.txtbadditional);
        bTotal = findViewById(R.id.txtbtotal);

        btnforget = findViewById(R.id.btnforgetdrugs);
        btnadddrug = findViewById(R.id.btnbilAdd);
        btntotal = findViewById(R.id.btnbilltotal);
        DB = new DBhelperDrug(this);




        btnforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = DB.getdataDrug();

                if(res.getCount() == 0){

                    Toast.makeText(Billchecker.this,"No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Drug Name :" + res.getString(0)+"\n");
                    buffer.append("Drug Quntity :" + res.getString(1)+"\n");
                    buffer.append("Drug Price :" + res.getString(2)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Billchecker.this);
                builder.setCancelable(true);
                builder.setTitle("Drugs and Quntity ");
                builder.setMessage(buffer.toString());
                builder.show();




            }
        });





        btnadddrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               add();




            }
        });

        btntotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int subtotal = Integer.parseInt(bsTotal.getText().toString());
                int additional = Integer.parseInt(bAdditional.getText().toString());
                int bal =  additional + subtotal;
                bTotal.setText(String.valueOf(bal));

            }
        });


      /* bAdditional.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int subtotal = Integer.parseInt(bsTotal.getText().toString());
                int additional = Integer.parseInt(bAdditional.getText().toString());
                int bal =  additional + subtotal;
                bTotal.setText(String.valueOf(bal));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/



    }


    public void add()

    {
        int tot;

        String prodname = dname.getText().toString();
        int price = Integer.parseInt(dprice.getText().toString());
        int qty = Integer.parseInt(dquntity.getText().toString());
        tot = price * qty;

        data.add(prodname);
        data1.add(String.valueOf(price));
        data2.add(String.valueOf(qty));
        data3.add(String.valueOf(tot));

        TableLayout table = (TableLayout) findViewById(R.id.tb1);

        TableRow row = new TableRow(this);
        TextView t1 = new TextView(this);
        TextView t2 = new TextView(this);
        TextView t3 = new TextView(this);
        TextView t4 = new TextView(this);

        String total;

        int sum = 0;

        for(int i = 0; i< data.size(); i ++)
        {

            String pname = data.get(i);
            String prc = data1.get(i);
            String qtyy = data2.get(i);
            total = data3.get(i);

            t1.setText(pname);
            t2.setText(prc);
            t3.setText(qtyy);
            t4.setText(total);

            sum = sum + Integer.parseInt(data3.get(i).toString());
        }

        row.addView(t1);
        row.addView(t2);
        row.addView(t3);
        row.addView(t4);
        table.addView(row);


        bsTotal.setText(String.valueOf(sum));
        dname.setText("");
        dprice.setText("");
        dquntity.setText("");
        dname.requestFocus();

    }


}