package com.route.quranproject;

import android.os.Bundle;
import android.widget.TextView;

import com.route.Base.BaseActivity;

public class HadeesContent extends BaseActivity {
TextView textView;
String Hadeth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hadeescontent);
      Hadeth=getIntent().getStringExtra("Hadith");
       textView=findViewById(R.id.hadeth);
         textView.setText(Hadeth);

        }

    }





