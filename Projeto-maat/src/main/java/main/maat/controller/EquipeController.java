package main.maat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import main.maat.DAO.EquipeDAO;
import main.maat.DAO.UsuarioDAO; // MODIFICADO
import main.maat.MainApplication;
import main.maat.model.Equipe;
import main.maat.model.Usuario; // MODIFICADO

import java.util.List;

public class EquipeController {

    @FXML
    private TextField nomeEquipeField;

    @FXML
    private ComboBox<Usuario> gerenteComboBox; // MODIFICADO

    @FXML
    private ComboBox<Usuario> membrosComboBox; // MODIFICADO

    private EquipeDAO equipeDAO = new EquipeDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO(); // MODIFICADO

    private Equipe equipeAtual = new Equipe();

    @FXML
    public void initialize() {
        // Carregar usuários do banco para os ComboBox
        List<Usuario> usuarios = usuarioDAO.listarTodos(); // MODIFICADO
        gerenteComboBox.getItems().addAll(usuarios);
        membrosComboBox.getItems().addAll(usuarios);
    }

    @FXML
    private void adicionarMembro() {
        Usuario selecionado = membrosComboBox.getValue(); // MODIFICADO
        if (selecionado != null) {
            equipeAtual.adicionarMembro(selecionado);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Membro '" + selecionado.getNomeCompleto() + "' adicionado à lista.");
            alert.setHeaderText(null);
            alert.showAndWait();
            membrosComboBox.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void salvarEquipe() {
        equipeAtual.setNome(nomeEquipeField.getText());
        equipeAtual.setGerente(gerenteComboBox.getValue());

        equipeDAO.salvar(equipeAtual);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Equipe");
        alert.setHeaderText(null);
        alert.setContentText("Equipe cadastrada com sucesso!");
        alert.showAndWait();

        // Limpa a tela para um novo cadastro
        nomeEquipeField.clear();
        gerenteComboBox.getSelectionModel().clearSelection();
        equipeAtual = new Equipe();
    }

    @FXML
    private void voltarParaMenu(ActionEvent event) {
        MainApplication.trocarTela("dashboard.fxml", 800, 600);
    }
}