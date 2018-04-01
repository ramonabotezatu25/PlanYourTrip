package com.example.ramona.planyourtrip.GmailSender;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.tv.TvInputService;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ramona.planyourtrip.R;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Handler;

import javax.mail.Session;

import static com.example.ramona.planyourtrip.GmailSender.CodUnicIdentificare.codUnicDeIndentificare;
import static com.example.ramona.planyourtrip.GmailSender.CodUnicIdentificare.getSaltString;

public class SendEmail extends AppCompatActivity {

    ProgressDialog progressDialog ;
    FlipProgressDialog fpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
    }

    final Thread myThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                GMailSender sender = new GMailSender("worldtipstravel@gmail.com", "Houriapalace1!");
                sender.sendMail("Plan Your Trip",
                        "Bun venit !Codul dumneavoastra de identificare este : " +codUnicDeIndentificare,
                        "worldtipstravel@gmail.com",
                        "dansilviuiancu@gmail.com");
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }finally {
                fpd.dismiss();
            }
        }

    });

    public void sendEmail(View view){
      /*  progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Doing something, please wait.");
        progressDialog.show();
        myThread.start();*/
        getSaltString();
        List<Integer> imageList = new ArrayList<Integer>();
        imageList.add(R.drawable.gmail);

        try{
            fpd = new FlipProgressDialog();
            fpd.setImageList(imageList);                              // *Set a imageList* [Have to. Transparent background png recommended]
            fpd.setCanceledOnTouchOutside(true);                      // If true, the dialog will be dismissed when user touch outside of the dialog. If false, the dialog won't be dismissed.
            fpd.setDimAmount(0.0f);                                   // Set a dim (How much dark outside of dialog)

            // About dialog shape, color
            fpd.setBackgroundColor(Color.parseColor("#FF4081"));      // Set a background color of dialog
            fpd.setBackgroundAlpha(0.2f);                             // Set a alpha color of dialog
            fpd.setBorderStroke(0);                                   // Set a width of border stroke of dialog
            fpd.setBorderColor(-1);                                   // Set a border stroke color of dialog
            fpd.setCornerRadius(16);                                  // Set a corner radius

// About image
            fpd.setImageSize(200);                                    // Set an image size
            fpd.setImageMargin(10);                                   // Set a margin of image

// About rotation
            fpd.setOrientation("rotationY");                          // Set a flipping rotation
            //  fpd.setDuration(600);                                     // Set a duration time of flipping ratation
            fpd.setStartAngle(0.0f);                                  // Set an angle when flipping ratation start
            fpd.setEndAngle(180.0f);                                  // Set an angle when flipping ratation end
            fpd.setMinAlpha(0.0f);                                    // Set an alpha when flipping ratation start and end
            fpd.setMaxAlpha(1.0f);                                    // Set an alpha while image is flipping


            fpd.show(getFragmentManager(),"");                        // Show flip-progress-dialg
            myThread.start() ;                                                         // Dismiss flip-progress-dialg
        }catch (Exception ex){
            System.out.print(ex.toString());
        }

    }


    /*public void schimbaActivitatea(View view){
        Intent intent =new Intent(this,Main2Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.goup,R.anim.godown);
    }*/
}
