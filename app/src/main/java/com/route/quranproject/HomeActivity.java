package com.route.quranproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.route.Base.BaseActivity;

public class HomeActivity extends BaseActivity {

    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);//new onnavigationclicklistner
        navigation.setSelectedItemId(R.id.navigation_quran);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            if(item.getItemId()==R.id.navigation_quran)
              fragment=new QuranFragment();
            if(item.getItemId()==R.id.navigation_praise)
                fragment=new PraiseFragment();
            if(item.getItemId()==R.id.navigation_hadees)
                fragment=new HadeesFragment();
            if (item.getItemId()==R.id.navigation_Radio)
                fragment=new RadioFragment();
            if (item.getItemId()==R.id.navigation_Listening)
                fragment=new ListeningFragment();


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentnavigation,fragment)
                    .commit();

           //kda lma ados 3ala item hchange elcolor
return true;
        }
    };


}
