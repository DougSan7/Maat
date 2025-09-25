package main.maat.DAO;

import main.maat.model.Equipe;
import main.maat.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO {

    public void salvar(Equipe equipe) {
        String sql = "INSERT INTO equipe (nome, gerente_id) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false); // Inicia transação

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, equipe.getNome());
                if (equipe.getGerente() != null) {
                    stmt.setInt(2, equipe.getGerente().getId());
                } else {
                    stmt.setNull(2, Types.INTEGER);
                }
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int equipeId = rs.getInt(1);
                    equipe.setId(equipeId);

                    // Adicionar membros na tabela equipe_usuario
                    if (equipe.getMembros() != null && !equipe.getMembros().isEmpty()) {
                        String sqlMembro = "INSERT INTO equipe_usuario (id_equipe, id_usuario) VALUES (?, ?)";
                        try (PreparedStatement stmtMembro = conn.prepareStatement(sqlMembro)) {
                            for (Usuario membro : equipe.getMembros()) {
                                stmtMembro.setInt(1, equipeId);
                                stmtMembro.setInt(2, membro.getId());
                                stmtMembro.addBatch();
                            }
                            stmtMembro.executeBatch();
                        }
                    }
                }
                conn.commit(); // Finaliza a transação com sucesso
            } catch (SQLException e) {
                conn.rollback(); // Desfaz a transação em caso de erro
                throw new RuntimeException("Erro ao salvar equipe", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro de conexão", e);
        }
    }

    // MÉTODO 'listarTodos' ADICIONADO DE VOLTA
    public List<Equipe> listarTodos() {
        List<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT * FROM equipe";
        UsuarioDAO usuarioDAO = new UsuarioDAO(); // Para buscar os usuários

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipe e = new Equipe();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));

                // Busca o gerente (que agora é um Usuário)
                int gerenteId = rs.getInt("gerente_id");
                if (!rs.wasNull()) {
                    e.setGerente(usuarioDAO.buscarPorId(gerenteId));
                }

                // Busca os membros da equipe (que agora são Usuários)
                e.setMembros(buscarMembrosPorEquipe(e.getId(), conn));

                equipes.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar equipes", e);
        }
        return equipes;
    }

    // MÉTODO AUXILIAR PARA BUSCAR MEMBROS
    private List<Usuario> buscarMembrosPorEquipe(int equipeId, Connection conn) throws SQLException {
        List<Usuario> membros = new ArrayList<>();
        String sql = "SELECT u.* FROM usuario u INNER JOIN equipe_usuario eu ON u.id = eu.id_usuario WHERE eu.id_equipe = ?";
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, equipeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Reutiliza o método do UsuarioDAO para criar o objeto a partir do ResultSet
                Usuario membro = usuarioDAO.buscarPorId(rs.getInt("id"));
                membros.add(membro);
            }
        }
        return membros;
    }


    // OUTROS MÉTODOS QUE ESTAVAM FALTANDO (buscarPorId, remover)
    public Equipe buscarPorId(int id) {
        String sql = "SELECT * FROM equipe WHERE id = ?";
        Equipe equipe = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                equipe = new Equipe();
                equipe.setId(rs.getInt("id"));
                equipe.setNome(rs.getString("nome"));

                int gerenteId = rs.getInt("gerente_id");
                if (!rs.wasNull()) {
                    equipe.setGerente(usuarioDAO.buscarPorId(gerenteId));
                }
                equipe.setMembros(buscarMembrosPorEquipe(equipe.getId(), conn));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar equipe por ID", e);
        }
        return equipe;
    }

    public void remover(int id) {
        String sqlEquipeUsuario = "DELETE FROM equipe_usuario WHERE id_equipe = ?";
        String sqlEquipe = "DELETE FROM equipe WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            // Remove membros associados
            try (PreparedStatement stmtMembros = conn.prepareStatement(sqlEquipeUsuario)) {
                stmtMembros.setInt(1, id);
                stmtMembros.executeUpdate();
            }
            // Remove a equipe
            try (PreparedStatement stmtEquipe = conn.prepareStatement(sqlEquipe)) {
                stmtEquipe.setInt(1, id);
                stmtEquipe.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover equipe", e);
        }
    }
}