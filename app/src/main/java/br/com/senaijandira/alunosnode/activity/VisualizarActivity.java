package br.com.senaijandira.alunosnode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.senaijandira.alunosnode.R;
import br.com.senaijandira.alunosnode.model.Aluno;
import br.com.senaijandira.alunosnode.presenter.VisualizarPresenter;
import br.com.senaijandira.alunosnode.service.AlunosService;
import br.com.senaijandira.alunosnode.service.ServiceFactory;
import br.com.senaijandira.alunosnode.view.VisualizarView;
import util.DateUtil;

public class VisualizarActivity extends AppCompatActivity implements VisualizarView {

    Aluno aluno;
    AlunosService service;
    VisualizarPresenter presenter;
    DateUtil util;
    int idAluno;

    TextView txtNomeResult, txtdtNascimentoResult, txtMatriculaResult, txtCpfResult, txtNotaResult;
    Button btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);
        idAluno = getIntent().getIntExtra("idAluno", 0);

        service = ServiceFactory.create();
        presenter = new VisualizarPresenter(this, service);

        util = new DateUtil();

        aluno = new Aluno();
        txtNomeResult = findViewById(R.id.txtNomeResult);
        txtdtNascimentoResult = findViewById(R.id.txtdtNascimentoResult);
        txtMatriculaResult = findViewById(R.id.txtMatriculaResult);
        txtCpfResult = findViewById(R.id.txtCpfResult);
        txtNotaResult = findViewById(R.id.txtNotaResult);
        btnBackHome = findViewById(R.id.btnBackHome);
        presenter.carregarAluno(idAluno);



    }

    public void backHome(View v){
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void preencherCampos(Aluno aluno) {
        //* Setar campos aqui *//
        txtNomeResult.setText(aluno.getNome());
        txtdtNascimentoResult.setText(util.convertToString(aluno.getDataNascimento()));
        txtMatriculaResult.setText(aluno.getMatricula()+"");
        txtCpfResult.setText(aluno.getCpf());
        txtNotaResult.setText(aluno.calcularMedia().toString());

    }
}
