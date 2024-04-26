package com.example.tugas_praktek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title1, publisher1, page1, year1;
    Button update_btn;
    String title, publisher, page, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title1 = findViewById(R.id.title_txt);
        publisher1 = findViewById(R.id.Publish_txt);
        page1 = findViewById(R.id.Page_txt);
        year1 = findViewById(R.id.Year_txt);
        update_btn = findViewById(R.id.update_btn);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        getAndSetIntent();
        DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
        db.UpdateData(title, publisher, page, year);
    }
    public void getAndSetIntent(){
        if (getIntent().hasExtra("title") && getIntent().hasExtra("publisher") && getIntent().hasExtra("page") && getIntent().hasExtra("year")) {
            title = getIntent().getStringExtra("title");
            publisher = getIntent().getStringExtra("publisher");
            page = getIntent().getStringExtra("page");
            year = getIntent().getStringExtra("year");

            title1.setText(title);
            publisher1.setText(publisher1);
            page1.setText(page);
            year1.setText(year);
        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}