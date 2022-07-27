package com.example.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public EditText username;
    public EditText password;
    public Button login;
    public DBHelper loginDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginDB = new DBHelper(this);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = (EditText) findViewById(R.id.editTextUsername);
                password = (EditText) findViewById(R.id.editTextPassword);

                String name = String.valueOf(username.getText());
                String pass = String.valueOf(password.getText());
                if (name.equals("")||pass.equals("")) {
                    Toast.makeText(Login.this, "Invalid credentials!", Toast.LENGTH_LONG).show();
                } else {
                    Boolean validateCredentials = loginDB.validateCredentials(name, pass);
                    if (validateCredentials) {
                        Toast.makeText(Login.this,"Login successful!", Toast.LENGTH_LONG).show();
                        Intent entryIntent = new Intent(Login.this, Entry.class);
                        startActivity(entryIntent);
                    } else {
                        Toast.makeText(Login.this, "Invalid credentials!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}