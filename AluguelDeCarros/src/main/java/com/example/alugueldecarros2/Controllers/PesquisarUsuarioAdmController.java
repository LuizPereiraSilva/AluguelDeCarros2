package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Basico.Conta;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PesquisarUsuarioAdmController {

    @FXML
    private Button VoltarButton;

    @FXML
    private Button NovoAdmButton;

    @FXML
    private TextField TextCpf;

    @FXML
    private ListView<String> ListaUsuarios;

    private Conta[] contas;

    @FXML
    void handleVoltarButtonAction(ActionEvent event){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PainelDeControle.fxml",
                "Painel de Controle");
    }

    public void inicializar(){
        Fachada fachada = Fachada.getInstance();
        Conta[] auxConta = fachada.getListaContas();
        Conta cadastro = SceneManager.getInstance().getPerfilAdmController().getCadastro();
        ListaUsuarios.getItems().clear();
        contas = auxConta;

        for(int i = 0; i < auxConta.length; i++){
            if(auxConta[i] != null && !auxConta[i].compareTo(cadastro)){
                ListaUsuarios.getItems().add(auxConta[i].adicionarNaLista());
            }
        }

        ListaUsuarios.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                SceneManager.getInstance().changeScreen("TelaUsuarioAdm.fxml", "Tela de Usuario do Administrador");
                Conta auxConta = null;
                for(int i = 0; i < contas.length; i++){
                    if(contas[i] != null && t1.equals(contas[i].adicionarNaLista())){
                        auxConta = contas[i];
                        break;
                    }
                }
                SceneManager.getInstance().getTelaUsuarioAdmController().initialize(auxConta);
            }
        });
    }

    @FXML
    void handleNovoAdmButton(ActionEvent event){
        SceneManager.getInstance().changeScreen("AddAdm.fxml",
                "Adicionar Conta Administrador");
    }


}
