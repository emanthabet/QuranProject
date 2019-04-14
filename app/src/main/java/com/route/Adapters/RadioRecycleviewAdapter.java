package com.route.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.route.APIs.Models.Radios.RadiosItem;
import com.route.quranproject.R;

import java.util.List;

public class RadioRecycleviewAdapter extends RecyclerView.Adapter<RadioRecycleviewAdapter.ViewHolder> {
List<RadiosItem>channels;
OnItemClickListner onStopClickListner;
OnItemClickListner onPlayClickListner;

    public RadioRecycleviewAdapter(List<RadiosItem> channels) {
        this.channels = channels;
    }

    public void setOnPlayClickListner(OnItemClickListner onPlayClickListner) {
        this.onPlayClickListner = onPlayClickListner;
    }

    public void setOnStopClickListner(OnItemClickListner onStopClickListner) {
        this.onStopClickListner = onStopClickListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.radio_recycleview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final  RadiosItem radiosItem=channels.get(i);
        viewHolder.name.setText(radiosItem.getName());
        if(onPlayClickListner!=null){
            viewHolder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPlayClickListner.OnItemClick(i,radiosItem);//bb3t elposition w elobject
                }
            });

            if (onStopClickListner!=null)
            {
                viewHolder.stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onStopClickListner.OnItemClick(i,radiosItem);
                    }
                });
            }
        }

    }

    //method to update eldata
    public void changedata(List<RadiosItem>items)
    {this.channels=items;
    notifyDataSetChanged();}

    @Override
    public int getItemCount() {
     if (channels==null) return 0;
        return channels.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
ImageView play;
ImageView stop;
        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.text);
play=view.findViewById(R.id.playbutton);
stop=view.findViewById(R.id.stop);
        }
    }


    public interface OnItemClickListner{
        void OnItemClick(int position,RadiosItem Channel);
    }


}
