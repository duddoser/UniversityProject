package com.example.universities;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("/get_faculties")
    Call<JsonObject> get_faculties(@Body HashMap<String, List<String>> json);
}
