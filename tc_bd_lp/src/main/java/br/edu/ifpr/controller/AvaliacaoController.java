package br.edu.ifpr.controller;

import java.util.List;

import br.edu.ifpr.model.Avaliacao;
import br.edu.ifpr.model.dao.AvaliacaoDAO;

public class AvaliacaoController {

    AvaliacaoDAO dao = new AvaliacaoDAO();

    public void avaliar(Avaliacao a) {
        dao.inserir(a);
    }

    public List<Avaliacao> listarPorUsuario(String nomeUsuario) {

        return dao.listarPorUsuario(nomeUsuario);
    }

    public void remover(String nomeUsuario, String nomeFilme) {
        dao.remover(nomeUsuario, nomeFilme);
    }

      public void editar(Avaliacao avaliacao) {
        dao.atualizar(avaliacao);
    }
}
