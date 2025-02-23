package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaPesquisaController implements Initializable {

    @FXML
    private ChoiceBox<String> CategoriaCarroChoiceBox;

    @FXML
    private ChoiceBox<String> CategoriaPrecoChoiceBox;

    @FXML
    private DatePicker DataFinalDatePicker;

    @FXML
    private DatePicker DataInicialDatePicker;

    @FXML
    private ListView<String> ListResultados;

    private Carro[] carros;

    @FXML
    private Button VoltarButton1;

    @FXML
    private Button BuscarButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.listarCarros();
        this.initialize();
    }


    @FXML
    public void listarCarros(){

        Fachada fachada = Fachada.getInstance();
        String tipo = CategoriaCarroChoiceBox.getSelectionModel().getSelectedItem();
        String faixaDePreco = CategoriaPrecoChoiceBox.getSelectionModel().getSelectedItem();
        Carro[] lista = new Carro[1];

        try {
            lista = fachada.getListaCarros(tipo, faixaDePreco);
        } catch(OperacaoInvalidaException e){
            lista = fachada.getListaInicialCarros();
        }

        carros = lista;

        ListResultados.getItems().clear();

        for (int i = 0; i < lista.length; i++) {
            if(lista[i] != null) {
                ListResultados.getItems().add(lista[i].adicionarNaLista());
            }
        }
    }


    @FXML
    void handleVoltarButton1Action(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.getPerfilClienteController().initialize();
        sceneManager.changeScreen("PerfilCliente.fxml", "PerfilCliente ");

    }

    public void initialize(){
        CategoriaCarroChoiceBox.setValue("Categoria");
        CategoriaCarroChoiceBox.getItems().clear();
        CategoriaCarroChoiceBox.getItems().add("Hatchback");
        CategoriaCarroChoiceBox.getItems().add("Sedan");
        CategoriaCarroChoiceBox.getItems().add("Pickup");
        CategoriaCarroChoiceBox.getItems().add("SUV");
        CategoriaCarroChoiceBox.getItems().add("Qualquer categoria");

        CategoriaPrecoChoiceBox.setValue("Faixa de Preço");
        CategoriaPrecoChoiceBox.getItems().clear();
        CategoriaPrecoChoiceBox.getItems().add("Popular");
        CategoriaPrecoChoiceBox.getItems().add("Médio");
        CategoriaPrecoChoiceBox.getItems().add("Luxo");
        CategoriaPrecoChoiceBox.getItems().add("Qualquer preço");
    }
}
