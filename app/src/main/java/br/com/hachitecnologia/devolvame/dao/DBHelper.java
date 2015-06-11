package br.com.hachitecnologia.devolvame.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by iaufc26.costa on 26/05/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_DO_BANCO = "devolva_me";
    private static final int VERSAO_DO_BANCO = 3;

    public DBHelper(Context context){
        super(context, NOME_DO_BANCO, null, VERSAO_DO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     /*   String sql = "CREATE TABLE objeto_emprestado (" +
            "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT" +
            ", objeto TEXT NOT NULL" +
            ", pessoa TEXT NOT NULL" +
            ", telefone TEXT NOT NULL" +
            ", data_emprestimo INTEGER NOT NULL" +
            ");";*/
        String sql = "CREATE TABLE objeto_emprestado (" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT" +
                ", objeto TEXT NOT NULL" +
                ", contato_id INTEGER NOT NULL" +
                ", data_emprestimo INTEGER NOT NULL" +
                ", foto BLOB" +
                ");";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS objeto_emprestado";
        db.execSQL(sql);
        onCreate(db);
    }
}
