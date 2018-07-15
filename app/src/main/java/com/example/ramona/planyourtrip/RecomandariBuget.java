package com.example.ramona.planyourtrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RecomandariBuget extends AppCompatActivity {

    String[] TITLES={"2 zile Paris", "Weekend Barcelina", "Excursie in Maroc"};
    String[] DESCRIPTIONS={"bilet avion ieftin",  "cazare ieftina in centtu", "mancarea ieftina"};
   ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomandari_buget);
        listView=(ListView)findViewById(R.id.listview_recomandari);

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
