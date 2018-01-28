package com.example.ramona.planyourtrip.Util.FragmentList;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

public class FragmentList extends ListFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.fragment,container,false);
        String[] listaMeniu  = {"Limba","Moneda","Tara"};
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
        Toast.makeText(getActivity(),textView.getText().toString(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(),Profile.class);
        intent.putExtra("nume","nume");
        startActivity(intent);
    }
}
