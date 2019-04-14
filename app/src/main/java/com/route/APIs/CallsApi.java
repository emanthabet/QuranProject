package com.route.APIs;

import com.route.APIs.Models.Radios.RadioResponse;
import com.route.APIs.Models.Reciters.RecitersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallsApi {
    @GET ("radio//radio_ar.json")
    Call<RadioResponse> getAllRadioChannels();

    @GET("_arabic.json")
    Call<RecitersResponse> getAllReciters();
}
