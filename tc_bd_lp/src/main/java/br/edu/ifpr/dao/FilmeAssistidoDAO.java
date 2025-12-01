package br.edu.ifpr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.FilmeAssistido;

public class FilmeAssistidoDAO {
    
    // Objetos para acessar outros DAOs
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    FilmeDAO filmeDAO = new FilmeDAO();

    // Método para registrar um filme como assistido
    public void inserir(FilmeAssistido fa) {
        
        // 1. Buscar o ID do usuário pelo nome
        int usuarioId = usuarioDAO.buscarIdPorNome(fa.getNomeUsuario());
        
        // 2. Buscar o ID do filme pelo título
        int filmeId = filmeDAO.buscarIdPorTitulo(fa.getNomeFilme());

        // 3. SQL para inserir na tabela filmes_assistidos
        String sql = "INSERT INTO filmes_assistidos (id_usuario, id_filme, data_assistido) VALUES (?, ?, ?)";

        try {
            // 4. Conectar ao banco de dados
            Connection conn = ConnectionFactory.getConnection();
            
            // 5. Preparar o comando SQL
            PreparedStatement ps = conn.prepareStatement(sql);
            
            // 6. Colocar os valores no SQL
            ps.setInt(1, usuarioId);                // Primeiro ? = id do usuário
            ps.setInt(2, filmeId);                  // Segundo ? = id do filme
            ps.setString(3, fa.getDataAssistido()); // Terceiro ? = data
            
            // 7. Executar o comando
            int linhasAfetadas = ps.executeUpdate();
            
            if (linhasAfetadas > 0) {
                System.out.println("Filme registrado como assistido: " + fa.getNomeFilme());
            }
            
            // 8. Fechar conexões
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao registrar filme assistido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    public List<FilmeAssistido> listar(String nomeUsuario) {
        
        // buscar id do usuario
        int usuarioId = usuarioDAO.buscarIdPorNome(nomeUsuario);
        
        // cria lista p armazenar os resultados
        List<FilmeAssistido> lista = new ArrayList<FilmeAssistido>();

        //buscar filmes assistidos
        String sql = "SELECT f.titulo AS filme, fa.data_assistido " +
                     "FROM filmes_assistidos fa " +
                     "JOIN filmes f ON f.id = fa.id_filme " +
                     "WHERE fa.id_usuario = ? " +
                     "ORDER BY fa.data_assistido DESC";

        try {
            //conecta no banco
            Connection conn = ConnectionFactory.getConnection();
            
            // 5. Preparar comando SQL
            PreparedStatement ps = conn.prepareStatement(sql);
            
            // 6. Colocar ID do usuário no SQL
            ps.setInt(1, usuarioId);
            
            // 7. Executar consulta
            ResultSet rs = ps.executeQuery();

            // 8. Percorrer resultados
            int contador = 0;
            while (rs.next()) {
                // Pegar dados do banco
                String nomeFilme = rs.getString("filme");
                String dataAssistido = rs.getString("data_assistido");
                
                // Criar objeto FilmeAssistido
                FilmeAssistido filme = new FilmeAssistido(nomeUsuario, nomeFilme, dataAssistido);
                
                // Adicionar à lista
                lista.add(filme);
                contador++;
            }
            
            System.out.println("Encontrados " + contador + " filmes assistidos para " + nomeUsuario);
            
            // 9. Fechar conexões
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao listar filmes assistidos: " + e.getMessage());
            e.printStackTrace();
        }

        // 10. Retornar a lista
        return lista;
    }
}