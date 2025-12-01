package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Watchlist;

public class WatchlistDAO {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    FilmeDAO filmeDAO = new FilmeDAO();

    public void inserir(Watchlist w) {
       
        int usuarioId = usuarioDAO.buscarIdPorNome(w.getNomeUsuario());

        
        int filmeId = filmeDAO.buscarIdPorTitulo(w.getNomeFilme());

        String sql = "INSERT INTO watchlist (id_usuario, id_filme) VALUES (?, ?)";

        try {

            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

         
            ps.setInt(1, usuarioId); 
            ps.setInt(2, filmeId);

            
            ps.executeUpdate();

           
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à watchlist: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //  listar a watchlist do usuário
    public List<Watchlist> listarPorUsuario(String nomeUsuario) {
        
        int usuarioId = usuarioDAO.buscarIdPorNome(nomeUsuario);

        // Criar uma lista vazia para armazenar os resultados
        List<Watchlist> lista = new ArrayList<Watchlist>();

        // SQL para buscar os filmes da watchlist do usuário
        // JOIN: junta a tabela watchlist com a tabela filmes
        String sql = "SELECT f.titulo AS filme " +
                "FROM watchlist w " +
                "JOIN filmes f ON f.id = w.id_filme " +
                "WHERE w.id_usuario = ?";

        try {
          
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
         
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            // Percorrer todos os resultados
            while (rs.next()) {
                // Para cada filme encontrado, criar um objeto Watchlist
                Watchlist item = new Watchlist(nomeUsuario, rs.getString("filme"));

                lista.add(item);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao listar watchlist: " + e.getMessage());
            e.printStackTrace();
        }

        
        return lista;
    }
}