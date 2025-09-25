package main.maat.DAO;

import main.maat.model.Projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    // Salvar projeto
    public void salvar(Projeto projeto) {
        String sql = "INSERT INTO projeto (nome, descricao, data_inicio, data_prevista_fim, data_fim) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());

            if (projeto.getDataInicio() != null) {
                stmt.setDate(3, java.sql.Date.valueOf(projeto.getDataInicio()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }

            if (projeto.getDataPrevistaFim() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(projeto.getDataPrevistaFim()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }

            if (projeto.getDataFim() != null) {
                stmt.setDate(5, java.sql.Date.valueOf(projeto.getDataFim()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                projeto.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar projetos
    public List<Projeto> listarTodos() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projeto";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Projeto p = new Projeto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));

                Date dataInicio = rs.getDate("data_inicio");
                if(dataInicio != null) p.setDataInicio(dataInicio.toLocalDate());

                Date dataPrevista = rs.getDate("data_prevista_fim");
                if(dataPrevista != null) p.setDataPrevistaFim(dataPrevista.toLocalDate());

                Date dataFim = rs.getDate("data_fim");
                if(dataFim != null) p.setDataFim(dataFim.toLocalDate());

                projetos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projetos;
    }

    // Buscar por ID
    public Projeto buscarPorId(int id) {
        String sql = "SELECT * FROM projeto WHERE id = ?";
        Projeto projeto = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                projeto = new Projeto();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));

                Date dataInicio = rs.getDate("data_inicio");
                if(dataInicio != null) projeto.setDataInicio(dataInicio.toLocalDate());

                Date dataPrevista = rs.getDate("data_prevista_fim");
                if(dataPrevista != null) projeto.setDataPrevistaFim(dataPrevista.toLocalDate());

                Date dataFim = rs.getDate("data_fim");
                if(dataFim != null) projeto.setDataFim(dataFim.toLocalDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projeto;
    }

    // Remover projeto
    public void remover(int id) {
        String sql = "DELETE FROM projeto WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}