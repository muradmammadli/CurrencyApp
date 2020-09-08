package com.example.tayqaprojectv1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/rates.php?base=USD")
    Call<List<rates>> getRates();
}
