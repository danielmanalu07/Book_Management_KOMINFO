package com.example.tugas_praktek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class InformasiActivity extends AppCompatActivity {

    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);

        TextView textView = findViewById(R.id.teks_data);

        String teks = "Aplikasi kampusku merupakan aplikasi yang\n" +
                "digunakan untuk pendaftaran mahasiswa.\n" +
                "User nantinya bisa menambahkan, update, dan hapus data mahasiswa";

        textView.setText(teks);

        btnBack = findViewById(R.id.backBtn);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
                finish();
            }
        });


    }
}