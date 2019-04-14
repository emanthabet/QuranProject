package com.route.quranproject;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.route.APIs.ApiManager;
import com.route.APIs.Models.Radios.RadioResponse;
import com.route.APIs.Models.Radios.RadiosItem;
import com.route.Adapters.RadioRecycleviewAdapter;
import com.route.Base.BaseFragment;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RadioFragment extends BaseFragment {
RecyclerView channelrecyclerView;
RadioRecycleviewAdapter Adapter;
RecyclerView.LayoutManager layoutManager;
    public RadioFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_radio, container, false);
        channelrecyclerView=view.findViewById(R.id.radio_recycleview);
        layoutManager=new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,true);
        Adapter=new RadioRecycleviewAdapter(null);//ana fe elawl b5leh null l7d lma ageb eldata mn elapi w b5ly fe elgetitem eno lw null yrg3 zero 3shan mydrabsh
        channelrecyclerView.setLayoutManager(layoutManager);
        channelrecyclerView.setAdapter(Adapter);
        channelrecyclerView.setLayoutManager(layoutManager);
        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(channelrecyclerView);
        getRadioChannels();
        Adapter.setOnStopClickListner(new RadioRecycleviewAdapter.OnItemClickListner() {
            @Override
            public void OnItemClick(int position, RadiosItem Channel) {
                onstop();
            }
        });
        Adapter.setOnPlayClickListner(new RadioRecycleviewAdapter.OnItemClickListner() {
            @Override
            public void OnItemClick(int position, RadiosItem Channel) {
                onplayer(Channel.getURL());
            }
        });

            return view;
        }


        MediaPlayer mediaPlayer=null;
        public void onplayer(String url){
            onstop();
            mediaPlayer=new MediaPlayer();
            try
            {ShowProgressBar();
                mediaPlayer.setDataSource(url);//bta5od url or file mn 3ala elmobile
            mediaPlayer.prepareAsync();//prepare fe elbackthread bs prepare only de lw mfesh network call ya3ney files mn 3ala elmobile
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    hideprogressbar();
                    mp.start();
                }
            });
            }
            catch (IOException e)
            {hideprogressbar();
            ShowMessage("error","cannott find url","ok");
            }
        }

        public void onstop(){
        if(mediaPlayer!=null)
        {mediaPlayer.stop();}
        }

    void getRadioChannels() {//kda ana bageb eldata
        ShowProgressBar();
        ApiManager.getApis()
                .getAllRadioChannels()
                 .enqueue(new Callback<RadioResponse>() {
            @Override
            public void onResponse(Call<RadioResponse> call, Response<RadioResponse> response) {
                hideprogressbar();
                if (response.isSuccessful()) {

              Adapter.changedata(response.body().getRadios());}
            }

            @Override
            public void onFailure(Call<RadioResponse> call, Throwable t) {
                hideprogressbar();
                ShowMessage("error", t.getLocalizedMessage(), "ok");
            }
        });
    }


}
