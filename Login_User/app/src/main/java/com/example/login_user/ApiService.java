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
                @Field("email") String email,
                @Field("password") String password
        );

        @FormUrlEncoded
        @POST("user_API.php?action=register")
        Call<JsonObject> register(
                @Field("nama") String nama,
                @Field("password") String password,
                @Field("email") String email
        );

        @FormUrlEncoded
        @POST("user_API.php?action=get_user")
        Call<User> getUserData(
                @Field("email") String email
        );

        @FormUrlEncoded
        @POST("user_API.php?action=logout")
        Call<JsonObject> logout(
                @Field("email") String email
        );
}