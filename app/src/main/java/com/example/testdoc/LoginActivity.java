package com.example.testdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText Uname, Upass;
    Button btnlogin,btnregister;
    DBhelperLogin DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Uname = findViewById(R.id.RName);
        Upass = findViewById(R.id.RPassword);
        btnlogin = findViewById(R.id.btnRe);
        btnregister = findViewById(R.id.btnReg);
        DB = new DBhelperLogin(this);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String user = Uname.getText().toString();
               String pass = Upass.getText().toString();

               if(user.equals("")|| pass.equals("")){
                   Toast.makeText(LoginActivity.this,"Please Fill All Fields", Toast.LENGTH_SHORT).show();

               }else{
                   Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                   if(checkuserpass==true){
                       Toast.makeText(LoginActivity.this,"Login Successfull", Toast.LENGTH_SHORT).show();

                       Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                       startActivity(intent);
                       finish();

                   }
               }


            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),REGActivity.class);
                startActivity(intent);
            }
        });

    }
}