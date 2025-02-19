package com.example.alugueldecarros2;

import com.example.alugueldecarros2.Controllers.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AluguelDeCarrosApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = SceneManager.getInstance();
        SceneManager.setStage(stage);
        sceneManager.changeScreen("PrimeiraTela.fxml", "PrimeiraTela");

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginTela.fxml"));
//
//        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
//        stage.setTitle("Login Tela");

        stage.show();
    }

    public static void main(String[] args) {
        AluguelDeCarrosApp.launch(args);
    }

}