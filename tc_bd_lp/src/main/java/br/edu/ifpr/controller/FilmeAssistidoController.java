package br.edu.ifpr.controller;

import java.util.List;

import br.edu.ifpr.dao.FilmeAssistidoDAO;
import br.edu.ifpr.model.FilmeAssistido;

public class FilmeAssistidoController {
    
    FilmeAssistidoDAO dao = new FilmeAssistidoDAO();

    public void registrar(FilmeAssistido fa) {
        dao.inserir(fa);
    }

    public List<FilmeAssistido> listar(String nomeUsuario) {
        return dao.listar(nomeUsuario);
    }
}
