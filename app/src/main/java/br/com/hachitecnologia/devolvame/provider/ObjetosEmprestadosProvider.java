package br.com.hachitecnologia.devolvame.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import br.com.hachitecnologia.devolvame.dao.DBHelper;

/**
 * Created by Glailton Costa on 09/06/2015.
 * <p/>
 * For Devolva.me
 */
public class ObjetosEmprestadosProvider extends ContentProvider {

    private static final String AUTHORITY = "br.com.hachitecnologia.devolvame.provider";
    public static final String PATH = "objeto_emprestado";
    public static final Uri URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    private static final int BUSCA_TODOS = 1;
    private static final int BUSCA_POR_ID = 2;

    private DBHelper dbHelper;
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PATH, BUSCA_TODOS);
        uriMatcher.addURI(AUTHORITY, PATH + "/#", BUSCA_POR_ID);
    }


    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        switch (uriMatcher.match(uri)){
            case BUSCA_POR_ID:
                String id = uri.getPathSegments().get(1);
                return db.query(PATH, projection, "_id=?", new String[]{id}, null, null, sortOrder);
            case BUSCA_TODOS:
                return db.query(PATH, projection, selection, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("URI inválido");
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case BUSCA_TODOS:
                return "vnd.android.cursor.dir/vnd." + "br.com.hachitecnologia.devolvame.provider/objeto_emprestado";
            case BUSCA_POR_ID:
                return "vnd.android.cursor.item/vnd." + "br.com.hachitecnologia.devolvame.provider/objeto_emprestado";
            default:
                throw new IllegalArgumentException("URI inválido");

        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insert(PATH, null, values);

        return ContentUris.withAppendedId(URI, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
