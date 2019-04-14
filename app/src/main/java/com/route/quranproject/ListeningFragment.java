package com.route.quranproject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.route.APIs.ApiManager;
import com.route.APIs.Models.Reciters.RecitersItem;
import com.route.APIs.Models.Reciters.RecitersResponse;
import com.route.Adapters.ReciterRecycleviewAdapter;
import com.route.Adapters.SpinnerAdapter;
import com.route.Base.BaseFragment;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListeningFragment extends BaseFragment {
    RecyclerView recyclerView;
    ReciterRecycleviewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Spinner spinner;
    SpinnerAdapter spinnerAdapter;
    ArrayList<Integer>suraindeces=new ArrayList<>();
    ImageView playbutton;
    ImageView stopbutton;
    String url;
    public ListeningFragment() {
        // Required empty public constructor
    }

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_listening,container,false);
        recyclerView=view.findViewById(R.id.listening_recycleview);
        spinner=view.findViewById(R.id.listening_spinner);
        adapter=new ReciterRecycleviewAdapter(null);
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(recyclerView);
        getreciter();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstvisibleposition=((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
                //now i have the index of the thing
                RecitersItem item= adapter.getReciter(firstvisibleposition);
                SetSpinner(item);
                spinner.setAdapter(spinnerAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //elid gabha mn eladapter
                        int suraindex=suraindeces.get(position);

                        int sura=suraindeces.get(position);
                        String suraindexstring=(sura+1)+"";
                        while(suraindexstring.length()<3)
                            suraindexstring="0"+suraindexstring;
                        int firstvisibleposition=((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
                        RecitersItem item= adapter.getReciter(firstvisibleposition);
                        url=item.getServer()+"/"+suraindexstring+".mp3";
                       //lw d5lt 3ala elserver / 001.mp3 hed5lny 3nd awl sora
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
        });
        playbutton=view.findViewById(R.id.playbutton);
        stopbutton=view.findViewById(R.id.stop);
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onplayer(url);
            }
        });
        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onstop();
            }
        });
        return view;
    }

    public void getreciter(){
        ShowProgressBar();
        ApiManager.getApis().getAllReciters().enqueue(new Callback<RecitersResponse>() {
            @Override
            public void onResponse(Call<RecitersResponse> call, Response<RecitersResponse> response) {
                if(response.isSuccessful())
                {hideprogressbar();
                adapter.changedata(response.body().getReciters());}
            }

            @Override
            public void onFailure(Call<RecitersResponse> call, Throwable t) {
                hideprogressbar();
                ShowMessage("warning",t.getLocalizedMessage(),"ok");
            }
        });
    }

    public void SetSpinner(RecitersItem recitersItem){
        String[] surahs=recitersItem.getSuras().split(",");
        for(int i=0;i<surahs.length;i++)
        {String s=surahs[i];
       suraindeces.add(Integer.valueOf(s)-1);}

        ArrayList<String> suranames=new ArrayList<>();
        for(int i=0;i<suraindeces.size();i++)
        {
            suranames.add(QuranFragment.names[suraindeces.get(i)]);
        }
        spinnerAdapter=new SpinnerAdapter(suranames);
    }


    MediaPlayer mediaPlayer=null;
    public void onplayer(String url){
        onstop();
        mediaPlayer=new MediaPlayer();
        try
        {
            mediaPlayer.setDataSource(url);//bta5od url or file mn 3ala elmobile
            mediaPlayer.prepareAsync();//prepare fe elbackthread bs prepare only de lw mfesh network call ya3ney files mn 3ala elmobile
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
        catch (IOException e)
        {
            ShowMessage("error","cannott find url","ok");
        }
    }

    public void onstop(){
        if(mediaPlayer!=null)
        {mediaPlayer.stop();}
    }
}
