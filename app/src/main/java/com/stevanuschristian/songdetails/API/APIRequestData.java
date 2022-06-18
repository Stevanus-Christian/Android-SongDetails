package com.stevanuschristian.songdetails.API;

import com.stevanuschristian.songdetails.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    //Isi sesuai file di API
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieveAllData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreateData(
            @Field("judul") String judul,
            @Field("penyanyi") String penyanyi,
            @Field("album") String album,
            @Field("aliran") String aliran
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse> ardDeleteData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardUpdateData(
            @Field("id") String id,
            @Field("judul") String judul,
            @Field("penyanyi") String penyanyi,
            @Field("album") String album,
            @Field("aliran") String aliran
    );
}
