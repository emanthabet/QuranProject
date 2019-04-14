package com.route.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.route.quranproject.R;

import java.util.ArrayList;

public class HadethRecycleviewAdapter extends RecyclerView.Adapter <HadethRecycleviewAdapter.ViewHolder>{
    ArrayList<String> titles;
    OnItemClickListner onHadethclick;

    public void setOnHadethclick(OnItemClickListner onHadethclick) {
        this.onHadethclick = onHadethclick;
    }

    public HadethRecycleviewAdapter(ArrayList<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hadeth_recycleview,viewGroup,false);
         return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        viewHolder.text.setText(titles.get(i));
if (onHadethclick!=null)
{
    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onHadethclick.OnItemClick(i);
        }
    });
}

    }


    @Override
    public int getItemCount() {

        if(titles==null)
            return 0;
        else
            return titles.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public ViewHolder(@NonNull View view) {
            super(view);
           text=view.findViewById(R.id.hadeth);

        }
    }

    public interface OnItemClickListner{
        void OnItemClick(int position);
    }
}
