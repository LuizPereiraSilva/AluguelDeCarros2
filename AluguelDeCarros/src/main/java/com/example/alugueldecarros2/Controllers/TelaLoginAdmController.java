package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.Contas.ContaNaoExisteException;
import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class TelaLoginAdmController {

    @FXML
    private ImageView ImagemLogo;

    @FXML
    private Button LoginAdmButton;

    @FXML
    private Button LoginClienteButton;

    @FXML
    private TextField TextCpfAdm;

    @FXML
    private PasswordField TextPasswordAdm;

    @FXML
    void handleLoginAdmButtonAction(ActionEvent event) {
        String cpf = TextCpfAdm.getText();
        String password = TextPasswordAdm.getText();
        Fachada fachada = Fachada.getInstance();
        Conta auxConta = null;
        SceneManager sceneManager = SceneManager.getInstance();

        try{
            auxConta = fachada.buscarContaPeloCpf(cpf);

            if(auxConta.getSenha().equals(password)){
                if(auxConta.getAdministrador()) {
                    sceneManager.getPerfilAdmController().setCadastro(auxConta);
                    sceneManager.changeScreen("PainelDeControle.fxml", "Painel de Controle");
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Problema durante o login");
                    alert.setContentText("Não pode entrar com uma conta cliente ");
                    alert.show();
                }
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Problema durante o login");
                alert.setContentText("Senha incorreta ");
                alert.show();
            }
        } catch(ContaNaoExisteException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Problema durante o login");
            alert.setContentText("Conta não existe");
            alert.show();
        }
    }

    @FXML
    void handleLoginClienteButton(ActionEvent event) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("LoginTela.fxml",
                "Tela de Login");
    }

    public void setFieldsNull(){
        TextCpfAdm.setText(null);
        TextPasswordAdm.setText(null);
    }
}
