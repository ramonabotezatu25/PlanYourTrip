package com.example.ramona.planyourtrip.coverFlow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramona.planyourtrip.R;
import com.example.ramona.planyourtrip.Util.Locatii;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ramona on 4/4/2018.
 */

public class LocatiiAdapter extends BaseAdapter {

    private List<Locatii> locatiiExploreList;
    private Context mContext;

    public LocatiiAdapter(List<Locatii> locatiiExploreList, Context mContext) {
        this.locatiiExploreList = locatiiExploreList;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return locatiiExploreList.size();
    }

    @Override
    public Object getItem(int i) {
        return locatiiExploreList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        if(rowView == null){
            rowView = LayoutInflater.from(mContext).inflate(R.layout.items_coverflow_explore,null);

            TextView name= (TextView)rowView.findViewById(R.id.cover_flow_textView);
            ImageView image= (ImageView)rowView.findViewById(R.id.cover_flow_image);

            //Set Data

            Picasso.with(mContext).load(locatiiExploreList.get(i).getLink2())
                    .into(image);
            name.setText(locatiiExploreList.get(i).getNume());

        }

        return  rowView;
    }
}
