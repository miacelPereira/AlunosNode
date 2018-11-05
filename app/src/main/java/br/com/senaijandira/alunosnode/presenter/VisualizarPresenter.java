package br.com.senaijandira.alunosnode.presenter;


import android.util.Log;

import java.util.List;

import br.com.senaijandira.alunosnode.model.Aluno;
import br.com.senaijandira.alunosnode.service.AlunosService;
import br.com.senaijandira.alunosnode.view.MainView;
import br.com.senaijandira.alunosnode.view.VisualizarView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarPresenter {

    VisualizarView visualizarViewView;
    AlunosService service;
    int idAluno;

    public VisualizarPresenter(VisualizarView visualizarViewView, AlunosService service){
        this.visualizarViewView = visualizarViewView;
        this.service = service;
        this.idAluno = idAluno;
    }

    public void carregarAluno(int idAluno) {
        Call<Aluno> call = service.obterAlunosPorId(idAluno);

        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                Aluno aluno = response.body();
                visualizarViewView.preencherCampos(aluno);
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                Log.e("ALUNO", t.getMessage());
            }
        });

    }
}
