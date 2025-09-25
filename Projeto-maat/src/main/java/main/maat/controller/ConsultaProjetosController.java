package main.maat.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.maat.DAO.ProjetoDAO;
import main.maat.MainApplication;
import main.maat.model.Projeto;

import java.time.LocalDate;
import java.util.List;

public class ConsultaProjetosController {

    @FXML
    private TableView<Projeto> tabelaProjetos;

    @FXML
    private TableColumn<Projeto, Integer> colunaId;

    @FXML
    private TableColumn<Projeto, String> colunaNome;

    @FXML
    private TableColumn<Projeto, String> colunaDescricao;

    @FXML
    private TableColumn<Projeto, LocalDate> colunaDataInicio;

    @FXML
    private TableColumn<Projeto, LocalDate> colunaDataPrevistaFim;

    @FXML
    private TableColumn<Projeto, LocalDate> colunaDataFim;

    private ProjetoDAO projetoDAO = new ProjetoDAO();

    /**
     * Este método é chamado automaticamente quando a tela é carregada.
     */
    @FXML
    public void initialize() {
        // Configura as colunas para saberem de qual atributo do objeto Projeto pegar o valor
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        colunaDataPrevistaFim.setCellValueFactory(new PropertyValueFactory<>("dataPrevistaFim"));
        colunaDataFim.setCellValueFactory(new PropertyValueFactory<>("dataFim"));

        // Carrega os dados do banco de dados e os exibe na tabela
        carregarProjetos();
    }

    /**
     * Busca os projetos no banco de dados e atualiza a tabela.
     */
    private void carregarProjetos() {
        List<Projeto> projetosDoBanco = projetoDAO.listarTodos();
        ObservableList<Projeto> observableListProjetos = FXCollections.observableArrayList(projetosDoBanco);
        tabelaProjetos.setItems(observableListProjetos);
    }

    /**
     * Chamado pelo botão "Voltar". Leva o usuário de volta para o menu principal.
     */
    @FXML
    private void voltarParaMenu(ActionEvent event) {
        MainApplication.trocarTela("dashboard.fxml", 800, 600);
    }
}