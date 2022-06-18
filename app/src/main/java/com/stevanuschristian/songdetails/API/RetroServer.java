package com.stevanuschristian.songdetails.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseURL = "https://api-uas-pabl-stevanuschristian-si61.000webhostapp.com/"; //Isi URL sesuai dengan API kalian masing-masing
    private static Retrofit retro;

    public static Retrofit konekRetrofit(){
        if (retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
