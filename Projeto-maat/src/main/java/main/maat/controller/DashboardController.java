package main.maat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.maat.MainApplication;

public class DashboardController {

    /**
     * Chamado quando o botão "Cadastrar Usuário" é clicado.
     * MODIFICADO PARA ABRIR A NOVA TELA DE USUÁRIO
     */
    @FXML
    private void irParaPessoa(ActionEvent event) {
        MainApplication.trocarTela("usuario-view.fxml", 600, 500);
    }

    /**
     * Chamado quando o botão "Cadastrar Projeto" é clicado.
     */
    @FXML
    private void irParaProjeto(ActionEvent event) {
        MainApplication.trocarTela("projeto-view.fxml", 600, 450);
    }

    /**
     * Chamado quando o botão "Cadastrar Equipe" é clicado.
     */
    @FXML
    private void irParaEquipe(ActionEvent event) {
        MainApplication.trocarTela("equipe-view.fxml", 600, 400);
    }

    /**
     * Chamado quando o botão "Consultar Projetos" é clicado.
     */
    @FXML
    private void irParaConsultaProjetos(ActionEvent event) {
        MainApplication.trocarTela("consulta-projetos-view.fxml", 600, 400);
    }

    /**
     * Chamado quando o botão "Sair" é clicado.
     */
    @FXML
    private void sair(ActionEvent event) {
        MainApplication.trocarTela("login-View.fxml", 600, 400);
    }
}