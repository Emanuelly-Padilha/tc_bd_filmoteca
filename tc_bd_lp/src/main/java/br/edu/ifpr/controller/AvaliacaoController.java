package br.edu.ifpr.controller;

import br.edu.ifpr.dao.AvaliacaoDAO;
import br.edu.ifpr.model.Avaliacao;

public class AvaliacaoController {
    
    AvaliacaoDAO dao = new AvaliacaoDAO();

    public void avaliar(Avaliacao a) {
        dao.inserir(a);
    }
}
