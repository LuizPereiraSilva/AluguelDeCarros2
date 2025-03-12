package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.DataInvalidaException;
import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Basico.Reserva;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TelaCarroController {

    @FXML
    private Label CategoriaCarro;

    @FXML
    private Button ConfirmarReservaButton;

    @FXML
    private DatePicker DataFinal;

    @FXML
    private DatePicker DataInicio;

    @FXML
    private ImageView FotoCarro;

    @FXML
    private Label MarcaCarro;

    @FXML
    private Label ModeloCarro;

    @FXML
    private Button SelecionarButton;

    @FXML
    private Label TipoCarro;

    @FXML
    private Label ValorDiaria;

    @FXML
    private Label ValorTotal;

    @FXML
    private Label TotalDeDiarias;

    @FXML
    private Button VoltarButton;

    @FXML
    private ChoiceBox<String> FormaDePagamentoChoiceBox;


    private Carro carro;

    @FXML
    void handleAdicionarButtonAction(ActionEvent event) {
        Fachada fachada = Fachada.getInstance();
        SceneManager sceneManager = SceneManager.getInstance();

        Conta conta = sceneManager.getPerfilClienteController().getCadastro();
        LocalDate dataInicio = DataInicio.getValue();
        LocalDate dataFinal = DataFinal.getValue();
        String formaDePagamento = FormaDePagamentoChoiceBox.getValue();
        Reserva reservaAux = null;

        try {
            reservaAux = fachada.cadastrarReserva(this.carro, conta, dataInicio, dataFinal, formaDePagamento);
        }catch(DataInvalidaException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("");
            alert.setContentText("Selecione datas válidas");
            alert.show();
        } catch(OperacaoInvalidaException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("");
            alert.setContentText("Carro já está reservado no período");
            alert.show();
        } catch(Exception e){}

        if(reservaAux != null){
            sceneManager.changeScreen("TelaReservaConfirmada.fxml", "Tela de reserva confirmada");
            sceneManager.getTelaReservaConfirmadaController().initialize(reservaAux);
        }
    }

    public void setCarro(Carro carro){
        this.carro = carro;
    }

    @FXML
    void handleSelecionarButtonAction(ActionEvent event) {
        LocalDate dataInicio = DataInicio.getValue();
        LocalDate dataFinal = DataFinal.getValue();
        String formaDePagamento = FormaDePagamentoChoiceBox.getSelectionModel().getSelectedItem();

        if(dataInicio !=  null && dataFinal != null && formaDePagamento != null) {
            if(dataInicio.isBefore(dataFinal)) {
                long aux = ChronoUnit.DAYS.between(dataInicio, dataFinal);
                TotalDeDiarias.setText(aux + " diárias");
                ValorTotal.setText("R$ " + (aux * this.carro.getPreco()));
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("");
                alert.setContentText("A data inicial deve ser anterior a data final");
                alert.show();
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("");
            alert.setContentText("Selecione as datas e a forma de pagamento");
            alert.show();
        }
    }

    @FXML
    void handleVoltarButtonAction(ActionEvent event) {
        DataInicio.setValue(null);
        DataFinal.setValue(null);
        FormaDePagamentoChoiceBox.setValue(null);

        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("TelaPesquisa.fxml", "Tela de pesquisa");
    }

    public void initialize(Carro carro){
        this.carro = carro;
        TipoCarro.setText(this.carro.getCategoria());
        MarcaCarro.setText(this.carro.getMarca());
        ValorDiaria.setText("R$ " + this.carro.getPreco());
        ValorTotal.setText("");
        ModeloCarro.setText(this.carro.getModelo());
        ValorTotal.setText(null);
        TotalDeDiarias.setText(null);

        FormaDePagamentoChoiceBox.getItems().clear();
        String[] formasDePagamento = {"Débito", "Crédito", "Pix"};
        FormaDePagamentoChoiceBox.getItems().addAll(formasDePagamento);

        if(this.carro.getPreco() < 150){
            CategoriaCarro.setText("Popular");
        } else if(this.carro.getPreco() < 500){
            CategoriaCarro.setText("Normal");
        }else{
            CategoriaCarro.setText("Luxo");
        }

    }

}


