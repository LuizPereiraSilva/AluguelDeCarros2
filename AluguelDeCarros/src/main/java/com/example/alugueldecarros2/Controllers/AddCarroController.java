package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.Carros.CarroJaExisteException;
import com.example.alugueldecarros2.Exceptions.OperacaoBemSucedidaException;
import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Exceptions.RepositorioCheioException;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddCarroController {

    @FXML
    private Button VoltarButton;

    @FXML
    private Button AddCarroButton;

    @FXML
    private TextField ModeloTxt;

    @FXML
    private TextField MarcaTxt;

    @FXML
    private TextField PlacaTxt;


    @FXML
    private ChoiceBox<String> CategoriaChoiceBox;

    @FXML
    private TextField DiariaTxt;


    public void inicializar(){
        CategoriaChoiceBox.getItems().clear();
        CategoriaChoiceBox.getItems().add("Hatchback");
        CategoriaChoiceBox.getItems().add("Sedan");
        CategoriaChoiceBox.getItems().add("Pickup");
        CategoriaChoiceBox.getItems().add("SUV");

        DiariaTxt.clear();
        ModeloTxt.clear();
        PlacaTxt.clear();
        MarcaTxt.clear();
    }

    @FXML
    void btnAddClicked(ActionEvent event) {
        Fachada fachada =  Fachada.getInstance();
        try{
            fachada.cadastrarCarro(CategoriaChoiceBox.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(DiariaTxt.getText()),  PlacaTxt.getText(), ModeloTxt.getText(), MarcaTxt.getText());
        } catch(NumberFormatException e){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Erro ao cadastrar");
            alerta.setHeaderText(null);
            alerta.setContentText("Digite um inteiro válido");
        } catch(CarroJaExisteException e){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Erro ao cadastrar");
            alerta.setHeaderText(null);
            alerta.setContentText("Carro já existe no repositorio");
        } catch(OperacaoInvalidaException e){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Erro ao cadastrar");
            alerta.setHeaderText(null);
            alerta.setContentText("Todos os campos devem ser preenchidos");
        } catch(RepositorioCheioException e){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Erro ao cadastrar");
            alerta.setHeaderText(null);
            alerta.setContentText("Repositório cheio ");
        } catch (OperacaoBemSucedidaException e){
            DiariaTxt.clear();
            ModeloTxt.clear();
            PlacaTxt.clear();
            MarcaTxt.clear();

            SceneManager sceneManager = SceneManager.getInstance();
            sceneManager.changeScreen("PesquisarCarroAdm.fxml", "Pesquisar Carros Administrador");
        }
    }

    @FXML
    void btnVoltarClicked(ActionEvent event){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PesquisarCarroAdm.fxml", "Pesquisar Carro Administrador");
    }
}
