package com.example.testdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class REGActivity extends AppCompatActivity {

    EditText Rname, password,cpassword;
    Button btnlogin;
    DBhelperLogin DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regactivity);

        Rname = findViewById(R.id.RName);
        password = findViewById(R.id.RPassword);
        cpassword = findViewById(R.id.RCPassword);
        btnlogin =findViewById(R.id.btnRe);
        DB = new DBhelperLogin(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Rname.getText().toString();
                String pass = password.getText().toString();
                String repass = cpassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")){

                    Toast.makeText(REGActivity.this,"Please Fill All Fields", Toast.LENGTH_SHORT).show();

                }else {

                    if (pass.equals(repass)){

                        Boolean checkuser = DB.checkusername(user);

                        if (checkuser==false){

                            Boolean insert= DB.insertdata(user,pass);

                            if(insert==true){
                                Toast.makeText(REGActivity.this,"Registered Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        }
                    }
                }

            }
        });




    }
}