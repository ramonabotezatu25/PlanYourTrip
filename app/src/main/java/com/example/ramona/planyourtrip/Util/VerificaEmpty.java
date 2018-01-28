package com.example.ramona.planyourtrip.Util;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramona.planyourtrip.R;

/**
 * Created by Ramona on 1/28/2018.
 */

public class VerificaEmpty extends Activity{


    //aceasta metoda este de a verifica toate campurile de tip editText
    public void vericaEmplty(Context context, EditText... editText){
        for(int i =0;i<editText.length;i++){
            if(editText[i].getText().toString().equals("")){
                Toast.makeText(context, R.string.util_ve_editText,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
