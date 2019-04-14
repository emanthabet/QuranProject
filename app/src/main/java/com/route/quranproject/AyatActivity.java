package com.route.quranproject;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.route.Adapters.AyatRecycleviewAdapter;
import com.route.Base.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class AyatActivity extends BaseActivity {
    TextView ayatitle;
    TextView besm;
    RecyclerView recyclerView;
    AyatRecycleviewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
   ArrayList<String> verses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat);
        String nameofsura= getIntent().getStringExtra("name_of_sura");
        String nameoffile = getIntent().getStringExtra("name_of_file");
        recyclerView = findViewById(R.id.ayatrecycleview);
        ayatitle=findViewById(R.id.titleofaya);
        Typeface  ayaface = Typeface.createFromAsset(this.getAssets(),"Fonts/GE_SS_Two_Bold.otf");
        ayatitle.setTypeface(ayaface);
        ayatitle.setText(nameofsura);
        besm=findViewById(R.id.intro);
        Typeface  besmface = Typeface.createFromAsset(this.getAssets(),"Fonts/Besmellah.ttf");
        besm.setTypeface(besmface);
        besm.setText("]");
        verses=new ArrayList<>();
        readfile(nameoffile);
        adapter = new AyatRecycleviewAdapter(verses,getApplicationContext());
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        }


  public ArrayList<String> readfile(String filename) {
        int counter=1;
      BufferedReader reader;
        try {
            final InputStream file =getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while (line != null) {
                // Log.d("StackOverflow", line);
                verses.add(line+" ("+counter+")");
                line = reader.readLine();
                counter++;
            }

        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return verses;
    }

}


