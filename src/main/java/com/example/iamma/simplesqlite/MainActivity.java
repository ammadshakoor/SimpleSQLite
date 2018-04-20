package com.example.iamma.simplesqlite;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText user,pass,email;
    Button btnReg,btnLog;

    DatabaseHelper mydb;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mydb = new DatabaseHelper(this);

        user = (EditText)findViewById(R.id.editTextName);
        email = (EditText)findViewById(R.id.editTextEmail);
        pass = (EditText)findViewById(R.id.editTextPassword);

        btnReg = (Button)findViewById(R.id.buttonReg);
        btnLog = (Button)findViewById(R.id.btnLog);
/*
        String a = user.getText().toString();
        String b = email.getText().toString();
        String c = pass.getText().toString();*/

        addNewUser();
        login();
    }

    public void addNewUser()
    {
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = mydb.InsertData(user.getText().toString(),email.getText().toString(),pass.getText().toString());
                if(isInserted==true)
                {
                    Toast.makeText(getBaseContext(),"Data is inserted", Toast.LENGTH_SHORT).show();
                    user.setText(null);
                    email.setText(null);
                    pass.setText(null);
                    // if data inserted correctly proceed to login page...
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getBaseContext(), "Data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void login()
    {
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
