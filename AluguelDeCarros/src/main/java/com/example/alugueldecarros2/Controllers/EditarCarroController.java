package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.Carros.CarroNaoExisteException;
import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditarCarroController {

    @FXML
    private Button VoltarButton;

    @FXML
    private Button RemoverCarroButton;

    @FXML
    private Button EditarButton;

    @FXML
    private Button TrocarDisponibilidadeButton;


    @FXML
    private TextField ModeloTxtEditar;

    @FXML
    private TextField MarcaTxtEditar;

    @FXML
    private TextField DiariaTxtEditar;


    @FXML
    private ChoiceBox<String> CategoriaChoiceBoxEditar;

    @FXML
    private ChoiceBox<String> DisponibilidadeChoiceBox;


    @FXML
    private Label FaixaDePrecoCarro;

    @FXML
    private Label MarcaCarro;

    @FXML
    private Label ModeloCarro;

    @FXML
    private Label TipoCarro;

    @FXML
    private Label ValorDiaria;

    private Carro carro;


    public void initialize(Carro carro){
        this.carro = carro;
        ModeloCarro.setText(carro.getModelo());
        MarcaCarro.setText(carro.getMarca());
        TipoCarro.setText(carro.getCategoria());
        ValorDiaria.setText("R$ " + carro.getPreco());

        String[] lista = {"Hatchback", "Sedan", "Pickup", "SUV"};

        CategoriaChoiceBoxEditar.getItems().clear();
        CategoriaChoiceBoxEditar.getItems().addAll(lista);

        String[] lista2 = {"Disponível", "Não disponível"};

        DisponibilidadeChoiceBox.getItems().clear();
        DisponibilidadeChoiceBox.getItems().addAll(lista2);

        if(carro.getPreco() < 150){
            FaixaDePrecoCarro.setText("Popular");
        } else if(carro.getPreco() < 500){
            FaixaDePrecoCarro.setText("Médio");
        } else{
            FaixaDePrecoCarro.setText("Luxo");
        }
    }

    @FXML
    void btnRemoverClicked(ActionEvent event) {
        Fachada fachada = Fachada.getInstance();
        try {
            fachada.removerCarro(this.carro.getPlaca());
        } catch(CarroNaoExisteException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao remover");
            alert.setContentText(e.getMessage());
            alert.show();
        }

        carro = null;

        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.getPesquisarCarroAdmController().listarCarros();
        sceneManager.changeScreen("PesquisarCarroAdm.fxml", "Pesquisar Carros");
    }

    @FXML
    void trocarDisponibilidade(){
        Fachada fachada = Fachada.getInstance();
        try {
            if (DisponibilidadeChoiceBox.getSelectionModel().getSelectedItem().equals("Disponível")) {
                fachada.buscarCarroPorPlaca(carro.getPlaca()).setDisponivel(true);
            } else{
                fachada.buscarCarroPorPlaca(carro.getPlaca()).setDisponivel(false);
            }
        }catch(Exception e){}

        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.getPesquisarCarroAdmController().listarCarros();
        sceneManager.changeScreen("PesquisarCarroAdm.fxml", "Pesquisar Carros");
    }

    @FXML
    void btnEditarClicked(ActionEvent event) {
        Fachada fachada = Fachada.getInstance();
        try{
            fachada.atualizarCarro(CategoriaChoiceBoxEditar.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(DiariaTxtEditar.getText()), carro.getPlaca(),
                    ModeloTxtEditar.getText(), MarcaTxtEditar.getText());
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao atualizar");
            alert.setContentText("Digite um valor inteiro");
        } catch(OperacaoInvalidaException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao atualizar");
            alert.setContentText("Todos os campos devem ser preenchidos");
        } catch(CarroNaoExisteException e){

        }

        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PesquisarCarroAdm.fxml", "Pesquisar Carro Administrador");
    }

    @FXML
    void btnVoltarClicked(ActionEvent event){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PesquisarCarroAdm.fxml",
                "Pesquisar Carro Administrador");
    }
}
