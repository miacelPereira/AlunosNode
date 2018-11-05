package br.com.senaijandira.alunosnode.presenter;

import android.util.Log;

import java.util.List;

import br.com.senaijandira.alunosnode.view.MainView;
import br.com.senaijandira.alunosnode.model.Aluno;
import br.com.senaijandira.alunosnode.service.AlunosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    MainView mainView;
    AlunosService service;

    public MainPresenter(MainView mainView, AlunosService service){
        this.mainView = mainView;
        this.service = service;
    }

    public void carregarAlunos() {
        mainView.exibirBarraProgresso();
        Call<List<Aluno>> call = service.obterAlunos();

        //Executar a chamada da Api
        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                List<Aluno> alunos = response.body(); // Pegando as informações da Api
                mainView.preencherLista(alunos);
                mainView.esconderBarraProgesso();
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) { //Se falhar vem para cá
                Log.e("ALUNOS", t.getMessage()); // Pegando o erro
            }
        });

    }

}
