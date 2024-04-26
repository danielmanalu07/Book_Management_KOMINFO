package com.example.tugas_praktek;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public  static final String SHARED_PREF_NAME = "SessionLogin";

    private SharedPreferences sharedPreferences;

    private TextView nameText;

    private Button btnLogout, inputData, lihatData, informasi;

    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        sharedPreferences =getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);


        Boolean isLoggedIn = db.checkSession("authorized");
        if (isLoggedIn == false){
            Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(logout);
            finish();
        }

        btnLogout = findViewById(R.id.Logout);
        inputData = findViewById(R.id.inputData);
        lihatData = findViewById(R.id.readData);
        informasi = findViewById(R.id.informasi);



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updateSession =db.upgradeSession("unauthorized", 1);
                if (updateSession == true) {
                    Toast.makeText(getApplicationContext(), "Logout Successfully", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("authorized", false);
                    editor.apply();

                    Intent logout =new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(logout);
                    finish();
                }
            }
        });

        inputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(add);
                finish();
            }
        });

        lihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent read = new Intent(getApplicationContext(), AllDataBookActivity.class);
                startActivity(read);
                finish();
            }
        });

        informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(getApplicationContext(), InformasiActivity.class);
                startActivity(info);
                finish();
            }
        });
    }
}