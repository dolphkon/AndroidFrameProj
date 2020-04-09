package com.example.frameproj.utils;

import com.example.frameproj.bean.LoginResponse;
import com.example.frameproj.bean.RegisterResp;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    /**
    * 注册
    * */

    @FormUrlEncoded
    @POST( "user/register")
    Observable<RegisterResp> register(@Field("username")String username, @Field("password")String password, @Field("repassword")String repassword);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginResponse> login(@Field("username") String name, @Field("password") String password);
}
