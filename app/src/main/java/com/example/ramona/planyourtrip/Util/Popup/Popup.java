package com.example.ramona.planyourtrip.Util.Popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.ramona.planyourtrip.ManageAccount;
import com.example.ramona.planyourtrip.R;

/**
 * Created by Ramona on 1/28/2018.
 */

public class Popup extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }

    public void manageAccount(View view){
        Intent manageAccount = new Intent(this, ManageAccount.class);
        startActivity(manageAccount);
    }
}
