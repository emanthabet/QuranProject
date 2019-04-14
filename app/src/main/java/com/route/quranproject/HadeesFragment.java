package com.route.quranproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.route.Adapters.HadethRecycleviewAdapter;
import com.route.Base.BaseFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HadeesFragment extends BaseFragment {
    ArrayList<String> Hadeth;
    ArrayList<String> title;
    ArrayList<String> SpecificHadeth;

    RecyclerView recyclerView;
    HadethRecycleviewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    public HadeesFragment() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hadees, container, false);
        recyclerView = view.findViewById(R.id.hadeth_recycleview);
        Hadeth = new ArrayList<>();
        title = new ArrayList<>();
        readfile();
        adapter = new HadethRecycleviewAdapter(title);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnHadethclick(new HadethRecycleviewAdapter.OnItemClickListner() {
            @Override
            public void OnItemClick(int position) {
                Intent intent=new Intent(getContext(),HadeesContent.class);
                intent.putExtra("Hadith",Hadeth.get(position));
                startActivity(intent);
            }
        });

        return view;
    }

   /* public void trysome() {
        Hadeth = new ArrayList<>();
        title = new ArrayList<>();
        title.add(0, "الاول");
        title.add(1, "الثانى");
        title.add(2, "الثالث");


    }*/


   public ArrayList<String> readfile(){
        BufferedReader reader;
        try { int j=0;
            int counter=0;
            final InputStream file = getContext().getAssets().open("ahadeth.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while (line != null) {
                if (j == 0) {
                    title.add(line);
                    j++;

                } else {
                    if (line.equals("#") || line.equals(" #")) {
                        j = 0;
                        ++counter;
                    } else {
                        Hadeth.add(counter,line);
                    }
                }

                line = reader.readLine();
            }



        }
        catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
            return title;
            }


        }


