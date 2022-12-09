package com.example.comic.Retrofit;

import com.example.comic.Model.Comic;
import com.example.comic.Model.ComicModel;
import com.example.comic.Model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiComic {
    @GET("getcomic.php")
    Observable<ComicModel> getComic();

    @POST("register.php")
    @FormUrlEncoded
    Observable<UserModel> register(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("fullname") String fullname
    );
    @POST("login.php")
    @FormUrlEncoded
    Observable<UserModel> login(
            @Field("email") String email,
            @Field("pass") String pass

    );
}
