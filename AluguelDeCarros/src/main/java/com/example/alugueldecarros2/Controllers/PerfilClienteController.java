package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Basico.Reserva;
import com.example.alugueldecarros2.Negocio.Fachada;
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

    private Conta cadastro;


    public Conta getCadastro(){
        return cadastro;
    }

    public void setCadastro(Conta cadastro){
        this.cadastro = cadastro;
    }


    public void initialize(){
        Fachada fachada = Fachada.getInstance();

        if(cadastro != null) {
            LabelCpf.setText(cadastro.getCpf());
            LabelNome.setText(cadastro.getNome());
            LabelTelefone.setText(cadastro.getTelefone());
            LabelEmail.setText(cadastro.getEmail());
        }

        if(cadastro != null) {
            Reserva[] reservas = fachada.buscarReservasCliente(cadastro.getIdConta());

            TabelaReservas.getColumns().clear();
            TabelaReservas.getItems().clear();

            TabelaReservas.getColumns().add(new TableColumn<Reserva, String>("Carro Reservado: "));
            TabelaReservas.getColumns().add(new TableColumn<Reserva, LocalDate>("Data de Início: "));
            TabelaReservas.getColumns().add(new TableColumn<Reserva, LocalDate>("Data final: "));
            TabelaReservas.getColumns().add(new TableColumn<Reserva, String>("Forma de Pagamento: "));


        }
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
