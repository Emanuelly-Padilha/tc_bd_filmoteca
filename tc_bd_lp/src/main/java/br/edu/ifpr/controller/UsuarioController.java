package br.edu.ifpr.controller;

import java.util.List;

import br.edu.ifpr.dao.UsuarioDAO;
import br.edu.ifpr.model.Usuario;

public class UsuarioController {
    
    UsuarioDAO dao = new UsuarioDAO();

    public void cadastrar(Usuario u) {
        dao.inserir(u);
    }

    public List<Usuario> listar() {
        return dao.listar();
    }

    public static void login(String email,String senha){

        dao.login(email, senha);
    }
}
