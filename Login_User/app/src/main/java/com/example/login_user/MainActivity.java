package com.example.login_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

// Di MainActivity.java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cek status login
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("is_logged_in", false);

        if (isLoggedIn) {
            // Jika sudah login, arahkan ke HomeActivity
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            // Jika belum login, arahkan ke LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish(); // Tutup MainActivity
    }
}