package br.com.hachitecnologia.devolvame.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;
import br.com.hachitecnologia.devolvame.view.CadastraObjetoEmprestadoActivity;
import br.com.hachitecnologia.devolvame.view.ListaObjetosEmprestadosActivity;

/**
 * Created by iaufc26.costa on 27/05/2015.
 */
public class ListaObjetosEmprestadosListener implements AdapterView.OnItemClickListener{

    private final ListaObjetosEmprestadosActivity activity;

    public ListaObjetosEmprestadosListener(ListaObjetosEmprestadosActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent i = new Intent(activity, CadastraObjetoEmprestadoActivity.class);

        i.putExtra("itemSelecionadoParaEdicao",
                (ObjetoEmprestado)activity.getListaObjetosEmpregados().getItemAtPosition(position));

        activity.startActivity(i);

    }
}
