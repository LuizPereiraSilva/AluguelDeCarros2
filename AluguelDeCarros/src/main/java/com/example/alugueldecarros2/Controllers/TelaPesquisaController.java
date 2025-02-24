package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
        String categoria = CategoriaCarroChoiceBox.getSelectionModel().getSelectedItem();
        String faixaDePreco = CategoriaPrecoChoiceBox.getSelectionModel().getSelectedItem();
        LocalDate dataInicial = DataInicialDatePicker.getValue();
        LocalDate dataFinal = DataFinalDatePicker.getValue();
        Carro[] lista = new Carro[1];

        try {
            if (dataInicial == null || dataFinal == null) {
                System.out.println("Sucesso 1");
                if(dataInicial == null && dataFinal == null) {
                    System.out.println("Sucesso 2");
                    lista = fachada.getListaCarros(categoria, faixaDePreco);

                }else if (dataInicial == null) {
                    System.out.println("Sucesso 3");
                    lista = fachada.getListaCarrosAntesDaData(dataFinal,
                            categoria, faixaDePreco);

                } else {
                    System.out.println("Sucesso 4");
                    lista = fachada.getListaCarrosAPartirDaData(dataInicial,
                            categoria, faixaDePreco);

                }
            } else {
                System.out.println("Sucesso 5");
                lista = fachada.getListaCarrosNoPeriodo(dataInicial,
                        dataFinal, categoria, faixaDePreco);

            }
        } catch(OperacaoInvalidaException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Problema durante a busca de carros");
            alert.setContentText(ex.getMessage());
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
