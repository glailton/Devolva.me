package br.com.hachitecnologia.devolvame.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import br.com.hachitecnologia.devolvame.modelo.Contato;

/**
 * Created by Glailton Costa on 08/06/2015.
 * <p/>
 * For Devolva.me
 */
public class Contatos {

    public static Contato getContato(int id, Context context){

        ContentResolver cr = context.getContentResolver();

        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?",
                new String[]{String.valueOf(id)}, null);

        Contato contato = null;

        if(cursor.moveToFirst()){
            contato = new Contato();
            contato.setId(id);
            contato.setNome(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            contato.setTelefone(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
        }

        cursor.close();

        return contato;
    }
}
