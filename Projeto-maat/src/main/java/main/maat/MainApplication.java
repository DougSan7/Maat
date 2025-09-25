package main.maat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    static Stage stage; // Usado para trocar de telas

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Gerenciamento de Projetos - Maat");

        // Carrega a tela de login como inicial
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Troca de tela dentro do programa.
     * @param fxml caminho do arquivo FXML a ser carregado
     * @param largura largura da janela
     * @param altura altura da janela
     */
    public static void trocarTela(String fxml, int largura, int altura) {
        try {
            // MODIFICAÇÃO APLICADA AQUI: O caminho foi corrigido de "/view/" para "/main/maat/"
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/main/maat/" + fxml));
            Scene novaCena = new Scene(fxmlLoader.load(), largura, altura);
            stage.setScene(novaCena);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao trocar de tela: " + fxml);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}