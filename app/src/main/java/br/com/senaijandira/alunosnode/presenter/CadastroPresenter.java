package br.com.senaijandira.alunosnode.presenter;

import android.content.Context;

import br.com.senaijandira.alunosnode.model.Aluno;
import br.com.senaijandira.alunosnode.model.ApiResult;
import br.com.senaijandira.alunosnode.service.AlunosService;
import br.com.senaijandira.alunosnode.view.CadastroView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroPresenter {

    CadastroView view;
    AlunosService service;

    public CadastroPresenter(Context ctx, AlunosService service) {
        this.service = service;
        this.view = view;
    }

    public void cadastrarAluno(Aluno aluno){
        service.cadastrarAluno(aluno).enqueue(new Callback<ApiResult>(){
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                ApiResult result = response.body();
                if(result.isSucesso()){
                    view.showMessage("Sucesso", "Cadastrado com sucesso");
                }else{
                    view.showMessage("Erro", "Erro ao cadastrar");
                }
            }
            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
