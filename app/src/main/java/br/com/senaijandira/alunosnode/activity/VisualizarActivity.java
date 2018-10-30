package br.com.senaijandira.alunosnode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.senaijandira.alunosnode.R;

public class VisualizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);
        int idAluno = getIntent().getIntExtra("idAluno", 0);


    }
}
