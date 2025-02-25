package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class TelaFaturamentoController {

    @FXML
    private Button BuscarFaturamento;

    @FXML
    private DatePicker DataFinal;

    @FXML
    private DatePicker DataInicio;

    @FXML
    private Label LabelTotalAlugueis;

    @FXML
    private Label LabelTotalFaturamento;

    @FXML
    private Button VoltarButton;


    @FXML
    void btnVoltarClicked(ActionEvent event) {
        SceneManager.getInstance().changeScreen("PainelDeControle.fxml",
                "Painel de Controle");
    }

    @FXML
    void btnBuscarFaturamento(ActionEvent event) {
        float[] valores = Fachada.getInstance().
                getFaturamentoNoPeriodo(DataInicio.getValue(), DataFinal.getValue());

        LabelTotalAlugueis.setText(String.valueOf(valores[1]) + " reservas");
        LabelTotalFaturamento.setText("R$ " + String.valueOf(valores[0]));
    }

    public void setFieldsNull(){
        LabelTotalAlugueis.setText("");
        LabelTotalFaturamento.setText("");
    }
}
