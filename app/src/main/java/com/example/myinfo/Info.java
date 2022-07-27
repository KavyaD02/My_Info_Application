package com.example.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Info extends AppCompatActivity {

    private Button enter;
    private EditText username;
    private EditText password;
    public DBHelper infoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        infoDB = new DBHelper(Info.this);

        enter = (Button) findViewById(R.id.entry);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = (EditText) findViewById(R.id.editTextUsername2);
                password = (EditText) findViewById(R.id.editTextPassword2);

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("")||pass.equals("")) {
                    String response = "Invalid entry!";
                    Toast.makeText(Info.this, response, Toast.LENGTH_LONG).show();
                } else {
                    Boolean insert = infoDB.insertData(user, pass);
                    if (insert) {
                        Toast.makeText(Info.this, "Entry successful!", Toast.LENGTH_LONG).show();
                        Intent entryIntent = new Intent(Info.this, Entry.class);
                        startActivity(entryIntent);
                    } else {
                        Toast.makeText(Info.this, "Entry failed!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}