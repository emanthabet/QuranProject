package com.route.quranproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.route.Adapters.SurahsRecycleviewAdapter;
import com.route.Base.BaseFragment;

public class QuranFragment extends BaseFragment {
    TextView title;
    RecyclerView recyclerView;
    SurahsRecycleviewAdapter adapter;
   GridLayoutManager layoutManager;
    View view;

    public QuranFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.quran_layout, container, false);
        title = view.findViewById(R.id.HolyQuran);
        Typeface face = Typeface.createFromAsset(getContext().getAssets(),"Fonts/almushaf.ttf");
        title.setTypeface(face);
        recyclerView = view.findViewById(R.id.HolyQuranrecycleview);
        adapter = new SurahsRecycleviewAdapter(names,getContext());
        layoutManager = new GridLayoutManager(getContext(),3, GridLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);
        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

        adapter.setOnsuraclick(new SurahsRecycleviewAdapter.OnItemClickListner() {
            @Override
            public void OnItemClick(int position,String name) { //3shan enta 3amel ve elviewholde en seonclicklistner be3ml obj mn elonitemclicklistner
               // w beb3t position; ya3ney 3shan yexcute elmethod de lazem ykon gh mn eladapter elpos w elname
                Intent intent=new Intent(getContext(),AyatActivity.class);
                intent.putExtra("name_of_sura",name);
                intent.putExtra("name_of_file",(position+1)+".txt");//3shan 2b3tlo elfile bta3o bs msh lsa heshof 3ala 7asb elpos y3rd eh
                startActivity(intent);
            }
        });


        return view;
    }


  public  static String []names= {"الفاتحه", "البقرة", "آل عمران", "النساء", "المائدة", "الأنعام", "الأعراف", "الأنفال", "التوبة", "يونس", "هود"
            , "يوسف", "الرعد", "إبراهيم", "الحجر", "النحل", "الإسراء", "الكهف", "مريم", "طه", "الأنبياء", "الحج", "المؤمنون"
            , "النّور", "الفرقان", "الشعراء", "النّمل", "القصص", "العنكبوت", "الرّوم", "لقمان", "السجدة", "الأحزاب", "سبأ"
            , "فاطر", "يس", "الصافات", "ص", "الزمر", "غافر", "فصّلت", "الشورى", "الزخرف", "الدّخان", "الجاثية", "الأحقاف"
            , "محمد", "الفتح", "الحجرات", "ق", "الذاريات", "الطور", "النجم", "القمر", "الرحمن", "الواقعة", "الحديد", "المجادلة"
            , "الحشر", "الممتحنة", "الصف", "الجمعة", "المنافقون", "التغابن", "الطلاق", "التحريم", "الملك", "القلم", "الحاقة", "المعارج"
            , "نوح", "الجن", "المزّمّل", "المدّثر", "القيامة", "الإنسان", "المرسلات", "النبأ", "النازعات", "عبس", "التكوير", "الإنفطار"
            , "المطفّفين", "الإنشقاق", "البروج", "الطارق", "الأعلى", "الغاشية", "الفجر", "البلد", "الشمس", "الليل", "الضحى", "الشرح"
            , "التين", "العلق", "القدر", "البينة", "الزلزلة", "العاديات", "القارعة", "التكاثر", "العصر",
            "الهمزة", "الفيل", "قريش", "الماعون", "الكوثر", "الكافرون", "النصر", "المسد", "الإخلاص", "الفلق", "الناس"};




}