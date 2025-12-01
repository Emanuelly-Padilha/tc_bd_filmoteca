package br.edu.ifpr.controller;

import java.util.List;

import br.edu.ifpr.model.Filme;
import br.edu.ifpr.model.dao.FilmeDAO;

public class FilmeController {
      FilmeDAO dao = new FilmeDAO();

    public void cadastrar(Filme f) {
        dao.inserir(f);
    }

    public List<Filme> listar() {
        return dao.listar();
    }
}
