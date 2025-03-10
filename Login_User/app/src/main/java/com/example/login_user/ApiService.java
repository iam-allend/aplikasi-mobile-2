package com.example.login_user;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("user_API.php?action=login")
    Call<JsonObject> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api.php?action=register")
    Call<JsonObject> register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("api.php?action=get_user")
    Call<User> getUserData(
            @Field("username") String username
    );

    Call<JsonObject> logout(String username);
}
