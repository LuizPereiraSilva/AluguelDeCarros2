package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.event.ChangeEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.net.URL;
import java.util.ResourceBundle;

public class PesquisarCarroAdmController implements Initializable {

    @FXML
    private Button VoltarButton;

    @FXML
    private Button NovoCarroButton;

    @FXML
    private Button BuscarButton;

    @FXML
    private Button BuscarPlacaButton;

    @FXML
    private ChoiceBox<String> CategoriaCarroChoiceBox;

    @FXML
    private ChoiceBox<String> CategoriaPrecoChoiceBox;

    @FXML
    private TextField TextPlaca;

    @FXML
    private ListView<String> ListResultados;

    private Carro[] carros;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initialize();
        this.listarCarros();

        ListResultados.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                SceneManager sceneManager = SceneManager.getInstance();

                Carro carro = null;
                int i = 0;
                while(carros[i] != null){
                    if(t1.contains(carros[i].getPlaca())){
                        carro = carros[i];
                        break;
                    }
                }
                sceneManager.getEditarCarroController().initialize(carro);
                sceneManager.changeScreen("EditarCarro.fxml", "Editar Carro");
            }
        });
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

//    private void adicionarCarroTeste(){
//        Fachada fachada = Fachada.getInstance();
//        carros = fachada.getListaCarros();
//        ListResultados.getItems().add(carros[0].adicionarNaLista());
//    }

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
    public void buscarPlaca(){
        Fachada fachada = Fachada.getInstance();
        ListResultados.getItems().clear();

        try {
            ListResultados.getItems().add(fachada.buscarCarroPorPlaca(
                    TextPlaca.getText()).adicionarNaLista());
        } catch(CarroNaoExisteException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Problema ao buscar");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    void handleVoltarButtonAction(ActionEvent event){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PainelDeControle.fxml",
                "Painel de Controle");
    }

    @FXML
    void handleNovoCarroButton(ActionEvent event){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("AddCarro.fxml", "Adicionar Carro");
    }
}
