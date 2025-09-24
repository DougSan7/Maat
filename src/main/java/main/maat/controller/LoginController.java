package main.maat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.maat.MainApplication;

/**
 * ✅ Controller da tela de login — valida credenciais e navega.
 */
public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    /**
     * ✅ Método chamado ao clicar em "Entrar".
     */
    @FXML
    private void fazerLogin() {
        String email = emailField.getText().trim();
        String senha = senhaField.getText().trim();

        boolean autenticado = false;

        // ✅ Login fixo para desenvolvimento: admin / admin
        if ("admin".equals(email) && "admin".equals(senha)) {
            autenticado = true;
            System.out.println("✅ Login com usuário fixo 'admin' realizado.");
        }

        if (autenticado) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Login realizado com sucesso!");
            alert.showAndWait();

            // 🔥 Navega para o DASHBOARD após login
            MainApplication.trocarTela("dashboard.fxml", 800, 600);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Usuário ou senha inválidos.");
            alert.showAndWait();
        }
    }
}
