package com.example.ramona.planyourtrip;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.GmailSender.GMailSender;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Email;
import com.example.ramona.planyourtrip.Util.User;
import com.john.waveview.WaveView;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;

import static com.example.ramona.planyourtrip.GmailSender.CodUnicIdentificare.codUnicDeIndentificare;
import static com.example.ramona.planyourtrip.GmailSender.CodUnicIdentificare.getSaltString;
import static com.example.ramona.planyourtrip.Util.Constants.ADMIN_EMAIL;
import static com.example.ramona.planyourtrip.Util.Constants.COD_CONFIRMARE;
import static com.example.ramona.planyourtrip.Util.Constants.EMAIL_BODY_NEW_ACCOUNT;
import static com.example.ramona.planyourtrip.Util.Constants.EMAIL_BODY_NEW_ACCOUNT_ADMIN;
import static com.example.ramona.planyourtrip.Util.Constants.EMAIL_SUBJECT_NEW_ACCOUNT;
import static com.example.ramona.planyourtrip.Util.Constants.SENDER_EMAIL;

public class SignUpActivity extends AppCompatActivity {

    //database
    DatabaseOperation db =new DatabaseOperation();

    TextView nume;
    TextView email;
    TextView parola;
    WaveView waveView;
    TextView campMesaj;
    FlipProgressDialog fpd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        waveView = (WaveView) findViewById(R.id.waveView);
        waveView.setProgress(0);

        campMesaj = (TextView) findViewById(R.id.tv_campMesaj_ConfirmationCode);

        nume = (TextView) findViewById(R.id.editText_name_SignUp);
        email = (TextView) findViewById(R.id.editText_email_SignUp);
        parola = (TextView) findViewById(R.id.editText_password_SignUp);
        nume.addTextChangedListener(fieldValidatorTextWatcher);
        email.addTextChangedListener(fieldValidatorTextWatcher);
        parola.addTextChangedListener(fieldValidatorTextWatcher);
    }

    public void upateProgressBar() {
        waveView.setProgress(0);
        List<String> lista = new ArrayList<String>();

        if (!nume.getText().toString().equals(""))
            lista.add("element");

        if (!email.getText().toString().equals(""))
            lista.add("element");


        if (!parola.getText().toString().equals(""))
            lista.add("element");

        waveView.setProgress((int) (33.3 * lista.size()));

    }

    TextWatcher fieldValidatorTextWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            upateProgressBar();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    };

    public void verificaDateSignUp(View view) {
        if (dateCompletate()) {
            sendCodeConfirmationEmail();
        } else {
            campMesaj.setVisibility(View.VISIBLE);
        }

    }

    public boolean dateCompletate() {
        if (!nume.getText().toString().equals("")) {
            if (!email.getText().toString().equals("")) {
                if (!parola.getText().toString().equals("")) {
                    return true;
                }
            }
        }
        return false;
    }

    final Thread myThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                GMailSender sender = new GMailSender("worldtipstravel@gmail.com", "Houriapalace1!");
                sender.sendMail(EMAIL_SUBJECT_NEW_ACCOUNT,
                        EMAIL_BODY_NEW_ACCOUNT  + codUnicDeIndentificare,
                        ADMIN_EMAIL,
                        email.getText().toString());
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            } finally {
                fpd.dismiss();
                    User user = new User();
                    user.setNume(nume.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setParola(parola.getText().toString());
                    user.setCodConfirmare(codUnicDeIndentificare);
                    //message Admin
                    Email messages = new Email();
                    messages.setNume_utilizator(user.getNume());
                    messages.setEmailTo(ADMIN_EMAIL);
                    messages.setEmailFrom(user.getEmail());
                    messages.setEmailSubject(EMAIL_SUBJECT_NEW_ACCOUNT);
                    messages.setEmailBody(EMAIL_BODY_NEW_ACCOUNT_ADMIN + user.getEmail());
                    //Message User
                    Email messagesUsr = new Email();
                    messagesUsr.setNume_utilizator(user.getNume());
                    messagesUsr.setEmailTo(user.getEmail());
                    messagesUsr.setEmailFrom(ADMIN_EMAIL);
                    messagesUsr.setEmailSubject(EMAIL_SUBJECT_NEW_ACCOUNT);
                    messagesUsr.setEmailBody(EMAIL_BODY_NEW_ACCOUNT + codUnicDeIndentificare);
                    //Message User
                    //database store
                    db.insertMessages(messages);
                    db.insertMessages(messagesUsr);
                    db.insertUtilizator(user);
                    //
                    Intent intent = new Intent(SignUpActivity.this, ConfirmationCode.class);
                    intent.putExtra(SENDER_EMAIL, user.getEmail());
                    intent.putExtra(COD_CONFIRMARE, codUnicDeIndentificare);
                    startActivity(intent);

            }
        }

    });

    public void sendCodeConfirmationEmail() {
      /*  progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Doing something, please wait.");
        progressDialog.show();
        myThread.start();*/
        Integer uniqueEmail = db.checkUniqueEmail(email.getText().toString());
        if(uniqueEmail == 0) {
        getSaltString();
        List<Integer> imageList = new ArrayList<Integer>();
        imageList.add(R.drawable.gmail);

        try {
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


            fpd.show(getFragmentManager(), "");
            // Show flip-progress-dialg

                myThread.start();
                                                            // Dismiss flip-progress-dialg
        } catch (Exception ex) {
            System.out.print(ex.toString());
        }
    }
        else{
            Toast.makeText(getApplicationContext(),"EMAIL DEJA FOLOSIT",Toast.LENGTH_SHORT);
            try {
                verifyUserIdentity.start();
            } catch (Exception ex) {
                System.out.print(ex.toString());
            }
        }
    }

    final Thread verifyUserIdentity = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                GMailSender sender = new GMailSender("worldtipstravel@gmail.com", "Houriapalace1!");
                sender.sendMail("Plan Your Trip",
                        "Cineva incearca sa isi creeze cont cu emailul dumneavoastra",
                        "worldtipstravel@gmail.com",
                        email.getText().toString());
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            } finally {
                Log.e("SendMail", "Protejare indetitate utilizator");
            }
        }

    });
}
