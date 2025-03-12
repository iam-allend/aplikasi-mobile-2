package com.example.login_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password harus diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Buat instance Gson dengan setLenient(true)
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            // Konfigurasi Retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2/API_mobile/") // Ganti dengan base URL Anda
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            // Buat instance ApiService
            ApiService apiService = retrofit.create(ApiService.class);

            // Panggil API login
            Call<JsonObject> call = apiService.login(email, password);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        // Handle success
                        JsonObject jsonResponse = response.body();
                        String status = jsonResponse.get("status").getAsString();
                        if (status.equals("success")) {
                            // Login berhasil
                            Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            // Login gagal
                            String message = jsonResponse.get("message").getAsString();
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Handle error response
                        try {
                            String errorBody = response.errorBody().string();
                            Log.e("API_ERROR", errorBody);
                            Toast.makeText(LoginActivity.this, "Error: " + errorBody, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    // Handle network failure
                    Toast.makeText(LoginActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}