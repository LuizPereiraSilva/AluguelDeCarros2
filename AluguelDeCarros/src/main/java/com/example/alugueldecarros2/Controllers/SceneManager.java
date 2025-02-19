package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Controllers.*;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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
    private Scene primeiraTela;
    private Scene telaLoginAdm;
    private Scene telaReservaConfirmada;


    private LoginTelaController loginTelaController;
    private PerfilClienteController perfilClienteController;
    private TelaCadastroController telaCadastroController;
    private TelaPesquisaController telaPesquisaController;
    private TelaCarroController telaCarroController;
    private PrimeiraTelaController primeiraTelaContoller;
    private TelaLoginAdmController telaLoginAdmController;
    private TelaReservaConfirmadaController telaReservaConfirmadaController;

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

    public Scene getPrimeiraTela(){ return this.primeiraTela; }

    public Scene getTelaLoginAdm(){ return this.telaLoginAdm; }

    public Scene getTelaReservaConfirmada(){ return this.telaReservaConfirmada; }



    public LoginTelaController getLoginTelaController(){ return this.loginTelaController; }

    public PerfilClienteController getPerfilClienteController(){ return this.perfilClienteController; }

    public TelaCadastroController getTelaCadastroController() { return this.telaCadastroController; }

    public TelaPesquisaController getTelaPesquisaController() { return this.telaPesquisaController; }

    public TelaCarroController getTelaCarroController() { return this.telaCarroController; }

    public PrimeiraTelaController getPrimeiraTelaContoller(){ return this.primeiraTelaContoller; }

     public TelaLoginAdmController getTelaLoginAdmController(){ return this.telaLoginAdmController; }

     public TelaReservaConfirmadaController getTelaReservaConfirmadaController(){ return this.telaReservaConfirmadaController; }



    private void screenLoader(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            VBox primeiraTelaPane = fxmlLoader.load(getClass().getResource("/PrimeiraTela.fxml"));
            this.primeiraTela = new Scene(primeiraTelaPane);
            this.primeiraTelaContoller = (PrimeiraTelaController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            VBox loginTelaPane = fxmlLoader.load(getClass().getResource("/LoginTela.fxml"));
            this.loginTela = new Scene(loginTelaPane);
            this.loginTelaController = (LoginTelaController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            VBox perfilClientePane = fxmlLoader.load(getClass().getResource("/PerfilCliente.fxml"));
            this.perfilCliente = new Scene(perfilClientePane);
            this.perfilClienteController = (PerfilClienteController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            VBox TelaCadastropane = fxmlLoader.load(getClass().getResource("/TelaCadastro.fxml"));
            this.telaCadastro = new Scene(TelaCadastropane);
            this.telaCadastroController= (TelaCadastroController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            HBox TelaPesquisapane = fxmlLoader.load(getClass().getResource("/TelaPesquisa.fxml"));
            this.telaPesquisa = new Scene(TelaPesquisapane);
            this.telaPesquisaController = (TelaPesquisaController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            VBox TelaCarropane = fxmlLoader.load(getClass().getResource("/TelaCarro.fxml"));
            this.telaCarro = new Scene(TelaCarropane);
            this.telaCarroController = (TelaCarroController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            VBox TelaLoginAdmpane = fxmlLoader.load(getClass().getResource("/TelaLoginAdm.fxml"));
            this.telaLoginAdm = new Scene(TelaLoginAdmpane);
            this.telaLoginAdmController = (TelaLoginAdmController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            VBox TelaReservaConfirmadapane = fxmlLoader.load(getClass().getResource("/TelaReservaConfirmada.fxml"));
            this.telaReservaConfirmada = new Scene(TelaReservaConfirmadapane);
            this.telaReservaConfirmadaController = (TelaReservaConfirmadaController) fxmlLoader.getController();


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
            case "PrimeiraTela.fxml" -> this.stage.setScene(this.primeiraTela);
        }
        stage.setTitle(titleScreen);

        if(max){ stage.setMaximized(true);}
    }
}
