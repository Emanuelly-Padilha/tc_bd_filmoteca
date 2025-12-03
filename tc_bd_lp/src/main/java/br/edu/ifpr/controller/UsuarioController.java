package br.edu.ifpr.controller;

import java.util.List;

import br.edu.ifpr.model.Usuario;
import br.edu.ifpr.model.dao.UsuarioDAO;

public class UsuarioController {

    UsuarioDAO dao = new UsuarioDAO();

    public void cadastrar(Usuario u) {
        dao.inserir(u);
    }

    public List<Usuario> listar() {
        return dao.listarTodos();
    }

    public Usuario login(String email, String senha) {

        return dao.login(email, senha);
    }

    public void remover(String nomeUsuario) {
        dao.remover(nomeUsuario);
    }
}