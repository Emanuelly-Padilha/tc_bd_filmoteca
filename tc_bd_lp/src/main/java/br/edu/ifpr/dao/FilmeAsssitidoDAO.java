package br.edu.ifpr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.FilmeAssistido;

public class FilmeAsssitidoDAO {
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    FilmeDAO filmeDAO = new FilmeDAO();

    public void inserir(FilmeAssistido fa) {

        int usuarioId = usuarioDAO.buscarIdPorNome(fa.getNomeUsuario());
        int filmeId = filmeDAO.buscarIdPorTitulo(fa.getNomeFilme());

        String sql = "INSERT INTO filmes_assistidos (id_usuario, id_filme, data_assistido) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ps.setInt(2, filmeId);
            ps.setString(3, fa.getDataAssistido());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<FilmeAssistido> listar(String nomeUsuario) {

        int usuarioId = usuarioDAO.buscarIdPorNome(nomeUsuario);
        List<FilmeAssistido> lista = new ArrayList<>();

        String sql = """
                SELECT f.titulo AS filme, fa.data_assistido
                FROM filmes_assistidos fa
                JOIN filmes f ON f.id = fa.id_filme
                WHERE fa.id_usuario = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new FilmeAssistido(
                        nomeUsuario,
                        rs.getString("filme"),
                        rs.getString("data_assistido")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
