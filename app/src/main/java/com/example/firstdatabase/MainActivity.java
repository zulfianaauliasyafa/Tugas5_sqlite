package com.example.firstdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, nim;
    Button insert, view;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       name = findViewById(R.id.name);
       nim = findViewById(R.id.nim);
       insert = findViewById(R.id.BtnInsert);
       view = findViewById(R.id.BtnView);

//        rror while executing: 'am start -n "com.example.firstdatabase/com.example.firstdatabase.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER'
//        Error while executing: 'am start -n "com.example.firstdatabase/com.example.firstdatabase.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER'
       DB = new DBHelper(this);

       view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, Userlist.class));
           }
       });

       insert.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              String nameTXT = name.getText().toString();
              String nimTXT = nim.getText().toString();

              Boolean checkinsertdata = DB.insertuserdata(nameTXT,nimTXT);
              if(checkinsertdata==true){
                  Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();

              }
              else{
                  Toast.makeText(MainActivity.this, "New Entry not Inserted", Toast.LENGTH_SHORT).show();

              }
           }
       });

    }
}