package com.example.alugueldecarros2;

import com.example.alugueldecarros2.Controllers.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class AluguelDeCarrosApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = SceneManager.getInstance();
        SceneManager.setStage(stage);
        sceneManager.changeScreen("PrimeiraTela.fxml", "PrimeiraTela");

        stage.show();
    }

    public static void main(String[] args) {
        AluguelDeCarrosApp.launch(args);
    }

}