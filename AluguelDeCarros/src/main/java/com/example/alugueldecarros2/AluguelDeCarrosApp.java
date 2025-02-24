package com.example.alugueldecarros2;

import com.example.alugueldecarros2.Controllers.SceneManager;
import com.example.alugueldecarros2.Negocio.Fachada;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AluguelDeCarrosApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setStage(stage);
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PerfilCliente.fxml", "Primeira Tela");

        stage.show();
    }

    public static void main(String[] args) {

        AluguelDeCarrosApp.launch(args);
    }

}