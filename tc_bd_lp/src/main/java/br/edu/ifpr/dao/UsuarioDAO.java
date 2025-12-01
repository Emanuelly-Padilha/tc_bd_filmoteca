package br.edu.ifpr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Usuario;

public class UsuarioDAO {
    
    // Método para cadastrar um novo usuário
    public void inserir(Usuario u) { 
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getSenha());
            
            ps.executeUpdate();
            
            System.out.println("Usuário cadastrado: " + u.getNome());
            
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int buscarIdPorNome(String nome) {
        String sql = "SELECT id FROM usuarios WHERE nome = ?";
        int id = -1;

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, nome);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
                System.out.println("Encontrado usuário " + nome + " com ID: " + id);
            } else {
                System.out.println("Usuário não encontrado: " + nome);
            }
            
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar ID do usuário: " + e.getMessage());
            e.printStackTrace();
        }
        
        return id;
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<Usuario>();
        String sql = "SELECT id, nome, email, senha FROM usuarios";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                
                Usuario usuario = new Usuario(id, nome, email, senha);
                lista.add(usuario);
                
                System.out.println("Usuário listado: " + nome);
            }
            
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
            e.printStackTrace();
        }
        
        return lista;
    }

    public Usuario login(String email, String senha) {
        String sql = "SELECT id, nome, email, senha FROM usuarios WHERE email = ? AND senha = ?";
        Usuario usuario = null;

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String emailBanco = rs.getString("email");
                String senhaBanco = rs.getString("senha");
                
                usuario = new Usuario(id, nome, emailBanco, senhaBanco);
                System.out.println("Login bem-sucedido para: " + nome);
            } else {
                System.out.println("Login falhou para email: " + email);
            }
            
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro no login: " + e.getMessage());
            e.printStackTrace();
        }
        
        return usuario;
    }
    
    
}