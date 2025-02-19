package com.example.alugueldecarros2;

import com.example.alugueldecarros2.Controllers.SceneManager.SceneManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AluguelDeCarrosApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(SceneManager.getInstance().getLoginTela());
        stage.setTitle("Sistema de Aluguel de Carros");

        stage.setWidth(800);
        stage.setHeight(534);
        stage.setResizable(false);

        SceneManager.getInstance().setStage(stage);

        stage.show();
    }

    public static void main(String[] args) {
        AluguelDeCarrosApp.launch(args);
    }

}