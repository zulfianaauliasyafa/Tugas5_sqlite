package com.example.firstdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Userlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> name, nim;
    DBHelper DB;
    MyAdapter adapter;
    private String selectedName = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        DB = new DBHelper(this);
        name = new ArrayList<>();
        nim = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,name,nim);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();

        Button btnBack = findViewById(R.id.stickyButton);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buat Intent untuk kembali ke CalculatorActivity
                Intent intent = new Intent(Userlist.this, MainActivity.class);
                startActivity(intent);
                finish(); // Tutup ResultActivity
            }
        });


        Button btnDelete = findViewById(R.id.hapus); // Pastikan tombol delete ada di layout
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedName != null) {
                    // Hapus data berdasarkan nama yang dipilih
                    boolean isDeleted = DB.deletedata(selectedName);

                    if (isDeleted) {
                        Toast.makeText(Userlist.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                        // Refresh RecyclerView setelah penghapusan
                        name.clear();
                        nim.clear();
                        displaydata(); // Memanggil ulang data
                        adapter.notifyDataSetChanged(); // Beritahu adapter untuk update data
                        selectedName = null; // Reset selected item
                    } else {
                        Toast.makeText(Userlist.this, "Failed to Delete", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Userlist.this, "No Card Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }

    private void displaydata() {

        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0){
            Toast.makeText(Userlist.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while(cursor.moveToNext()){
                name.add(cursor.getString(0));
                nim.add(cursor.getString(1));
            }
        }
    }

    public void onCardClicked(String name) {
        // Simpan nama dari card yang di-click
        selectedName = name;
    }






}