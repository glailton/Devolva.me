package br.com.hachitecnologia.devolvame.util;

import android.telephony.SmsManager;

import java.util.ArrayList;

/**
 * Created by Glailton Costa on 11/06/2015.
 * <p/>
 * For Devolva.me
 */
public class Telefonia {

    public static void enviaSMS(String numeroTelefone, String mensagem){
        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> mensagemArray = sms.divideMessage(mensagem);
        sms.sendMultipartTextMessage(numeroTelefone, null, mensagemArray, null, null);
    }
}
