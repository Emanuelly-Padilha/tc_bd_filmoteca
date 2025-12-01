package br.edu.ifpr.controller;

import java.util.List;

import br.edu.ifpr.dao.UsuarioDAO;
import br.edu.ifpr.model.Usuario;

public class UsuarioController {
    
    // Cria um objeto UsuarioDAO para usar
    UsuarioDAO dao = new UsuarioDAO();

    // Método para cadastrar usuário
    public void cadastrar(Usuario u) {
        dao.inserir(u);
    }

    // Método para listar todos os usuários
    public List<Usuario> listar() {
        return dao.listar();
    }

    // Método para fazer login - NÃO É MAIS 'static'
    public  Usuario login(String email, String senha) {
        // Chama o método login do DAO
        return dao.login(email, senha);
    }
}