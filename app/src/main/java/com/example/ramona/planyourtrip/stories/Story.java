package com.example.ramona.planyourtrip.stories;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ramona.planyourtrip.Home;
import com.example.ramona.planyourtrip.TravelTest;
import com.example.ramona.planyourtrip.Util.StoryObj;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.example.ramona.planyourtrip.R;
import java.util.ArrayList;
import java.util.List;

import static com.example.ramona.planyourtrip.GmailSender.Constante.storyList;

public class Story extends AppCompatActivity {

    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<StoryData> array;
    private ArrayList<StoryData> arrayList;
    private SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        array = new ArrayList<>();
        for(StoryObj s: storyList) {
            array.add(new StoryData(s.getLink(),s.getPoveste(),s.getFacebook(),s.getInstagram()));
        }
        /*       array.add(new StoryData("https://www.lacity.org/sites/g/files/wph781/f/styles/tiled_homepage_blog/public/bigstock-Los-Angeles-5909078.jpg?itok=Pu2dewLz", "Alexis Sanchez, Arsenal forward. Wanna chat with me ?. \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new StoryData("https://www.google.ro/search?q=messi&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjBmOmepPzbAhVFDywKHefwBGYQ_AUICigB&biw=1396&bih=674#imgdii=NMwHCiQ-ENYGLM:&imgrc=dsgmRdbm7Ypq4M:", "Christano Ronaldo, Real Madrid star. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new StoryData("http://www.delaroystudios.com/images/3.jpg", "Lionel Messi, Barcelona Best player. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new StoryData("http://www.delaroystudios.com/4.jpg", "David Beckham. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new StoryData("http://www.delaroystudios.com/images/5.jpg", "Sergio Arguerio. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new StoryData("http://www.delaroystudios.com/images/6.jpg", "Sergio Ramos. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new StoryData("http://www.delaroystudios.com/images/7.jpg", "Robert Lewandoski. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
*/
        arrayList = array;
        myAppAdapter = new MyAppAdapter(array, Story.this);
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {

                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(Story.this);
                builder.setTitle("Notice");
                builder.setMessage("Do you want to connect with this this user on instagram?");

                // add the buttons
                builder.setPositiveButton("Continue", null);
                // builder.setNeutralButton("Continue", null);
                builder.setNegativeButton("Cancel", null);

                builder.setNeutralButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        array.remove(0);
                        myAppAdapter.notifyDataSetChanged();
                        if(array.size()==0){
                            Intent intent = new Intent(getApplicationContext(), Story.class);
                            startActivity(intent);
                        }
                    }
                });


                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // do something like...
                        // do something like...


                        String instagram = array.get(0).getInstagram();
                        Uri uri = Uri.parse("http://instagram.com/_u/"+instagram);
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                        likeIng.setPackage("com.instagram.android");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://instagram.com/xxx")));
                        }

                        array.remove(0);
                        myAppAdapter.notifyDataSetChanged();
                        if(array.size()==0){
                            Intent intent = new Intent(getApplicationContext(), Story.class);
                            startActivity(intent);
                        }

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // do something like...
                        array.remove(0);
                        myAppAdapter.notifyDataSetChanged();
                        if(array.size()==0){
                            Intent intent = new Intent(getApplicationContext(), Story.class);
                            startActivity(intent);
                        }

                    }
                });

                AlertDialog dialog = builder.create();
                if(!array.get(0).getInstagram().equals("")){
                    dialog.show();
                } else {
                    array.remove(0);
                    myAppAdapter.notifyDataSetChanged();
                    if (array.size() == 0) {
                        Intent intent = new Intent(getApplicationContext(), Story.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(Story.this);
                builder.setTitle("Notice");
                builder.setMessage("Do you want to connect with this this user on facebook?");

                // add the buttons
                builder.setNeutralButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        array.remove(0);
                        myAppAdapter.notifyDataSetChanged();
                        if(array.size()==0){
                            Intent intent = new Intent(getApplicationContext(), Story.class);
                            startActivity(intent);
                        }
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // do something like...
                        String facebookName = array.get(0).getFacebook();

                        array.remove(0);
                        myAppAdapter.notifyDataSetChanged();
                        if(array.size()==0){
                            Intent intent = new Intent(getApplicationContext(), Story.class);
                            startActivity(intent);
                        }
                        try {
                            PackageManager pm = getApplicationContext().getPackageManager();
                            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
                            String url = "https://www.facebook.com/"+facebookName;
                            Uri uri = Uri.parse(url);
                            if (applicationInfo.enabled) {
                                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
                            }
                            Intent facebook = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(facebook);
                        } catch (Exception e) {
                            Intent facebook = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/"));
                            startActivity(facebook);
                        }

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // do something like...
                        array.remove(0);
                        myAppAdapter.notifyDataSetChanged();
                        if(array.size()==0){
                            Intent intent = new Intent(getApplicationContext(), Story.class);
                            startActivity(intent);
                        }

                    }
                });
                AlertDialog dialog = builder.create();
                if(!array.get(0).getFacebook().equals("")){
                    dialog.show();}
                else {
                    array.remove(0);
                    myAppAdapter.notifyDataSetChanged();
                    if(array.size()==0){
                        Intent intent = new Intent(getApplicationContext(), Story.class);
                        startActivity(intent);
                    }
                }


            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                myAppAdapter.notifyDataSetChanged();
            }
        });
    }

    public static class ViewHolder {
        public static FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;


    }

    public class MyAppAdapter extends BaseAdapter {


        public List<StoryData> parkingList;
        public Context context;

        private MyAppAdapter(List<StoryData> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.bookText);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.DataText.setText(parkingList.get(position).getDescription() + "");

            Glide.with(Story.this).load(parkingList.get(position).getImagePath()).into(viewHolder.cardImage);

            return rowView;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                // do something here
                Intent home = new Intent(getApplicationContext(), Home.class);
                startActivity(home);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
