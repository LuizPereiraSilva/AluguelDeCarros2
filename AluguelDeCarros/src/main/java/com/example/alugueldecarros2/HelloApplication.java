package com.example.alugueldecarros2;

import com.example.alugueldecarros2.Controllers.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import com.example.alugueldecarros2.Controllers.SceneManager;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TelaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        SceneManager screenManager = SceneManager.getInstance();
    }

    public static void main(String[] args) {
        launch();
    }
}