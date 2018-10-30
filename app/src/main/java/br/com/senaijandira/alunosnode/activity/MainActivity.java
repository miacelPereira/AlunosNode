package br.com.senaijandira.alunosnode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import br.com.senaijandira.alunosnode.R;
import br.com.senaijandira.alunosnode.adapter.AlunoAdapter;
import br.com.senaijandira.alunosnode.model.Aluno;
import br.com.senaijandira.alunosnode.presenter.MainPresenter;
import br.com.senaijandira.alunosnode.service.ServiceFactory;
import br.com.senaijandira.alunosnode.view.MainView;

public class MainActivity extends AppCompatActivity  implements MainView, AdapterView.OnItemClickListener {

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
        listView.setOnClickListener((View.OnClickListener) this);

        //Configurando o presenter e carregando os alunos
        presenter = new MainPresenter(this, ServiceFactory.create());
        presenter.carregarAlunos();
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Aluno alunoSelecionado = adapter.getItem(position);
        Intent intent = new Intent(this,VisualizarActivity.class);
        intent.putExtra("idAluno", alunoSelecionado.getId());
        startActivity(intent);
    }
}
