package com.example.ramona.planyourtrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Oferte;
import static com.example.ramona.planyourtrip.GmailSender.Constante.userPreferencesForHome;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecomandariBuget extends AppCompatActivity {

    String[] TITLES;
    String[] DESCRIPTIONS;
    ListView listView;
    DatabaseOperation databaseOperation = new DatabaseOperation();
    List<Oferte> offerte = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomandari_buget);
        listView=(ListView)findViewById(R.id.listview_recomandari);
        offerte = databaseOperation.getOffers(userPreferencesForHome.getBugetOferte());
        TITLES = new String[offerte.size()];
        DESCRIPTIONS = new String[offerte.size()];
        for(int i =0;i<offerte.size();i++){
            TITLES[i] = offerte.get(i).getBuget().toString()+"$";
            DESCRIPTIONS[i] =offerte.get(i).getDescriere();
        }
        CustomAdapter customAdapter= new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view= getLayoutInflater().inflate(R.layout.layout_recomandari_buget,null);
            TextView tvTitlu=(TextView)view.findViewById(R.id.tv_recomdariTitlu);
            TextView tvDescriere=(TextView)view.findViewById(R.id.tv_recomandariPret);

            tvTitlu.setText(TITLES[i]);
            tvDescriere.setText(DESCRIPTIONS[i]);

            return view;
        }
    }
}
