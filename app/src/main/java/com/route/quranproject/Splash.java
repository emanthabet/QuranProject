package com.route.quranproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.route.Base.BaseActivity;

public class Splash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Splash.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                    }
                }, 3000);

    }
}
