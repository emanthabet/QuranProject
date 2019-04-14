package com.route.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.route.quranproject.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {
ArrayList<String> suranames;

    public SpinnerAdapter(ArrayList<String> suranames) {
        this.suranames = suranames;
    }

    @Override
    public int getCount() {
        return suranames.size();
    }

    @Override
    public Object getItem(int position) {
        return suranames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sura_spinner_item, parent, false);//create view
           viewHolder=new ViewHolder();//created viewholder
           viewHolder.name = convertView.findViewById(R.id.sura_name);//created viewholder
            convertView.setTag(viewHolder);

        }else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.name.setText(suranames.get(position));
        return convertView;
    }
    public class ViewHolder{
        TextView name;
    }
}
