package com.example.login_register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cek status login
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("is_logged_in", false);
        String email = prefs.getString("email", "");

        if (isLoggedIn && !email.isEmpty()) {
            // Jika sudah login, arahkan ke HomeActivity
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
        } else {
            // Jika belum login, arahkan ke LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish(); // Tutup MainActivity
    }
}