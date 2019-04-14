package com.route.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.route.quranproject.R;

import java.util.ArrayList;

public class AyatRecycleviewAdapter extends RecyclerView.Adapter<AyatRecycleviewAdapter.ViewHolder> {
ArrayList<String> verses;
    Typeface face;

    public AyatRecycleviewAdapter(ArrayList<String> verses,Context context) {
        face = Typeface.createFromAsset(context.getAssets(),"Fonts/GE_SS_Two_Bold.otf");
        this.verses = verses;

    }

        @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ayat_recycleview,viewGroup,false);
        return new ViewHolder(view);

    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        viewHolder.aya.setText(verses.get(pos));
    }




    @Override
    public int getItemCount() {
        if(verses==null)
            return 0;
        else
      return   verses.size(); }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView aya;
        public ViewHolder(View view) {
            super(view);
            aya=view.findViewById(R.id.aya);
            aya.setTypeface(face);
        }
    }




}
