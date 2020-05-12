package com.example.globallogictest.interfaces;

import com.example.globallogictest.data.Info;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface globalLogicClient {

    @GET("list")
    Call<List<Info>> getInfo(
    );

}
