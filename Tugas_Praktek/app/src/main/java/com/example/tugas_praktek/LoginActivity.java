package com.example.tugas_praktek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText username, password;
    private Button btnlogin;
    private TextView register;

    public static final String SHARED_PREF_NAME = "SessionLogin";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btnlogin = findViewById(R.id.btnLogin);
        register = findViewById(R.id.tvRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register.newInstance().show(getSupportFragmentManager(), Register.TAG);
            }
        });

        db = new DatabaseHelper(this);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = username.getText().toString();
                String getPassword = password.getText().toString();

                if (getPassword.isEmpty() && getPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill must be required", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean login = db.checkLogin(getUsername, getPassword);
                    if (login == true) {
                        Boolean updateSession = db.upgradeSession("authorized", 1);
                        if (updateSession == true) {
                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("authorized", true);
                            editor.apply();

                            Intent dashboard =new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(dashboard);
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Username or Password incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}