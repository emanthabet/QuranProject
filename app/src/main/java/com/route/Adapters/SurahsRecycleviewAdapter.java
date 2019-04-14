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

public class SurahsRecycleviewAdapter  extends RecyclerView.Adapter<SurahsRecycleviewAdapter.ViewHolder> {
  //  ArrayList<Surahs> items;
    String []names;
    OnItemClickListner onsuraclick;
    Typeface face;


    public void setOnsuraclick(OnItemClickListner onsuraclick) {
        this.onsuraclick = onsuraclick;
    }

   /* public SurahsRecycleviewAdapter(ArrayList<Surahs> items) {
        this.items = items;
    }*/

    public SurahsRecycleviewAdapter(String[] names, Context context) {
        face = Typeface.createFromAsset(context.getAssets(),"Fonts/almushaf.ttf");
        this.names = names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quran_surah_recycleview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.name.setText(names[i]);
        if (onsuraclick!=null) //ya3ney fe obj mn elonitemclicklistner 2t3mlo create
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {//viewholder.name yb2a heclick 3ala elname only
                @Override
                public void onClick(View v) {
                    onsuraclick.OnItemClick(i,names[i]);
                }
            });
                               }
    }

    @Override
    public int getItemCount() {

        return names.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ViewHolder(View view) {
            super(view);
           name = view.findViewById(R.id.surah_name);
        name.setTypeface(face);}
    }


    public interface OnItemClickListner{
        void OnItemClick(int position,String name);
    }
}
