package com.example.ramona.planyourtrip;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramona.planyourtrip.GmailSender.GMailSender;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;

import static com.example.ramona.planyourtrip.GmailSender.CodUnicIdentificare.codUnicDeIndentificare;
import static com.example.ramona.planyourtrip.GmailSender.CodUnicIdentificare.getSaltString;
import static com.example.ramona.planyourtrip.Util.Constants.COD_CONFIRMARE;
import static com.example.ramona.planyourtrip.Util.Constants.SENDER_EMAIL;

public class ConfirmationCode extends AppCompatActivity {

    //database
    DatabaseOperation db =new DatabaseOperation();
    String email;
    String codConfirmare;

    FlipProgressDialog fpd;
    EditText cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_code);
        cod=(EditText)findViewById(R.id.editText_confirationCode);
        Bundle bu = getIntent().getExtras();
        email = bu.getString(SENDER_EMAIL);
        codConfirmare = bu.getString(COD_CONFIRMARE);
    }
    public  void golesteEditText(View view)
    {
        cod.setText("");
    }
    final Thread myThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                GMailSender sender = new GMailSender("worldtipstravel@gmail.com", "Houriapalace1!");
                sender.sendMail("Plan Your Trip",
                        "Bun venit !Codul dumneavoastra de identificare este : " +codUnicDeIndentificare,
                        "worldtipstravel@gmail.com",
                        email);
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }finally {
                db.updateConfirmationCode(email,codUnicDeIndentificare);
                fpd.dismiss();
            }
        }

    });

    public void sendConfirmationEmail(View view){
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

    public void checkConfirmationCode(View view){
        String codConfirmareDb = db.checkConfirmationCode(email);
        if(codConfirmareDb.equals(codConfirmare)){
            db.activateUser(email);
            Intent loginActivity = new Intent(this,LogIn.class);
            startActivity(loginActivity);
        }else{
            Toast.makeText(getApplicationContext(), "Not OK", Toast.LENGTH_SHORT).show();

        }
    }

}
