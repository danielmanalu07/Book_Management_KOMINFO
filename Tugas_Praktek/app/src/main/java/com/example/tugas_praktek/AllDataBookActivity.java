package com.example.tugas_praktek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class AllDataBookActivity extends AppCompatActivity {

    private Button btnBack;
    private DatabaseHelper db;
    private  RecyclerView recyclerView;
    ArrayList<String> Book_Title, Book_Publisher, Book_Page, Book_Year;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data_book);


        btnBack = findViewById(R.id.btnBack);
        db = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.view_data);

        Book_Title = new ArrayList<>();
        Book_Publisher = new ArrayList<>();
        Book_Page = new ArrayList<>();
        Book_Year = new ArrayList<>();

        Cursor cursor = db.ReadAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                Book_Title.add(cursor.getString(1));
                Book_Publisher.add(cursor.getString(2));
                Book_Page.add(cursor.getString(3));
                Book_Year.add(cursor.getString(4));
            }
        }

        customAdapter = new CustomAdapter(getApplicationContext(), Book_Title, Book_Publisher, Book_Page, Book_Year);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backPage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backPage);
                finish();
            }
        });
    }

}

