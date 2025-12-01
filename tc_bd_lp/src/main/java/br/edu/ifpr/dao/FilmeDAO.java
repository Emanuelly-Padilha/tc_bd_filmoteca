package br.edu.ifpr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Filme;

public class FilmeDAO {
       public void inserir(Filme f) {
        String sql = "INSERT INTO filmes (titulo, genero, ano, diretor, duracao, classificacao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, f.getTitulo());
            ps.setString(2, f.getGenero());
            ps.setInt(3, f.getAno());
            ps.setString(4, f.getDiretor());
            ps.setInt(5, f.getDuracao());
            ps.setString(6, f.getClassificacao());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int buscarIdPorTitulo(String titulo) {
        String sql = "SELECT id FROM filmes WHERE titulo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, titulo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Filme> listar() {
        List<Filme> lista = new ArrayList<>();
        String sql = "SELECT titulo, genero, ano, diretor FROM filmes";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Filme f = new Filme();
                f.setTitulo(rs.getString("titulo"));
                f.setGenero(rs.getString("genero"));
                f.setAno(rs.getInt("ano"));
                f.setDiretor(rs.getString("diretor"));
                lista.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
