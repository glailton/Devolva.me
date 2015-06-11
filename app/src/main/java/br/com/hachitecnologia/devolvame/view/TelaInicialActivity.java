package br.com.hachitecnologia.devolvame.view;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import devolvame.hachitecnologia.com.br.devolvame.R;

/**
 * Created by iaufc26.costa on 27/05/2015.
 */
public class TelaInicialActivity extends ListActivity {

    private static final String[] OPCOES_DO_MENU = new String[]{
            "Emprestar objeto", "Listar objetos emprestados", "Sair"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, OPCOES_DO_MENU));

        //setContentView(R.layout.activity_tela_inicial);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position){
            case 0:
            startActivity(new Intent("br.com.hachitecnologia.devolvame.action.CADASTRA_OBJETO"));
                break;
            case 1:
                startActivity(new Intent("br.com.hachitecnologia.devolvame.action.LISTA_OBJETOS"));
                break;
            default:
                finish();
        }
    }
}
