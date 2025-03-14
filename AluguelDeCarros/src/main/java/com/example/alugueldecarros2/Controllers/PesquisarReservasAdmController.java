package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.Contas.ContaNaoExisteException;
import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Basico.Reserva;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PesquisarReservasAdmController implements Initializable {

    @FXML
    private TextField TextNumeroReserva;

    @FXML
    private TextField TextCpf;

    @FXML
    private ChoiceBox<String> CategoriaCarroChoiceBox;

    @FXML
    private ChoiceBox<String> CategoriaPrecoChoiceBox;

    @FXML
    private DatePicker DataFinalDatePicker;

    @FXML
    private DatePicker DataInicialDatePicker;

    @FXML
    private TableView<Reserva> ReservasTableView;


    @FXML
    private Button VoltarButton;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button GerarRelatorioButton;

    @FXML
    private Button GerarRelatorioButton1;

    @FXML
    private Button GerarRelatorioButton2;

    private Reserva[] reservas;

    @FXML
    void handleVoltarButtonAction(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PainelDeControle.fxml",
                "Painel de Controle");
    }


    @FXML
    void handleGerarRelatorioButtonAction(ActionEvent event) {
        Fachada fachada = Fachada.getInstance();
        String categoria = CategoriaCarroChoiceBox.getSelectionModel().getSelectedItem();
        String faixaDePreco = CategoriaPrecoChoiceBox.getSelectionModel().getSelectedItem();
        LocalDate dataInicial = DataInicialDatePicker.getValue();
        LocalDate dataFinal = DataFinalDatePicker.getValue();
        Reserva[] lista = null;

        try {
            if (dataInicial == null || dataFinal == null) {

                System.out.println("Teste 1");

                if(dataInicial == null && dataFinal == null) {
                    lista = fachada.getListaReservas(categoria, faixaDePreco);

                }

                if (dataInicial == null && dataFinal != null) {
                    lista = fachada.getListaReservasAntesDaData(dataFinal,
                            categoria, faixaDePreco);

                }

                if (dataInicial != null && dataFinal == null) {
                    System.out.println("Teste 2");
                    lista = fachada.getListaReservasAPartirDaData(dataInicial,
                            categoria, faixaDePreco);

                }

            } else {
                lista = fachada.getListaReservasNoPeriodo(dataInicial,
                        dataFinal, categoria, faixaDePreco);

            }
        } catch(OperacaoInvalidaException ex) {
            lista = fachada.getListaInicialReservas();
        }

        reservas = lista;

        ReservasTableView.getItems().clear();

        ReservasTableView.getItems().addAll(lista);
    }


    @FXML
    void handleGerarRelatorioButtonAction1(ActionEvent event){
        Fachada fachada =  Fachada.getInstance();
        Conta contaAux = null;

        try {
            contaAux = fachada.buscarContaPeloCpf(TextCpf.getText());
        } catch(ContaNaoExisteException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao gerar relatorio");
            alert.setHeaderText("");
            alert.setContentText(e.getMessage());
            alert.show();
        }

        Reserva[] reservas = fachada.buscarReservasCliente(contaAux.getIdConta());

        ReservasTableView.getItems().clear();
        ReservasTableView.getItems().addAll(reservas);
    }

    @FXML
    void handleGerarRelatorioButtonAction2(ActionEvent event){
        Fachada fachada = Fachada.getInstance();
        Reserva reservaAux = null;
        try {
            reservaAux = fachada.buscarReserva(Integer.parseInt(TextNumeroReserva.getText()));
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setContentText(e.getMessage());
            alert.setTitle("Erro ao gerar relatorio");
            alert.show();
        }

        ReservasTableView.getItems().clear();
        ReservasTableView.getItems().addAll(reservaAux);
    }

    void initialize(){
        Fachada fachada = Fachada.getInstance();

        CategoriaCarroChoiceBox.getItems().clear();
        CategoriaPrecoChoiceBox.getItems().clear();

        String[] listaCCarro = {"Hatchback", "Sedan", "Pickup", "SUV", "Qualquer categoria"};
        String[] listaCPreco = {"Popular", "Médio", "Luxo", "Qualquer preço"};

        CategoriaCarroChoiceBox.getItems().addAll(listaCCarro);
        CategoriaPrecoChoiceBox.getItems().addAll(listaCPreco);

        Reserva[] reservas = null;

        reservas = fachada.getListaInicialReservas();

        ReservasTableView.getColumns().clear();
        ReservasTableView.getItems().clear();

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

        ReservasTableView.getColumns().add(carroColuna);
        ReservasTableView.getColumns().add(dataInicioColuna);
        ReservasTableView.getColumns().add(dataFinalColuna);
        ReservasTableView.getColumns().add(formaDePagamento);
        ReservasTableView.getColumns().add(valorTotal);

        ObservableList<Reserva> reservasAux = FXCollections.observableArrayList(reservas);

        ReservasTableView.getItems().addAll(reservasAux);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
    }
}
