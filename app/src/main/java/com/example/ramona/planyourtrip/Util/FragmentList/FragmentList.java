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
import android.widget.Toast;

import com.example.ramona.planyourtrip.CircleProfile;
import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.R;

import io.paperdb.Paper;

/**
 * Created by Ramona on 1/28/2018.
 */

public class FragmentList extends ListFragment {

    //setari de multilanguage
    private String limba ;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.fragment,container,false);
        String[] listaMeniu  = getMeniuProfil();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.fragmentraw,R.id.textViewFragment,listaMeniu);
        setListAdapter(arrayAdapter);
        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
        super.onListItemClick(l, view, position, id);
        ViewGroup viewGroup = (ViewGroup)view;
        TextView textView = (TextView)viewGroup.findViewById(R.id.textViewFragment);
        if (textView.getText().toString().equals(limba)) {
            Intent intent = new Intent(getActivity(),CircleProfile.class);
            intent.putExtra(FragmentConstant.FRAGMENT_NAME,textView.getText().toString());
            startActivity(intent);
        }else{
            Toast.makeText(getActivity(),"Aceasta functionalitate este in lucru!",Toast.LENGTH_SHORT).show();
        }
    }
    private String[] getMeniuProfil (){
        Context context = getContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        Resources resources = context.getResources();
        String tara = resources.getString(R.string.profil_meniu_country);
        limba = resources.getString(R.string.profil_meniu_language);
        return new String[]{tara,limba};
    }
}
