package com.example.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Entry extends AppCompatActivity {

    public Button newEntry;
    public Button viewEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        newEntry = (Button) findViewById(R.id.makeNewEntry);
        viewEntry = (Button) findViewById(R.id.viewEntries);

        newEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(Entry.this, Info.class);
                startActivity(infoIntent);
            }
        });
    }
}