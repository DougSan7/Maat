package main.maat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.maat.MainApplication;

/**
 * ✅ Controller do Dashboard — gerencia os botões da tela principal.
 */
public class DashboardController {

    /**
     * ✅ Chamado quando o botão "Cadastrar Pessoa" é clicado.
     */
    @FXML
    private void irParaPessoa(ActionEvent event) {
        MainApplication.trocarTela("pessoa-view.fxml", 600, 400);
    }

    /**
     * ✅ Chamado quando o botão "Cadastrar Projeto" é clicado.
     */
    @FXML
    private void irParaProjeto(ActionEvent event) {
        MainApplication.trocarTela("projeto-view.fxml", 600, 450);
    }

    /**
     * ✅ Chamado quando o botão "Cadastrar Equipe" é clicado.
     */
    @FXML
    private void irParaEquipe(ActionEvent event) {
        MainApplication.trocarTela("equipe-view.fxml", 600, 400);
    }

    /**
     * ✅ Chamado quando o botão "Sair" é clicado.
     */
    @FXML
    private void sair(ActionEvent event) {
        MainApplication.trocarTela("login-View.fxml", 600, 400);
    }
}