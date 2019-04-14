package com.route.Base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class BaseActivity extends AppCompatActivity {

    protected AppCompatActivity activity;
    MaterialDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
    }
    public MaterialDialog ShowMessage(String titleResID,String msgResID,String PosResTxt){
        dialog=new MaterialDialog.Builder(this)
                .title(titleResID)
                .content(msgResID)
                .positiveText(PosResTxt)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        finish();


                    }
                })
                .show();
        return  dialog;

    }

    public MaterialDialog ShowProgressBar(){
        dialog=new MaterialDialog.Builder(this)
                .progress(true,0)
                .cancelable(false)
                .show();
       return dialog;
    }

    public void hideprogressbar(){

        if(dialog!=null&&dialog.isShowing())
            dialog.dismiss();
    }

    SharedPreferences sharedPreferences;
    public void SaveData(String Key,int Value)
    { getSharedPreferences("Count",MODE_PRIVATE).edit().putInt(Key,Value).apply();
    }
    public int GetData(String Key){
       sharedPreferences= getSharedPreferences("Count",MODE_PRIVATE);

        return sharedPreferences.getInt(Key,0);
    }
}
