package com.example.alugueldecarros2.Controllers.SceneManager;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import com.example.alugueldecarros2.Controllers.LoginTelaController;
import com.example.alugueldecarros2.Controllers.PerfilClienteController;
import com.example.alugueldecarros2.Controllers.TelaCadastroController;
import com.example.alugueldecarros2.Controllers.TelaPesquisaController;

import java.io.IOException;

public class SceneManager {

    private static SceneManager instance;
    private static Stage stage;

    private Scene loginTela;
    private Scene perfilCliente;
    private Scene telaCadastro;
    private Scene telaPesquisa;

    private LoginTelaController loginTelaController;
    private PerfilClienteController perfilClienteController;
    private TelaCadastroController telaCadastroController;
    private TelaPesquisaController telaPesquisaController;

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



    public LoginTelaController getLoginTelaController(){ return this.loginTelaController; }

    public PerfilClienteController getPerfilClienteController(){ return this.perfilClienteController; }

    public TelaCadastroController getTelaCadastroController() { return this.telaCadastroController; }

    public TelaPesquisaController getTelaPesquisaController() { return this.telaPesquisaController; }



    public void screenLoader(){
        try {
            FXMLLoader loginTelaPane = new FXMLLoader(getClass().getResource("LoginTela.fxml"));
            this.loginTela = new Scene(loginTelaPane.load());
            this.loginTelaController = loginTelaPane.getController();

            FXMLLoader perfilClientePane = new FXMLLoader(getClass().getResource("PerfilCliente.fxml"));
            this.perfilCliente = new Scene(perfilClientePane.load());
            this.perfilClienteController = perfilClientePane.getController();

            FXMLLoader telaCadastro = new FXMLLoader(getClass().getResource("TelaCadastro.fxml"));
            this.telaCadastro = new Scene(telaCadastro.load());
            this.telaCadastroController = telaCadastro.getController();

            FXMLLoader telaPesquisa = new FXMLLoader(getClass().getResource("TelaPesquisa.fxml"));
            this.telaPesquisa = new Scene(telaPesquisa.load());
            this.telaPesquisaController = telaPesquisa.getController();

        } catch(IOException e){
            throw new RuntimeException(e);
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
        }
        stage.setTitle(titleScreen);

        if(max){ stage.setMaximized(true);}
    }
}
