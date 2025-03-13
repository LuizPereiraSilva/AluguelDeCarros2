package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
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

    @FXML
    private ChoiceBox<String> LocalizacaoChoiceBox;

    private Carro[] carros;

    @FXML
    private Button VoltarButton1;

    @FXML
    private Button BuscarButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.listarCarros();
        this.initialize();

        ListResultados.selectionModelProperty().addListener((observable, oldValue, newValue) -> {
            SceneManager sceneManager = SceneManager.getInstance();
            sceneManager.changeScreen("TelaCarro.fxml", "Tela Carro Selecionado");
        });
    }


    @FXML
    public void listarCarros(){

        Fachada fachada = Fachada.getInstance();
        String categoria = CategoriaCarroChoiceBox.getSelectionModel().getSelectedItem();
        String faixaDePreco = CategoriaPrecoChoiceBox.getSelectionModel().getSelectedItem();
        LocalDate dataInicial = DataInicialDatePicker.getValue();
        LocalDate dataFinal = DataFinalDatePicker.getValue();
        Carro[] lista = null;

        try {
            if (dataInicial == null || dataFinal == null) {

                if(dataInicial == null && dataFinal == null) {

                    lista = fachada.getListaCarros(categoria, faixaDePreco);

                }else if (dataInicial == null) {

                    lista = fachada.getListaCarrosAntesDaData(dataFinal,
                            categoria, faixaDePreco);

                } else {

                    lista = fachada.getListaCarrosAPartirDaData(dataInicial,
                            categoria, faixaDePreco);

                }
            } else {

                lista = fachada.getListaCarrosNoPeriodo(dataInicial,
                        dataFinal, categoria, faixaDePreco);

            }
        } catch(OperacaoInvalidaException ex) {

            lista = fachada.getListaInicialCarros();
        }

        carros = lista;

        ListResultados.getItems().clear();

        for (int i = 0; i < lista.length; i++) {
            if(lista[i] != null && lista[i].getDisponivel()) {
                ListResultados.getItems().add(lista[i].adicionarNaLista());
            }
        }
    }


    @FXML
    void handleVoltarButton1Action(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.getPerfilClienteController().initialize(sceneManager.
                getPerfilClienteController().getCadastro());
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

        LocalizacaoChoiceBox.getItems().clear();
        String[] lista = {"Recife", "Olinda", "Jaboatão dos Guararapes", "Caruaru", "Petrolina"};
        LocalizacaoChoiceBox.getItems().addAll(lista);

        ListResultados.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue != null) {
                    for (int i = 0; i < carros.length; i++) {
                        if (carros[i] != null && newValue.equals(carros[i].adicionarNaLista())) {
                            SceneManager sceneManager = SceneManager.getInstance();
                            sceneManager.changeScreen("TelaCarro.fxml", "Tela do Carro Selecionado");
                            sceneManager.getTelaCarroController().initialize(carros[i]);
                        }
                    }
                }
            }
        });
    }
}
