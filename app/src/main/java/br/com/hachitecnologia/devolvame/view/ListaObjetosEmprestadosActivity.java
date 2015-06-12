package br.com.hachitecnologia.devolvame.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.hachitecnologia.devolvame.adapter.ListaObjetosEmprestadosAdapter;
import br.com.hachitecnologia.devolvame.dao.ObjetoEmprestadoDAO;
import br.com.hachitecnologia.devolvame.listener.ListaObjetosEmprestadosListener;
import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;
import br.com.hachitecnologia.devolvame.util.Telefonia;
import devolvame.hachitecnologia.com.br.devolvame.R;

/**
 * Created by Glailton Costa on 26/05/2015. For Devolva.me
 */
public class ListaObjetosEmprestadosActivity extends Activity {

    private ListView listaObjetosEmpregados;

    //ID da op��o "Apagar" no menu de contexto
    private static final int MENU_APAGAR = Menu.FIRST;
    private static final int MENU_LIGAR = Menu.FIRST + 1;
    private static final int MENU_ENVIAR_SMS = Menu.FIRST + 2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_objetos_emprestados);

        listaObjetosEmpregados = (ListView) findViewById(R.id.lista_objetos_empretados);

        registerForContextMenu(listaObjetosEmpregados);
    }

    public ListView getListaObjetosEmpregados() {
        return listaObjetosEmpregados;
    }

    @Override
    protected void onResume(){
        super.onResume();

        montaListView();
    }

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, MENU_APAGAR, 0, R.string.apagar);
        menu.add(0, MENU_LIGAR, 0, R.string.ligar);
        menu.add(0, MENU_ENVIAR_SMS, 0, R.string.enviar_sms);


    }

    private void montaListView(){
        ObjetoEmprestadoDAO dao = new ObjetoEmprestadoDAO(getApplicationContext());

        final List<ObjetoEmprestado> objetoEmprestados = dao.listaTodos();

        ArrayAdapter<ObjetoEmprestado> adapter = new ListaObjetosEmprestadosAdapter(this, android.R.layout.simple_list_item_1, objetoEmprestados);

        listaObjetosEmpregados.setAdapter(adapter);

        listaObjetosEmpregados.setOnItemClickListener(new ListaObjetosEmprestadosListener(this));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == MENU_APAGAR){

            ObjetoEmprestado objeto = (ObjetoEmprestado) getListaObjetosEmpregados().getItemAtPosition(info.position);

            remove(objeto);

            Toast.makeText(getApplicationContext(), "Registro removido com sucesso!", Toast.LENGTH_LONG).show();

            return true;
        }

        if (item.getItemId() == MENU_LIGAR){
            ObjetoEmprestado objeto = (ObjetoEmprestado) getListaObjetosEmpregados().getItemAtPosition(info.position);

            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:" + objeto.getContato().getTelefone()));
            startActivity(i);
        }

        if (item.getItemId() == MENU_ENVIAR_SMS){
            ObjetoEmprestado objeto = (ObjetoEmprestado) getListaObjetosEmpregados().getItemAtPosition(info.position);

        /*    Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("sms:" + objeto.getContato().getTelefone()));
            i.putExtra("sms_body", "Olá! Você pegou emprestado meu objeto \"" + objeto.getObjeto() + "\" e ainda não devolveu. Por favor devolva-me o quanto antes.");
            startActivity(i);*/

            String mensagem = "Olá! Você pegou emprestado meu objeto \"" + objeto.getObjeto() + "\" e ainda não devolveu. Por favor devolva-me o quanto antes.";
            Telefonia.enviaSMS(objeto.getContato().getTelefone(), mensagem);
            Toast.makeText(getApplicationContext(), "Lembrete enviado", Toast.LENGTH_LONG).show();
        }

        return super.onContextItemSelected(item);
    }

    private void remove(ObjetoEmprestado objeto){
        ObjetoEmprestadoDAO dao = new ObjetoEmprestadoDAO(getApplicationContext());
        dao.remove(objeto);
        montaListView();
    }


}
