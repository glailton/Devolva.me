package br.com.hachitecnologia.devolvame.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.hachitecnologia.devolvame.modelo.Contato;
import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;
import br.com.hachitecnologia.devolvame.util.Contatos;

/**
 * Created by iaufc26.costa on 26/05/2015.
 */
public class ObjetoEmprestadoDAO {

    private DBHelper dbHelper;
    private Context context;

    public ObjetoEmprestadoDAO(Context context){
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public void adiciona(ObjetoEmprestado objeto){
        ContentValues values = new ContentValues();
        values.put("objeto", objeto.getObjeto());
        values.put("data_emprestimo", System.currentTimeMillis());
        values.put("contato_id", objeto.getContato().getId());
        values.put("foto", objeto.getFoto());
     //   values.put("pessoa", objeto.getContato().getNome());
    //    values.put("telefone", objeto.getContato().getTelefone());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long id = db.insert("objeto_emprestado", null, values);
        objeto.setId(id);

        db.close();
    }

    public List<ObjetoEmprestado> listaTodos(){

        List<ObjetoEmprestado> objetos = new ArrayList<ObjetoEmprestado>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.query("objeto_emprestado", null, null, null, null, null, "Objeto ASC");

        try {
            while (c.moveToNext()){
                ObjetoEmprestado objeto = new ObjetoEmprestado();
                objeto.setId(c.getLong(c.getColumnIndex("_id")));
                objeto.setObjeto(c.getString(c.getColumnIndex("objeto")));
                int contatoID = c.getInt(c.getColumnIndex("contato_id"));
                Contato contato = Contatos.getContato(contatoID, context);
                objeto.setContato(contato);
                objeto.setFoto(c.getBlob(c.getColumnIndex("foto")));
            //    objeto.getContato().setNome(c.getString(c.getColumnIndex("pessoa")));
            //    objeto.getContato().setTelefone(c.getString(c.getColumnIndex("telefone")));
                objetos.add(objeto);
            }
        } finally {
            c.close();
        }

        db.close();

        return objetos;
    }

    public void atualiza(ObjetoEmprestado objeto){
        ContentValues values = new ContentValues();
        values.put("objeto", objeto.getObjeto());
     //   values.put("pessoa", objeto.getContato().getNome());
     //   values.put("telefone", objeto.getContato().getTelefone());
        values.put("contato_id", objeto.getContato().getId());
        values.put("foto", objeto.getFoto());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.update("objeto_emprestado", values, "_id=?", new String[]{objeto.getId().toString()});

        db.close();

    }

    public void salva(ObjetoEmprestado objeto){
        if(objeto.getId() == null){
            adiciona(objeto);
        }else{
            atualiza(objeto);
        }
    }

    public void remove(ObjetoEmprestado objeto){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("objeto_emprestado", "_id=?", new String[]{objeto.getId().toString()});
        db.close();

    }
}
