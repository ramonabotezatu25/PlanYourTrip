package com.example.ramona.planyourtrip.GmailSender;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Infrasoft Ciprian on 31-Jan-18.
 */

public class CodUnicIdentificare {

    public static String codUnicDeIndentificare="";

    public static final void getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        codUnicDeIndentificare = salt.toString();
    }
}
