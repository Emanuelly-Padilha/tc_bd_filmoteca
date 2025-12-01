package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Usuario;

public class UsuarioDAO {
    
    public void inserir(Usuario u) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            if (conn == null) {
                System.out.println("Não conectou no banco");
                return;
            }
            
            String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getSenha());
            
            //executa o insert
            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
            
            //forcar o banco
            conn.commit();
            System.out.println("Dados salvos");
            
            // Verificar se realmente salvou
            if (linhas > 0) {
                System.out.println("Usuário '" + u.getNome() + "' salvo no MySQL");
            }
            
        } catch (SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
            
            // Se der erro, desfazer (rollback)
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Erro no rollback: " + e2.getMessage());
            }
            
        } finally {
            // Fechar conexões
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar: " + e.getMessage());
            }
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
    
