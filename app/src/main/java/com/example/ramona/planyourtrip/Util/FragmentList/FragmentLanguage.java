package com.example.ramona.planyourtrip.Util.FragmentList;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ramona.planyourtrip.CircleProfile;
import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.R;
import com.hitomi.cmlibrary.CircleMenu;

import io.paperdb.Paper;

import static com.example.ramona.planyourtrip.MultiLanguage.Language.schimbaLimba;

/**
 * Created by Ramona on 1/28/2018.
 */

public class FragmentLanguage extends ListFragment {
    //setari de limba
    Context context;
    Resources resources;
    //
    CircleMenu circleMenu;
    private String romana ;
    private String engleza;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){

        ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.fragment_item,container,false);
        String[] listaMeniu  =getSubmeniuProfil();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.fragmentraw_item,R.id.textViewFragment_item,listaMeniu);
        setListAdapter(arrayAdapter);
        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
        super.onListItemClick(l, view, position, id);
        ViewGroup viewGroup = (ViewGroup)view;
        TextView textView = (TextView)viewGroup.findViewById(R.id.textViewFragment_item);
        if(textView.getText().toString().equals(engleza)){
            schimbaLimba("EN");
        }else if(textView.getText().toString().equals(romana)){
            schimbaLimba("RO");
        }
        Intent intent = new Intent(getActivity(),CircleProfile.class);
        startActivity(intent);
    }

    private String[] getSubmeniuProfil (){
        context = getContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();
        engleza = resources.getString(R.string.profil_sub_language_engleza);
        romana = resources.getString(R.string.profil_sub_language_romana);
        return new String[]{romana,engleza};
    }
}
