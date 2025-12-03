package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifpr.model.Avaliacao;
import br.edu.ifpr.model.Usuario;

public class AvaliacaoDAO {
     UsuarioDAO usuarioDAO = new UsuarioDAO();
    FilmeDAO filmeDAO = new FilmeDAO();

  public void inserir(Avaliacao a) {
    // Primeiro busca o usuário
    Usuario usuario = usuarioDAO.buscarPorNome(a.getNomeUsuario());
    
    // Verifica se encontrou o usuário
    if (usuario == null) {
        System.out.println("Erro: Usuário não encontrado: " + a.getNomeUsuario());
        return; // ou lance uma exceção
    }
    
    int usuarioId = usuario.getId(); // Agora pega o ID
    int filmeId = filmeDAO.buscarIdPorTitulo(a.getNomeFilme());

    String sql = "INSERT INTO avaliacoes (id_usuario, id_filme, nota, review) VALUES (?, ?, ?, ?)";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, usuarioId);
        ps.setInt(2, filmeId);
        ps.setInt(3, a.getNota());
        ps.setString(4, a.getReview());

        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
