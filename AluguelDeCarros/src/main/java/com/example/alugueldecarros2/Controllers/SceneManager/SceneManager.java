package com.example.alugueldecarros2.Controllers.SceneManager;

import com.example.alugueldecarros2.Controllers.*;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class SceneManager {

    private static SceneManager instance;
    private Stage stage;

    private Scene loginTela;
    private Scene perfilCliente;
    private Scene telaCadastro;
    private Scene telaPesquisa;
    private Scene telaCarro;

    private LoginTelaController loginTelaController;
    private PerfilClienteController perfilClienteController;
    private TelaCadastroController telaCadastroController;
    private TelaPesquisaController telaPesquisaController;
    private TelaCarroController telaCarroController;

    private SceneManager(){
        this.screenLoader();
    }

    public static SceneManager getInstance(){
        if(instance == null){
            instance = new SceneManager();
        }

        return instance;
    }



    public void setStage(Stage stage){ this.stage = stage; }

    public Stage getStage(){ return stage; }



    public Scene getLoginTela(){ return this.loginTela; }

    public Scene getPerfilCliente(){ return this.perfilCliente; }

    public Scene getTelaCadastro(){ return this.telaCadastro; }

    public Scene getTelaPesquisa(){ return this.telaPesquisa; }

    public Scene getTelaCarro(){ return this.telaCarro; }



    public LoginTelaController getLoginTelaController(){ return this.loginTelaController; }

    public PerfilClienteController getPerfilClienteController(){ return this.perfilClienteController; }

    public TelaCadastroController getTelaCadastroController() { return this.telaCadastroController; }

    public TelaPesquisaController getTelaPesquisaController() { return this.telaPesquisaController; }

    public TelaCarroController getTelaCarroController() { return this.telaCarroController; }



    private void screenLoader(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            VBox loginTelaPane = fxmlLoader.load(getClass().getResource("/com/example/alugueldecarros2/LoginTela.fxml"));
            this.loginTela = new Scene(loginTelaPane);
            this.loginTelaController = (LoginTelaController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            VBox perfilClientePane = fxmlLoader.load(getClass().getResource("/com/example/alugueldecarros2/PerfilCliente.fxml"));
            this.perfilCliente = new Scene(perfilClientePane);
            this.perfilClienteController = (PerfilClienteController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            VBox TelaCadastropane = fxmlLoader.load(getClass().getResource("/com/example/alugueldecarros2/TelaCadastro.fxml"));
            this.telaCadastro = new Scene(TelaCadastropane);
            this.telaCadastroController= (TelaCadastroController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            VBox TelaPesquisapane = fxmlLoader.load(getClass().getResource("/com/example/alugueldecarros2/TelaPesquisa.fxml"));
            this.telaPesquisa = new Scene(TelaPesquisapane);
            this.telaPesquisaController = (TelaPesquisaController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            VBox TelaCarropane = fxmlLoader.load(getClass().getResource("/com/example/alugueldecarros2/TelaCarro.fxml"));
            this.telaCarro = new Scene(TelaCarropane);
            this.telaCarroController = (TelaCarroController) fxmlLoader.getController();


        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void changeScreen(String fileNameFxml, String titleScreen){
        boolean max = stage.isMaximized();
        if(max){ stage.setMaximized(false); }

        switch(fileNameFxml){
            case "LoginTela.fxml" -> this.stage.setScene(this.loginTela);
            case "PerfilCliente.fxml" -> this.stage.setScene(this.perfilCliente);
            case "TelaCadastro.fxml" -> this.stage.setScene(this.telaCadastro);
            case "TelaPesquisa.fxml" -> this.stage.setScene(this.telaPesquisa);
            case "TelaCarro.fxml" -> this.stage.setScene(this.telaCarro);
        }
        stage.setTitle(titleScreen);

        if(max){ stage.setMaximized(true);}
    }
}
