package com.ronny.smzdm_mvvm.data.network;

import com.ronny.smzdm_mvvm.data.entity.DailyEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Ronny on 2020/11/24
 */
public class SmzdmDataService {

    private static final String BASE_URL = "http://120.77.60.87:8890";

    private DataApi mDataApi;

    public SmzdmDataService() {
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        mDataApi = mRetrofit.create(DataApi.class);
    }

    public DataApi getDataApi() {
        return mDataApi;
    }

    public interface DataApi {
        @GET("/getDailyData")
        Call<List<DailyEntity>> getData();
    }
}
