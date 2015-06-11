package br.com.hachitecnologia.devolvame.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Glailton Costa on 09/06/2015.
 * <p/>
 * For Devolva.me
 */
public class Util {

    public static byte[] converteBitmapParaByteArray(Bitmap imagem, int qualidadeDaImagem){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagem.compress(Bitmap.CompressFormat.PNG, qualidadeDaImagem,stream);
        return stream.toByteArray();
    }

    public static Bitmap converteByteArrayParaBitmap(byte[] imagem){
        return BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
    }
}
