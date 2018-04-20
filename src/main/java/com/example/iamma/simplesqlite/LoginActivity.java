package com.example.iamma.simplesqlite;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SQLiteOpenHelper dHelper;
    SQLiteDatabase db;
    Cursor cursor;

    EditText email,pass;
    Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.editEmail);
        pass = (EditText)findViewById(R.id.editPassword);

        // opening sqlite
        dHelper = new DatabaseHelper(this);
        db = dHelper.getReadableDatabase();

        btnLog = (Button)findViewById(R.id.btnLogin);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = email.getText().toString();
                String b = pass.getText().toString();

                cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLENAME+" WHERE "+DatabaseHelper.COL3+"=? AND "+DatabaseHelper.COL4+" =? ", new String [] {a,b}  );

                if(cursor!= null)
                {
                    if(cursor.getCount()>0)
                    {
                        cursor.moveToFirst();
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("Alert");
                        builder.setMessage("Email or Password is wrong.");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        //-------Alert Dialog Code Snippet End Here
                    }
                }
            }
        });
    }
}
