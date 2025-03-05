package com.example.login_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.JsonObject;

public class HomeActivity extends AppCompatActivity {
    private TextView tvUsername, tvEmail;
    private Button btnLogout;
    private String username; // Pindahkan variabel username ke level class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        btnLogout = findViewById(R.id.btnLogout);

        // Ambil username dari Intent
        username = getIntent().getStringExtra("username");

        // Ambil data user dari API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/API_mobile/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Panggil API untuk mendapatkan data user
        Call<User> getUserCall = apiService.getUserData(username);
        getUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if (user != null) {
                        tvUsername.setText("Username: " + user.getUsername());
                        tvEmail.setText("Email: " + user.getEmail());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Tombol logout
        btnLogout.setOnClickListener(v -> {
            // 1. Update status di SharedPreferences
            SharedPreferences.Editor editor = getSharedPreferences("user_prefs", MODE_PRIVATE).edit();
            editor.putBoolean("is_logged_in", false);
            editor.apply(); // Pastikan apply() atau commit() dipanggil

            // 2. Panggil API logout
            Call<JsonObject> logoutCall = apiService.logout(username); // Gunakan variabel username yang sudah didefinisikan
            logoutCall.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        // 3. Pindah ke LoginActivity
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear back stack
                        startActivity(intent);
                        finish(); // Tutup HomeActivity
                    } else {
                        Toast.makeText(HomeActivity.this, "Logout gagal: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(HomeActivity.this, "Logout gagal: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}