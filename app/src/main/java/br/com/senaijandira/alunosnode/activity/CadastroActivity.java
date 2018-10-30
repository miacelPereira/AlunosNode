package br.com.senaijandira.alunosnode.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import br.com.senaijandira.alunosnode.R;
import br.com.senaijandira.alunosnode.model.Aluno;
import br.com.senaijandira.alunosnode.presenter.CadastroPresenter;
import br.com.senaijandira.alunosnode.service.AlunosService;
import br.com.senaijandira.alunosnode.service.ServiceFactory;
import br.com.senaijandira.alunosnode.view.CadastroView;
import util.DateUtil;

public class CadastroActivity extends AppCompatActivity implements CadastroView {

    static EditText txtNome, txtDataNascimento, txtMatricula, txtCPF;
    AlunosService service = ServiceFactory.create();
    CadastroPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        presenter = new CadastroPresenter(this, service);

        txtNome = findViewById(R.id.txtNome);
        txtDataNascimento = findViewById(R.id.txtDataNascimento);
        txtMatricula = findViewById(R.id.txtMatricula);
        txtCPF = findViewById(R.id.txtCPF);

    }

    public void cadastrarAluno(View view){
        String nome = txtNome.getText().toString();
        String dtNascimento = txtDataNascimento.getText().toString();
        int dataFormatada = new DateUtil().convertToInt(dtNascimento);
        String matricula = txtMatricula.getText().toString();
        String cpf = txtCPF.getText().toString();

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setDataNascimento(dataFormatada);
        aluno.setMatricula(Integer.parseInt(matricula));
        aluno.setCpf(cpf);

        presenter.cadastrarAluno(aluno);

    }

    public void abrirCalendario(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void showMessage(String titulo, String mensagem) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setMessage(mensagem);
        alert.create();
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.show();
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            /* Do something with the date chosen by the user
            String dia = String.format("%02d", day);
            String mes = String.format("%02d", month+1);
            String ano = String.format("%d", year);*/

            String data = String.format("%02d/%02d/%d", day, month, year); //(dia + "/" + mes+ "/" + ano);
            txtDataNascimento.setText(data);

        }
    }
}
