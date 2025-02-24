package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Basico.Reserva;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PerfilClienteController{

    @FXML
    private Label LabelCpf;

    @FXML
    private Label LabelNome;

    @FXML
    private Label LabelEmail;

    @FXML
    private Label LabelTelefone;

    @FXML
    private Button SairButton;

    @FXML
    private TableView<Reserva> TabelaReservas;

    @FXML
    private Button VoltarButton;

    @FXML
    private Button CancelarReservaButton;


    private Conta cadastro;

    private Reserva[] reservas;

    private Reserva reservaSelecionada;


    public Conta getCadastro(){
        return cadastro;
    }

    public void setCadastro(Conta cadastro){
        this.cadastro = cadastro;
    }


    public void initialize(){
        Fachada fachada = Fachada.getInstance();
        this.setCadastro();

        if(cadastro != null) {
            LabelCpf.setText(cadastro.getCpf());
            LabelNome.setText(cadastro.getNome());
            LabelTelefone.setText(cadastro.getTelefone());
            LabelEmail.setText(cadastro.getEmail());

            reservas = fachada.buscarReservasCliente(cadastro.getIdConta());

            TabelaReservas.getColumns().clear();
            TabelaReservas.getItems().clear();

            TableColumn <Reserva, String> carroColuna = new TableColumn<>("Carro Reservado: ");
            carroColuna.setCellValueFactory(new PropertyValueFactory<>("carroCaracteristicas"));

            TableColumn <Reserva, LocalDate> dataInicioColuna = new TableColumn<>("Data de Início: ");
            dataInicioColuna.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));

            TableColumn <Reserva, LocalDate> dataFinalColuna = new TableColumn<>("Data final: ");
            dataFinalColuna.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));

            TableColumn <Reserva, String> formaDePagamento = new TableColumn<>("Forma de Pagamento: ");
            formaDePagamento.setCellValueFactory(new PropertyValueFactory<>("formaPagamento"));

            TableColumn <Reserva, String> valorTotal = new TableColumn<>("Valor total: ");
            valorTotal.setCellValueFactory(new PropertyValueFactory<>("stringValorTotal"));

            TabelaReservas.getColumns().add(carroColuna);
            TabelaReservas.getColumns().add(dataInicioColuna);
            TabelaReservas.getColumns().add(dataFinalColuna);
            TabelaReservas.getColumns().add(formaDePagamento);
            TabelaReservas.getColumns().add(valorTotal);

            ObservableList<Reserva> reservasAux = FXCollections.observableArrayList(reservas);

            TabelaReservas.getItems().addAll(reservasAux);

            TabelaReservas.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
                reservaSelecionada = newValue;
            });
        }
    }

    @FXML
    private void cancelarReserva(){
        Fachada fachada = Fachada.getInstance();
        fachada.removerReserva(reservaSelecionada.getNumero());
        this.initialize();
    }

    private void setCadastro(){
        try {
            this.cadastro = Fachada.getInstance().buscarConta(1);
        } catch (Exception ex) {}
    }


    @FXML
    void handleSairButtonAction(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        Fachada fachada = Fachada.getInstance();

        sceneManager.changeScreen("LoginTela.fxml", "Login Tela");
        sceneManager.getLoginTelaController().setFieldsNull();
        cadastro = null;
    }

    @FXML
    void handleVoltarButtonAction(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("TelaPesquisa.fxml", "Tela Pesquisa");
    }
}
