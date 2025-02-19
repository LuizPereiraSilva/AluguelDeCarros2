package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Controllers.*;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class SceneManager {

    private static SceneManager instance;
    private static Stage stage;

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
    private PrimeiraTelaContoller primeiraTelaContoller;
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



    public static void setStage(Stage stage){ SceneManager.stage = stage; }

    public static Stage getStage(){ return stage; }



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

    public PrimeiraTelaContoller getPrimeiraTelaContoller(){ return this.primeiraTelaContoller; }

    public TelaLoginAdmController getTelaLoginAdmController(){ return this.telaLoginAdmController; }

    public TelaReservaConfirmadaController getTelaReservaConfirmadaController(){ return this.telaReservaConfirmadaController; }



    private void screenLoader(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoginTela.fxml"));
            this.loginTela = new Scene(fxmlLoader.load());
            this.loginTelaController = (LoginTelaController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/PerfilCliente.fxml"));
            this.perfilCliente = new Scene(fxmlLoader.load());
            this.perfilClienteController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/TelaCadastro.fxml"));
            this.telaCadastro = new Scene(fxmlLoader.load());
            this.telaCadastroController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/TelaPesquisa.fxml"));
            this.telaPesquisa = new Scene(fxmlLoader.load());
            this.telaPesquisaController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/TelaCarro.fxml"));
            this.telaCarro = new Scene(fxmlLoader.load());
            this.telaCarroController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/PrimeiraTela.fxml"));
            this.primeiraTela = new Scene(fxmlLoader.load());
            this.primeiraTelaContoller = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/TelaLoginAdm.fxml"));
            this.telaLoginAdm = new Scene(fxmlLoader.load());
            this.telaLoginAdmController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/TelaReservaConfirmada.fxml"));
            this.telaReservaConfirmada = new Scene(fxmlLoader.load());
            this.telaReservaConfirmadaController = fxmlLoader.getController();


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
