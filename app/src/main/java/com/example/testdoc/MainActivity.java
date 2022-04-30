package com.example.testdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnprss, btnpress2, btnappoint, btnpatient,btnlout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnprss = findViewById(R.id.btnpress);
        btnpress2 = findViewById(R.id.btnpress2);
        btnpatient = findViewById(R.id.btnhomepatient);
        btnappoint = findViewById(R.id.btnappoint);
        btnlout = findViewById(R.id.btnLogout);

        btnprss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Drug.class);
                startActivity(intent);


            }
        });

        btnlout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Successfully Logout", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }
        });



        btnpress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Billchecker.class);
                startActivity(intent);

            }
        });


       btnappoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Appointment.class);
                startActivity(intent);

            }
        });

        btnpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Patient.class);
                startActivity(intent);

            }
        });


    }
}