package br.com.senaijandira.alunosnode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import br.com.senaijandira.alunosnode.view.MainView;
import br.com.senaijandira.alunosnode.R;
import br.com.senaijandira.alunosnode.adapter.AlunoAdapter;
import br.com.senaijandira.alunosnode.model.Aluno;
import br.com.senaijandira.alunosnode.presenter.MainPresenter;
import br.com.senaijandira.alunosnode.service.ServiceFactory;

public class MainActivity extends AppCompatActivity  implements MainView {

    ListView listView;
    AlunoAdapter adapter;
    ProgressBar progressBar;
    MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progressBar);

        //Setando os valores que veio da API para o adapter
        listView = findViewById(R.id.listView);
        adapter = new AlunoAdapter(this);
        listView.setAdapter(adapter);

        //Configurando o presenter e carregando os alunos
        presenter = new MainPresenter(this, ServiceFactory.create());
        presenter.carregarAlunos();
    }

    @Override
    public void preencherLista(List<Aluno> lstAluno){
        adapter.clear();
        adapter.addAll(lstAluno);
    }


    @Override
    public void exibirBarraProgresso(){
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void esconderBarraProgesso(){
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    public void abrirCadastro(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }
}
