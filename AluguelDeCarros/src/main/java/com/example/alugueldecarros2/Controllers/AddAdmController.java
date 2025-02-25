package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.Contas.ContaJaExisteException;
import com.example.alugueldecarros2.Exceptions.OperacaoBemSucedidaException;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddAdmController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField TextNome;

    @FXML
    private TextField TextCpf;

    @FXML
    private TextField TextTelefone;

    @FXML
    private TextField TextEmail;

    @FXML
    private TextField TextSenha;


    @FXML
    void btnAddClicked(ActionEvent event) {
        String nome = TextNome.getText();
        String cpf = TextCpf.getText();
        String telefone = TextTelefone.getText();
        String email = TextEmail.getText();
        String senha = TextSenha.getText();

        if(nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() ||
                email.isEmpty() || senha.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao cadastrar Administrador");
            alert.setHeaderText(null);
            alert.setContentText("Preencha os campos");
            alert.show();
        } else{
            try {
                Fachada.getInstance().cadastrarAdministrador(nome, cpf, telefone, email, senha);
            } catch(ContaJaExisteException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro ao cadastrar Administrador");
                alert.setHeaderText(null);
                alert.setContentText("Conta já existe no repositorio.");
                alert.show();
            } catch(OperacaoBemSucedidaException e){
                this.setFieldsNull();
                SceneManager.getInstance().changeScreen("PesquisarUsuarioAdm.fxml",
                        "Pesquisar Contas Administrador");

            } catch(Exception e){}
        }
    }

    @FXML
    void btnVoltarClicked(ActionEvent event){
        this.setFieldsNull();
        SceneManager.getInstance().changeScreen("PesquisarUsuarioAdm.fxml",
                "Pesquisar Contas Administrador");
    }

    private void setFieldsNull(){
        TextNome.setText("");
        TextCpf.setText("");
        TextTelefone.setText("");
        TextEmail.setText("");
        TextSenha.setText("");
    }

}
