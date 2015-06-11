package br.com.hachitecnologia.devolvame.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;
import devolvame.hachitecnologia.com.br.devolvame.R;

/**
 * Created by iaufc26.costa on 26/05/2015.
 */
public class ListaObjetosEmprestadosAdapter extends ArrayAdapter<ObjetoEmprestado> {

    private final List<ObjetoEmprestado> objetosEmprestados;
    private final Activity activity;

    public ListaObjetosEmprestadosAdapter(Activity activity, int textViewResourceId, List<ObjetoEmprestado> objetosEmprestados) {
        super(activity, textViewResourceId, objetosEmprestados);
        this.objetosEmprestados = objetosEmprestados;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ObjetoEmprestado objetoEmprestado = objetosEmprestados.get(position);

        View view = activity.getLayoutInflater().inflate(R.layout.item_lista_objetos_emprestados, null);

        TextView objeto = (TextView) view.findViewById(R.id.item_objeto_emprestado_nome);
        objeto.setText(objetoEmprestado.getObjeto());

        TextView pessoa = (TextView) view.findViewById(R.id.item_objeto_emprestado_pessoa);
        objeto.setText(objetoEmprestado.getContato().getNome());

        return view;
    }

    @Override
    public long getItemId(int position){
        return objetosEmprestados.get(position).getId();
    }

    @Override
    public int getCount(){
        return super.getCount();
    }

    @Override
    public ObjetoEmprestado getItem(int position){
        return objetosEmprestados.get(position);
    }

}
