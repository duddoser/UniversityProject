package com.example.universities;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProcess {
    Retrofit retrofit;
    public final String BASE_URL = "https://dudddoser.pythonanywhere.com/";
    List<String> subjects, universities;

    public interface facultiesCallbacks{ void onGetFaculties(Map<String, List<String>> unis);}

    public RetrofitProcess(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getFaculties(HashMap<String, List<String>> subjects,
                             facultiesCallbacks facultiesCallbacks){
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Call<JsonObject> response = requestInterface.get_faculties(subjects);
        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject>
                    response) {
                Map<String, List<String>> resp = new Gson().fromJson(response.body().toString(),
                        Map.class);
                facultiesCallbacks.onGetFaculties(resp);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
