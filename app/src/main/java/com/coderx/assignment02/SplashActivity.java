package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hiding the title bar
        // Hide the status bar and set full-screen
        View view = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        view.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splash);

        /*creating new Handler method to go MainActivity after 4 second*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // creating intent for moving to MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        },SPLASH_TIME_OUT);
    }
}
