package br.com.hachitecnologia.devolvame.view;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.hachitecnologia.devolvame.dao.ObjetoEmprestadoDAO;
import br.com.hachitecnologia.devolvame.modelo.ObjetoEmprestado;
import br.com.hachitecnologia.devolvame.util.Util;
import devolvame.hachitecnologia.com.br.devolvame.R;


public class CadastraObjetoEmprestadoActivity extends Activity {

    private static final int ID_RETORNO_SELECIONA_CONTATO = 1234;
    private static final int ID_RETORNO_TIRA_FOTO_OBJETO = 5678;

    private ObjetoEmprestado objetoEmprestado;
    private EditText campoObjeto, campoPessoa, campoTelefone;
    private TextView campoNomePessoa;
    private Button botaoSelecionarContato;
    private Button botaoSalvar, botaoSair;
    private ImageView campoFotoObjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_objeto_emprestado);


        campoObjeto = (EditText) findViewById(R.id.objeto);
    //    campoPessoa = (EditText) findViewById(R.id.emprestado);
    //    campoTelefone = (EditText) findViewById(R.id.telefone);
        campoNomePessoa = (TextView) findViewById(R.id.cadastro_objeto_campo_pessoa);
        botaoSelecionarContato = (Button) findViewById(R.id.botao_selecionar_contato);
        campoFotoObjeto = (ImageView) findViewById(R.id.foto_objeto);
        botaoSalvar = (Button) findViewById(R.id.button_salvar);
        botaoSair = (Button) findViewById(R.id.button_sair);

      //  objetoEmprestado = new ObjetoEmprestado();
        objetoEmprestado = (ObjetoEmprestado) getIntent().getSerializableExtra("itemSelecionadoParaEdicao");

        if(objetoEmprestado == null){
            objetoEmprestado = new ObjetoEmprestado();
        } else{
            campoObjeto.setText(objetoEmprestado.getObjeto());
            campoFotoObjeto.setImageBitmap(Util.converteByteArrayParaBitmap(objetoEmprestado.getFoto()));

            botaoSelecionarContato.setVisibility(View.GONE);
            campoNomePessoa.setText(objetoEmprestado.getContato().getNome());
            campoNomePessoa.setVisibility(View.VISIBLE);
        //    campoPessoa.setText(objetoEmprestado.getContato().getNome());
        //    campoTelefone.setText(objetoEmprestado.getContato().getTelefone());
        }

        botaoSelecionarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setData(ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, ID_RETORNO_SELECIONA_CONTATO);
            }
        });

        campoNomePessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setData(ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, ID_RETORNO_SELECIONA_CONTATO);
            }
        });

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objetoEmprestado.setObjeto(campoObjeto.getText().toString());
         //       objetoEmprestado.getContato().setNome(campoPessoa.getText().toString());
         //       objetoEmprestado.getContato().setTelefone(campoTelefone.getText().toString());

                ObjetoEmprestadoDAO dao = new ObjetoEmprestadoDAO(getApplicationContext());
                dao.salva(objetoEmprestado);

                startActivity(new Intent(getApplicationContext(), ListaObjetosEmprestadosActivity.class));

                Toast.makeText(getApplicationContext(), "Objeto salvo com sucesso!", Toast.LENGTH_LONG).show();
            }
        });

        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        campoFotoObjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(i, ID_RETORNO_TIRA_FOTO_OBJETO);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case(ID_RETORNO_SELECIONA_CONTATO):
                if(resultCode == Activity.RESULT_OK){
                    Uri contactData = data.getData();
                    ContentResolver contentResolver = getContentResolver();

                    Cursor cursor = contentResolver.query(contactData, null, null, null, null);

                    if (cursor.moveToFirst()){
                        objetoEmprestado.getContato().setId(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)));
                        objetoEmprestado.getContato().setNome(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));

                        botaoSelecionarContato.setVisibility(View.GONE);

                        campoNomePessoa.setText(objetoEmprestado.getContato().getNome());
                        campoNomePessoa.setVisibility(View.VISIBLE);
                    }
                    cursor.close();
                }
                break;
            case ID_RETORNO_TIRA_FOTO_OBJETO:
                if(data != null){
                    Bundle bundle = data.getExtras();
                    if(bundle != null){
                        Bitmap foto = (Bitmap) bundle.get("data");
                        campoFotoObjeto.setImageBitmap(foto);
                        objetoEmprestado.setFoto(Util.converteBitmapParaByteArray(foto, 70));
                    }
                }
                break;
        }
    }
}
