package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PesquisarCarroAdmController {

    @FXML
    private Button VoltarButton;

    @FXML
    private Button NovoCarroButton;

    @FXML
    private ChoiceBox<?> CategoriaCarroChoiceBox;

    @FXML
    private ChoiceBox<?> CategoriaPrecoChoiceBox;

    @FXML
    private TextField TextPlaca;

    @FXML
    private ListView<String> ListResultados;

    @FXML
    void listarCarros(){
        Fachada fachada = Fachada.getInstance();
        String[] lista = fachada.getListaCarros();

        for(int i = 0; i < lista.length; i++) {
            ListResultados.getItems().add(lista[i]);
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

    }


}
