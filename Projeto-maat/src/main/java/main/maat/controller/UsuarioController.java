package main.maat.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.maat.DAO.UsuarioDAO;
import main.maat.MainApplication;
import main.maat.model.Usuario;

public class UsuarioController {

    @FXML
    private TextField nomeCompletoField;
    @FXML
    private TextField cpfField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField cargoField;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private ComboBox<String> perfilComboBox;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    public void initialize() {
        // Popula o ComboBox com os perfis disponíveis
        perfilComboBox.setItems(FXCollections.observableArrayList("Administrador", "Gerente", "Colaborador"));
    }

    @FXML
    private void salvarUsuario() {
        String nomeCompleto = nomeCompletoField.getText();
        String login = loginField.getText();
        String senha = senhaField.getText();
        String perfil = perfilComboBox.getValue();

        // Validação básica
        if (nomeCompleto.isEmpty() || login.isEmpty() || senha.isEmpty() || perfil == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Validação");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos obrigatórios: Nome, Login, Senha e Perfil.");
            alert.showAndWait();
            return;
        }

        // Cria o novo objeto Usuario
        Usuario novoUsuario = new Usuario(
                nomeCompleto,
                cpfField.getText(),
                emailField.getText(),
                cargoField.getText(),
                login,
                senha,
                perfil
        );

        // Salva no banco de dados
        try {
            usuarioDAO.salvar(novoUsuario);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Usuário '" + novoUsuario.getNomeCompleto() + "' salvo com sucesso!");
            alert.showAndWait();

            // Limpa os campos após salvar
            limparCampos();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no Banco de Dados");
            alert.setHeaderText("Não foi possível salvar o usuário.");
            alert.setContentText("Detalhes do erro: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private void limparCampos() {
        nomeCompletoField.clear();
        cpfField.clear();
        emailField.clear();
        cargoField.clear();
        loginField.clear();
        senhaField.clear();
        perfilComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void voltarParaMenu(ActionEvent event) {
        MainApplication.trocarTela("dashboard.fxml", 800, 600);
    }
}