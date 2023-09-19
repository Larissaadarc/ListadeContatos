package br.edu.ifsp.admo.listadecontatos.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import br.edu.ifsp.admo.listadecontatos.R;
import br.edu.ifsp.admo.listadecontatos.model.Contato;
import br.edu.ifsp.admo.listadecontatos.model.ContatoDao;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "LISTA_CONTATOS";
    private Button button;
    private ListView listView;
    private ContatoDao contatoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "Executado onCreate().");

        contatoDao = ContatoDao.getInstance();
        button = findViewById(R.id.btn_novo);
        button.setOnClickListener(v -> novoContato());

        listView = findViewById(R.id.Listview_contato);
        ContatoAdapter contatoAdapter = new ContatoAdapter(this, contatoDao.getDataset());
        listView.setAdapter((contatoAdapter);
        listView.setOnItemClickListener(this);
    }
    @Override
    protected void onStart(){
        Log.v(TAG, "Executando onStart().");
        super.onStart();
    }
    @Override
    protected void onResume(){
        Log.v(TAG, "Executado onResume().");
        super.onResume();
    }
    @Override
    protected void onPause(){
        Log.v(TAG, "Executando onPause().");
        super.onPause();
    }
    @Override
    protected void onStop(){
        Log.v(TAG, "Executando onStop().");
        super.onStop();
    }

    @Override
    protected void onRestart(){
        Log.v(TAG, "Executando onRestart().");
        super.onRestart();
    }
    @Override
    protected void onDestroy(){
        Log.v(TAG, "Executando onDestroy().");
        super.onDestroy();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Contato contato =
                ContatoDao.getInstance().getDataset().get(position);
        String uri = "tel:" + contato.getTelefone();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);

    }
    private void novoContato(){
        LayoutInflater inflater = getLayoutInflater();
        View viewInfaled = inflater.inflate(R.layout.novo_contato, null);
        EditText nomeEditText =
                viewInfaled.findViewById(R.id.edittext_nome);
        EditText foneEditText =
                viewInfaled.findViewById(R.id.edittext_telefone);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewInfaled)
                .setTitle(R.string.novo_contato)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.v(TAG, "Salvar contato:" + nomeEditText.getText().toString() + ", " +
                                        foneEditText.getText().toString());
                                        contatoDao.add(new Contato(nomeEditText.getText().toString(),
                                        foneEditText.getText().toString()));
                                        dialogInterface.dismiss();
                            }
                        })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}