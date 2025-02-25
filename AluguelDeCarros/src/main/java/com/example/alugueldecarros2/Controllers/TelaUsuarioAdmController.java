package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TelaUsuarioAdmController {

    @FXML
    private Button VoltarButton;

    @FXML
    private Button RemoverUserButton;

    @FXML
    private Label NomeUserText;

    @FXML
    private Label EmailText;

    @FXML
    private Label TelefoneText;

    @FXML
    private Label CpfUserText;

    private Conta conta;

    @FXML
    void handleRemoverUserButton(ActionEvent event){
        try {
            Fachada.getInstance().removerConta(conta.getIdConta());
        } catch (Exception ex) {}

        SceneManager.getInstance().getPesquisarUsuarioAdmController().inicializar();
        SceneManager.getInstance().changeScreen("PesquisarUsuarioAdm.fxml",
                "Pesquisar Usuario Administrador");

    }

    @FXML
    void handleVoltarButtonAction(ActionEvent event){
        SceneManager.getInstance().changeScreen("PesquisarUsuarioAdm.fxml",
                "Pesquisar Usuario Administrador");
    }


    public void initialize(Conta conta){
        this.conta = conta;
        NomeUserText.setText(conta.getNome());
        EmailText.setText(conta.getEmail());
        TelefoneText.setText(conta.getTelefone());
        CpfUserText.setText(conta.getCpf());
    }
}
