package br.com.senaijandira.alunosnode.view;

import java.util.List;

import br.com.senaijandira.alunosnode.model.Aluno;

public interface MainView {

    void preencherLista(List<Aluno> lstAluno);

    void exibirBarraProgresso();

    void esconderBarraProgesso();



}
