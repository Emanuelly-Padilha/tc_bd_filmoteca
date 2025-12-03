package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Usuario;

public class UsuarioDAO {

    public boolean inserir(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();

            if (emailExiste(usuario.getEmail())) {
                System.out.println("Erro: Email já cadastrado.");
                return false;
            }

            conn.setAutoCommit(false);

            String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, usuario.getNome().trim());
            ps.setString(2, usuario.getEmail().trim().toLowerCase());
            ps.setString(3, usuario.getSenha());

            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário.");

            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Erro no sistema.");
            }
            return false;

        } finally {
            fecharRecursos(ps, conn);
        }
    }

    public Usuario login(String email, String senha) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();

            String sql = "SELECT id, nome, email, senha FROM usuarios WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email.trim().toLowerCase());

            rs = ps.executeQuery();

            if (rs.next()) {
                String senhaBanco = rs.getString("senha");

                if (senha.equals(senhaBanco)) {

                    String nome = rs.getString("nome");
                    String emailBanco = rs.getString("email");

                    Usuario usuario = new Usuario(nome, emailBanco, senhaBanco);
                    usuario.setId(rs.getInt("id"));
                    return usuario;
                }
                return null;
            }
            return null;

        } catch (SQLException e) {
            System.out.println("Erro no sistema. Tente novamente.");
            return null;

        } finally {
            fecharRecursos(rs, ps, conn);
        }
    }

    public Usuario buscarPorEmail(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT id, nome, email, senha FROM usuarios WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email.trim().toLowerCase());

            rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");

                Usuario usuario = new Usuario(nome, email, senha);
                usuario.setId(rs.getInt("id"));
                return usuario;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por email: " + e.getMessage());

        } finally {
            fecharRecursos(rs, ps, conn);
        }

        return null;
    }

    public Usuario buscarPorNome(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT id, nome, email, senha FROM usuarios WHERE nome = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nome.trim());

            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nomeBanco = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");

                Usuario usuario = new Usuario(nomeBanco, email, senha);
                usuario.setId(id);
                return usuario;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por nome: " + e.getMessage());

        } finally {
            fecharRecursos(rs, ps, conn);
        }

        return null;
    }

    public boolean emailExiste(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT COUNT(*) as total FROM usuarios WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email.trim().toLowerCase());

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar email: " + e.getMessage());

        } finally {
            fecharRecursos(rs, ps, conn);
        }

        return false;
    }

    public boolean nomeExiste(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT COUNT(*) as total FROM usuarios WHERE nome = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nome.trim());

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar nome: " + e.getMessage());

        } finally {
            fecharRecursos(rs, ps, conn);
        }

        return false;
    }

    public boolean atualizarPorEmail(String emailAntigo, Usuario novosDados) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            if (!emailAntigo.equalsIgnoreCase(novosDados.getEmail())) {
                if (emailExiste(novosDados.getEmail())) {
                    System.out.println("Erro: Novo email já está em uso.");
                    return false;
                }
            }

            String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE email = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, novosDados.getNome().trim());
            ps.setString(2, novosDados.getEmail().trim().toLowerCase());
            ps.setString(3, novosDados.getSenha());
            ps.setString(4, emailAntigo.trim().toLowerCase());

            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());

            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException e2) {
                System.err.println("Erro no rollback: " + e2.getMessage());
            }
            return false;

        } finally {
            fecharRecursos(ps, conn);
        }
    }

    public boolean excluirPorEmail(String email) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            String sql = "DELETE FROM usuarios WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email.trim().toLowerCase());

            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuário: " + e.getMessage());

            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException e2) {
                System.err.println("Erro no rollback: " + e2.getMessage());
            }
            return false;

        } finally {
            fecharRecursos(ps, conn);
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT nome, email FROM usuarios ORDER BY nome"; // Não retorna senha
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                Usuario usuario = new Usuario(nome, email, "");
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());

        } finally {
            fecharRecursos(rs, ps, conn);
        }

        return usuarios;
    }

    private int buscarIdPorEmailInterno(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT id FROM usuarios WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email.trim().toLowerCase());

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            System.err.println("Erro interno ao buscar ID: " + e.getMessage());

        } finally {
            fecharRecursos(rs, ps, conn);
        }

        return -1;
    }

    public boolean validarCredenciais(String email, String senha) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT senha FROM usuarios WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email.trim().toLowerCase());

            rs = ps.executeQuery();

            if (rs.next()) {
                String senhaBanco = rs.getString("senha");
                return senha.equals(senhaBanco);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao validar credenciais: " + e.getMessage());

        } finally {
            fecharRecursos(rs, ps, conn);
        }

        return false;
    }

    private void fecharRecursos(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }

    private void fecharRecursos(PreparedStatement ps, Connection conn) {
        try {
            if (ps != null)
                ps.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }

    public void remover(String nomeUsuario) {

        Usuario usuario = buscarPorNome(nomeUsuario);

        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado: " + nomeUsuario);
            return;
        }

        int usuarioId = usuario.getId();

        Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            String sqlAvaliacoes = "DELETE FROM avaliacoes WHERE id_usuario = ?";
            PreparedStatement ps = conn.prepareStatement(sqlAvaliacoes);
            ps.setInt(1, usuarioId);
            ps.executeUpdate();
            System.out.println("Avaliações do usuário removidas.");

            String sqlWatchlist = "DELETE FROM watchlist WHERE id_usuario = ?";
            ps = conn.prepareStatement(sqlWatchlist);
            ps.setInt(1, usuarioId);
            ps.executeUpdate();
            System.out.println("Watchlist do usuário removida.");

            String sqlAssistidos = "DELETE FROM filmes_assistidos WHERE id_usuario = ?";
            ps = conn.prepareStatement(sqlAssistidos);
            ps.setInt(1, usuarioId);
            ps.executeUpdate();
            System.out.println("Histórico de filmes assistidos removido.");

            String sqlUsuario = "DELETE FROM usuarios WHERE id = ?";
            ps = conn.prepareStatement(sqlUsuario);
            ps.setInt(1, usuarioId);
            int linhasRemovidas = ps.executeUpdate();

            conn.commit();

            if (linhasRemovidas > 0) {
                System.out.println("Usuário '" + nomeUsuario + "' removido com sucesso!");
                System.out.println("Todos os dados relacionados também foram removidos.");
            }

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            System.out.println("Erro ao remover usuário: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}