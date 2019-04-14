package com.route.APIs;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static Retrofit retrofitinstance;

    private static Retrofit getInstance(){
        if(retrofitinstance==null){

            HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("api",message);
                }
            });


            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client=new OkHttpClient.Builder()//elretrofit mabnya 3ala open source library tanya esmha okhttp
                    .addInterceptor(loggingInterceptor).build();


           retrofitinstance = new Retrofit.Builder()
                    .baseUrl("http://mp3quran.net//api/")
                    .addConverterFactory(GsonConverterFactory.create())
                   .client(client)
                    .build();
        }
        return retrofitinstance;
    }

// 3shan a3ml obj mn elcallsapi zy eldao
    public static CallsApi getApis(){
       CallsApi callsApi=getInstance().create(CallsApi.class);
return  callsApi;
    }
}
