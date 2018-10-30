package br.com.senaijandira.alunosnode.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.senaijandira.alunosnode.R;
import br.com.senaijandira.alunosnode.model.Aluno;

public class AlunoAdapter extends ArrayAdapter<Aluno>{


    public AlunoAdapter(Context ctx){
        //Construtor padrão do Adapter, criando uma lista vazia
        super(ctx, 0, new ArrayList<Aluno>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v==null){
             //Inflando o layout no adapter
             v = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
         }

        Aluno aluno = getItem(position);

        //Pegando os itens e setando os valores para os valores que vem da API
        TextView txtNomeAluno = v.findViewById(R.id.txtNomeAluno);
        TextView txtMatriculaAluno= v.findViewById(R.id.txtMatriculaAluno);
        txtNomeAluno.setText(aluno.getNome());
        txtMatriculaAluno.setText(aluno.getMatricula()+"");

        return(v);
    }
}
