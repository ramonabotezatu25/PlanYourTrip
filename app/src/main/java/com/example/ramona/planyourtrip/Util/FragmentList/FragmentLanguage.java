package com.example.ramona.planyourtrip.Util.FragmentList;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.Profile;
import com.example.ramona.planyourtrip.R;

/**
 * Created by Ramona on 1/28/2018.
 */

public class FragmentLanguage extends ListFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.fragment_item,container,false);
        String[] listaMeniu  = {"Italiana","Engleza","RO"};
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
        Intent intent = new Intent(getActivity(),Profile.class);
        startActivity(intent);
    }
}
