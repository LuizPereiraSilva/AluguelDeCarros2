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
    private static Stage stage;

    private Scene loginTela;
    private Scene perfilCliente;
    private Scene telaCadastro;
    private Scene telaPesquisa;
    private Scene telaCarro;
    private Scene primeiraTela;
    private Scene telaLoginAdm;
    private Scene telaReservaConfirmada;
    private Scene addAdm;
    private Scene addCarro;
    private Scene editarCarro;
    private Scene painelDeControle;
    private Scene perfilAdm;
    private Scene pesquisarCarroAdm;
    private Scene pesquisarReservasAdm;
    private Scene pesquisarUsuarioAdm;
    private Scene telaUsuarioAdm;



    private LoginTelaController loginTelaController;
    private PerfilClienteController perfilClienteController;
    private TelaCadastroController telaCadastroController;
    private TelaPesquisaController telaPesquisaController;
    private TelaCarroController telaCarroController;
    private PrimeiraTelaController primeiraTelaController;
    private TelaLoginAdmController telaLoginAdmController;
    private TelaReservaConfirmadaController telaReservaConfirmadaController;
    private AddAdmController addAdmController;
    private AddCarroController addCarroController;
    private EditarCarroController editarCarroController;
    private PainelDeControleController painelDeControleController;
    private PerfilAdmController perfilAdmController;
    private PesquisarCarroAdmController pesquisarCarroAdmController;
    private PesquisarReservasAdmController pesquisarReservasAdmController;
    private PesquisarUsuarioAdmController pesquisarUsuarioAdmController;
    private TelaUsuarioAdmController telaUsuarioAdmController;

    private SceneManager(){
        this.screenLoader();
    }

    public static SceneManager getInstance(){
        if(instance == null){
            instance = new SceneManager();
        }

        System.out.println("Erro 1");
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

    public Scene getTelaReservaConfirmada(){ return this.telaReservaConfirmada;}

    public Scene getAddAdm(){ return this.addAdm; }

    public Scene getAddCarro(){ return this.addCarro; }

    public Scene getEditarCarro(){ return this.editarCarro; }

    public Scene getPainelDeControle(){ return this.painelDeControle; }

    public Scene getPerfilAdm(){ return this.perfilAdm; }

    public Scene getPesquisarCarroAdm(){ return this.pesquisarCarroAdm; }

    public Scene getPesquisarReservasAdm(){ return this.pesquisarReservasAdm; }

    public Scene getPesquisarUsuarioAdm(){ return this.pesquisarUsuarioAdm; }

    public Scene getTelaUsuarioAdm(){ return this.telaUsuarioAdm; }





    public LoginTelaController getLoginTelaController(){ return this.loginTelaController; }

    public PerfilClienteController getPerfilClienteController(){ return this.perfilClienteController; }

    public TelaCadastroController getTelaCadastroController() { return this.telaCadastroController; }

    public TelaPesquisaController getTelaPesquisaController() { return this.telaPesquisaController; }

    public TelaCarroController getTelaCarroController() { return this.telaCarroController; }

    public PrimeiraTelaController getPrimeiraTelaContoller(){ return this.primeiraTelaController; }

     public TelaLoginAdmController getTelaLoginAdmController(){ return this.telaLoginAdmController; }

     public TelaReservaConfirmadaController getTelaReservaConfirmadaController(){ return this.telaReservaConfirmadaController; }

    public AddAdmController getAddAdmController(){ return this.addAdmController; }

    public AddCarroController getAddCarroController(){ return this.addCarroController; }

    public EditarCarroController getEditarCarroController(){ return this.editarCarroController; }

    public PainelDeControleController getPainelDeControleController(){ return this.painelDeControleController; }

    public PerfilAdmController getPerfilAdmController(){ return this.perfilAdmController; }

    public PesquisarCarroAdmController getPesquisarCarroAdmController(){ return this.pesquisarCarroAdmController; }

    public PesquisarReservasAdmController getPesquisarReservasAdmController(){ return this.pesquisarReservasAdmController; }

    public PesquisarUsuarioAdmController getPesquisarUsuarioAdmController(){ return this.pesquisarUsuarioAdmController; }

    public TelaUsuarioAdmController getTelaUsuarioAdmController(){ return this.telaUsuarioAdmController; }





    private void screenLoader(){
        try {
            FXMLLoader fxmlLoader = null;

            fxmlLoader = new FXMLLoader(getClass().getResource("/PrimeiraTela.fxml"));
            this.primeiraTela = new Scene(fxmlLoader.load());
            this.primeiraTelaController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/LoginTela.fxml"));
            this.loginTela = new Scene(fxmlLoader.load());
            this.loginTelaController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/PerfilCliente.fxml"));
            this.perfilCliente = new Scene(fxmlLoader.load());
            this.perfilClienteController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/TelaCadastro.fxml"));
            this.telaCadastro = new Scene(fxmlLoader.load());
            this.telaCadastroController= fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/TelaPesquisa.fxml"));
            this.telaPesquisa = new Scene(fxmlLoader.load());
            this.telaPesquisaController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/TelaCarro.fxml"));
            this.telaCarro = new Scene(fxmlLoader.load());
            this.telaCarroController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/TelaLoginAdm.fxml"));
            this.telaLoginAdm = new Scene(fxmlLoader.load());
            this.telaLoginAdmController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/TelaReservaConfirmada.fxml"));
            this.telaReservaConfirmada = new Scene(fxmlLoader.load());
            this.telaReservaConfirmadaController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/AddAdm.fxml"));
            this.addAdm = new Scene(fxmlLoader.load());
            this.addAdmController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/AddCarro.fxml"));
            this.addCarro = new Scene(fxmlLoader.load());
            this.addCarroController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/EditarCarro.fxml"));
            this.editarCarro = new Scene(fxmlLoader.load());
            this.editarCarroController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/PainelDeControle.fxml"));
            this.painelDeControle = new Scene(fxmlLoader.load());
            this.painelDeControleController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/PerfilAdm.fxml"));
            this.perfilAdm = new Scene(fxmlLoader.load());
            this.perfilAdmController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/PesquisarCarroAdm.fxml"));
            this.pesquisarCarroAdm = new Scene(fxmlLoader.load());
            this.pesquisarCarroAdmController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/PesquisarReservasAdm.fxml"));
            this.pesquisarReservasAdm = new Scene(fxmlLoader.load());
            this.pesquisarReservasAdmController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/PesquisarUsuarioAdm.fxml"));
            this.pesquisarUsuarioAdm = new Scene(fxmlLoader.load());
            this.pesquisarUsuarioAdmController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(getClass().getResource("/TelaUsuarioAdm.fxml"));
            this.telaUsuarioAdm = new Scene(fxmlLoader.load());
            this.telaUsuarioAdmController = fxmlLoader.getController();


        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void changeScreen(String fileNameFxml, String titleScreen){
        boolean max = stage.isMaximized();
        if(max){ stage.setMaximized(false); }

        switch(fileNameFxml){
            case "LoginTela.fxml" -> stage.setScene(this.loginTela);
            case "PerfilCliente.fxml" -> stage.setScene(this.perfilCliente);
            case "TelaCadastro.fxml" -> stage.setScene(this.telaCadastro);
            case "TelaPesquisa.fxml" -> stage.setScene(this.telaPesquisa);
            case "TelaCarro.fxml" -> stage.setScene(this.telaCarro);
            case "PrimeiraTela.fxml" -> stage.setScene(this.primeiraTela);
            case "TelaLoginAdm.fxml" -> stage.setScene(this.telaLoginAdm);
            case "TelaReservaConfirmada.fxml" -> stage.setScene(this.telaReservaConfirmada);
            case "AddAdm.fxml" -> stage.setScene(this.addAdm);
            case "AddCarro.fxml" -> stage.setScene(this.addCarro);
            case "EditarCarro.fxml" -> stage.setScene(this.editarCarro);
            case "PainelDeControle.fxml" -> stage.setScene(this.painelDeControle);
            case "PerfilAdm.fxml" -> stage.setScene(this.perfilAdm);
            case "PesquisarCarroAdm.fxml" -> stage.setScene(this.pesquisarCarroAdm);
            case "PesquisarReservasAdm.fxml" -> stage.setScene(this.pesquisarReservasAdm);
            case "PesquisarUsuarioAdm.fxml" -> stage.setScene(this.pesquisarUsuarioAdm);
            case "TelaUsuarioAdm.fxml" -> stage.setScene(this.telaUsuarioAdm);
            default -> {
                System.out.println("Erro ao trocar de tela");
                System.exit(0);
            }
        }
        stage.setTitle(titleScreen);

        if(max){ stage.setMaximized(true);}
    }
}
