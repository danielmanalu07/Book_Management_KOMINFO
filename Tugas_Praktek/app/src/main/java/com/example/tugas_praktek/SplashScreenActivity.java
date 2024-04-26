package com.example.tugas_praktek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(view.SYSTEM_UI_FLAG_FULLSCREEN);
        progressBar =findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                SharedPreferences sharedPreferences =getSharedPreferences(LoginActivity.SHARED_PREF_NAME, 0);
                Boolean logged = sharedPreferences.getBoolean("authorized", false);

                if (logged) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        }, 5000);
    }
}