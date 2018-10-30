package br.com.senaijandira.alunosnode.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.com.senaijandira.alunosnode.R;
import br.com.senaijandira.alunosnode.model.Aluno;
import br.com.senaijandira.alunosnode.model.ApiResult;
import br.com.senaijandira.alunosnode.service.AlunosService;
import br.com.senaijandira.alunosnode.service.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    EditText txtNome;
    EditText txtDataNascimento;
    EditText txtMatricula;
    EditText txtCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtNome = findViewById(R.id.txtNome);
        txtDataNascimento = findViewById(R.id.txtDataNascimento);
        txtMatricula = findViewById(R.id.txtMatricula);
        txtCPF = findViewById(R.id.txtCPF);
    }

    public void cadastrarAluno(View view){
        String nome = txtNome.getText().toString();
        String dtNascimento = txtDataNascimento.getText().toString();
        String matricula = txtMatricula.getText().toString();
        String cpf = txtCPF.getText().toString();



        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setDataNascimento(Integer.parseInt(dtNascimento));
        aluno.setMatricula(Integer.parseInt(matricula));
        aluno.setCpf(cpf);

        AlunosService service = ServiceFactory.create();
        service.cadastrarAluno(aluno).enqueue(new Callback<ApiResult>(){
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                ApiResult result = response.body();

                if(result.isSucesso()){
                    alert("Sucesso", "Cadastrado com sucesso");
                }else{
                    alert("Erro", "Erro ao cadastrar");
                }
            }
            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void alert(String titulo, String mensagem) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setMessage(mensagem);
        alert.create();
        alert.show();


    }


}
