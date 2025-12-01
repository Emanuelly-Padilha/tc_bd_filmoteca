package br.edu.ifpr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Watchlist;

public class WatchlistDAO {
    // Criar objetos para acessar outros DAOs
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    FilmeDAO filmeDAO = new FilmeDAO();

    // Método para adicionar um filme à watchlist
    public void inserir(Watchlist w) {
        // Primeiro: buscar o ID do usuário pelo nome
        int usuarioId = usuarioDAO.buscarIdPorNome(w.getNomeUsuario());
        
        // Segundo: buscar o ID do filme pelo título
        int filmeId = filmeDAO.buscarIdPorTitulo(w.getNomeFilme());

        // SQL para inserir na tabela watchlist
        String sql = "INSERT INTO watchlist (id_usuario, id_filme) VALUES (?, ?)";

        try {
            // Conectar ao banco de dados
            Connection conn = ConnectionFactory.getConnection();
            
            // Preparar o comando SQL
            PreparedStatement ps = conn.prepareStatement(sql);
            
            // Colocar os valores no SQL (substituir os ?)
            ps.setInt(1, usuarioId);      // Primeiro ? = id do usuário
            ps.setInt(2, filmeId);        // Segundo ? = id do filme
            
            // Executar o comando
            ps.executeUpdate();
            
            // Fechar as conexões
            ps.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à watchlist: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para listar a watchlist de um usuário
    public List<Watchlist> listarPorUsuario(String nomeUsuario) {
        // Primeiro: buscar o ID do usuário
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
            // Conectar ao banco de dados
            Connection conn = ConnectionFactory.getConnection();
            
            // Preparar o comando SQL
            PreparedStatement ps = conn.prepareStatement(sql);
            
            // Colocar o ID do usuário no SQL
            ps.setInt(1, usuarioId);
            
            // Executar a consulta e obter os resultados
            ResultSet rs = ps.executeQuery();

            // Percorrer todos os resultados
            while (rs.next()) {
                // Para cada filme encontrado, criar um objeto Watchlist
                Watchlist item = new Watchlist(nomeUsuario, rs.getString("filme"));
                
                // Adicionar à lista
                lista.add(item);
            }
            
            // Fechar as conexões
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao listar watchlist: " + e.getMessage());
            e.printStackTrace();
        }

        // Retornar a lista com os filmes
        return lista;
    }
}