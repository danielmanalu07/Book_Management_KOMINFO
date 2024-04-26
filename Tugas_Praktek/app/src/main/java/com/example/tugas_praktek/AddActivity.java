package com.example.tugas_praktek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText title, publisher, year, page;
    private Button addBtn, back;
    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        db = new DatabaseHelper(this);
        title = findViewById(R.id.inputTitle);
        publisher = findViewById(R.id.inputPubliser);
        page = findViewById(R.id.inputPage);
        year = findViewById(R.id.inputYear);
        addBtn = findViewById(R.id.addBtn);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backPage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backPage);
                finish();
            }
        });

        addData();
    }

    public void addData(){
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intitle = title.getText().toString();
                String inpublisher = publisher.getText().toString();
                String inpage = page.getText().toString();
                String inyear = year.getText().toString();
                boolean insert = db.InsertDataBook(intitle, inpublisher, inpage, inyear);
                if (insert == true) {
                    Toast.makeText(getApplicationContext(), "Data Berhasil DiTambahkan", Toast.LENGTH_SHORT).show();
                    Intent back = new Intent(AddActivity.this, AllDataBookActivity.class);
                    startActivity(back);
                    finish();
                } else{
                    Toast.makeText(getApplicationContext(), "Data Gagal DiTambahkan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}