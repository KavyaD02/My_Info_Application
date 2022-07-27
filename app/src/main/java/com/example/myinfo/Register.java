package com.example.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    public EditText username;
    public EditText password;
    public EditText re_password;
    public Button sign_up;
    public DBHelper loginDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginDB = new DBHelper(Register.this);

        sign_up = (Button) findViewById(R.id.signup);
        sign_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                username = (EditText) findViewById(R.id.editTextUsername1);
                password = (EditText) findViewById(R.id.editTextPassword1);
                re_password = (EditText) findViewById(R.id.editTextRepeatPassword);

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String re_pass = re_password.getText().toString();

                if (user.equals("")||pass.equals("")||re_pass.equals("")) {
                    String response = "Invalid credentials!";
                    Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                } else {
                    if (pass.equals(re_pass)) {
                        Boolean insert = loginDB.insertData(user, pass);
                        if (insert) {
                            Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_LONG).show();
                            Intent entryIntent = new Intent(Register.this, Entry.class);
                            startActivity(entryIntent);
                        } else {
                            Toast.makeText(Register.this, "Registration failed!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "Passwords do not match!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}