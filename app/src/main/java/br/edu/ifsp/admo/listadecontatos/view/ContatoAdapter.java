package br.edu.ifsp.admo.listadecontatos.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.edu.ifsp.admo.listadecontatos.R;
import br.edu.ifsp.admo.listadecontatos.model.Contato;

public class ContatoAdapter extends ArrayAdapter<Contato> {
    public ContatoAdapter(@NonNull Context context, @NonNull List<Contato> objects) {
        super(context, R.layout.item_list_contato, objects);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int positon, @Nullable View converterView, @Nullable ViewGroup parent) {
        if (converterView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converterView = inflater.inflate(R.layout.item_list_contato, null);

        }
        TextView nome = (TextView)
                converterView.findViewById(R.id.text_nome);
        TextView fone = (TextView)
                converterView.findViewById(R.id.text_numero);
        Contato contato = getItem(positon);
        nome.setText(contato.getNome());
        fone.setText(contato.getTelefone());
        Log.v("LISTA_CONTATOS", "Inserido na listview: " + contato.toString());
        return converterView;
    }
}



