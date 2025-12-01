package br.edu.ifpr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Usuario;

public class UsuarioDAO {
    String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

    try(
    Connection conn = ConnectionFactory.getConnection();
    PreparedStatement ps = conn.prepareStatement(sql))
    {

        ps.setString(1, u.getNome());
        ps.setString(2, u.getEmail());
        ps.setString(3, u.getSenha());

        ps.executeUpdate();

    }catch(
    SQLException e)
    {
        e.printStackTrace();
    }
    }

    public int buscarIdPorNome(String nome) {
        String sql = "SELECT id FROM usuarios WHERE nome = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; 
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT nome, email FROM usuarios";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        "******"
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

        public static Usuario login(String email, String senha) {
            String sql = "SELECT nome, email, senha FROM usuarios WHERE email = ? AND senha = ?";
        
            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
        
                ps.setString(1, email);
                ps.setString(2, senha);
        
                ResultSet rs = ps.executeQuery();
        
                if (rs.next()) {
                    return new Usuario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                }
        
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null; 
        }

      
    }
