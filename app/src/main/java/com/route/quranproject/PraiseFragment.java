package com.route.quranproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.route.Base.BaseFragment;

public class PraiseFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
int counter=0;
int sumcounter;
    Spinner spinner;
    TextView count;
    ImageView imageView;
    TextView sumofcount;
    Button reset;
    public PraiseFragment() {
    }
    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.praise_layout,container,false);
      spinner = view.findViewById(R.id.praises_spinner);
      count=view.findViewById(R.id.counter);
      imageView=view.findViewById(R.id.click_taspe7);
      sumofcount=view.findViewById(R.id.sumofcounter);
      reset=view.findViewById(R.id.Reset);
        sumofcount.setText("" + GetData("counter"));
        sumcounter=GetData("counter");
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData("counter");
                sumofcount.setText(""+GetData("counter"));
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.praise_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ++counter;
                    count.setText("" + counter);
                    ++sumcounter;
                    SaveData("counter", sumcounter);
                    sumofcount.setText("" + GetData("counter"));

            }

        });





        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            String praise = parent.getItemAtPosition(position).toString();
            ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#fff176"));
            counter=0;
            count.setText(""+counter);
            Toast.makeText(getContext(), praise, Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
