package com.route.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.route.APIs.Models.Reciters.RecitersItem;
import com.route.quranproject.R;

import java.util.List;

public class ReciterRecycleviewAdapter extends RecyclerView.Adapter<ReciterRecycleviewAdapter.ViewHolder>  {

        List<RecitersItem> reciters;

        public ReciterRecycleviewAdapter(List<RecitersItem> reciters) {
            this.reciters = reciters;
        }



        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listening_recycleview, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            final RecitersItem recitersItem=reciters.get(i);
            viewHolder.name.setText(recitersItem.getName());
            viewHolder.rewaya.setText(recitersItem.getRewaya());
                }




        //method to update eldata
        public void changedata(List<RecitersItem> reciters)
        {this.reciters=reciters;
            notifyDataSetChanged();}

        @Override
        public int getItemCount() {
            if (reciters==null)
                return 0;
            return reciters.size();
        }



        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView rewaya;

            public ViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.Name);
                rewaya = view.findViewById(R.id.rawya);
            }

        }
            public RecitersItem getReciter(int position)
            {
                return reciters.get(position);
            }
        }







