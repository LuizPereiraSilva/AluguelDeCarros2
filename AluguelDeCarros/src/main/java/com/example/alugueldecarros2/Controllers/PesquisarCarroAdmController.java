package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Exceptions.OperacaoInvalidaException;
import com.example.alugueldecarros2.Negocio.Basico.Carro;
import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PesquisarCarroAdmController implements Initializable {

    @FXML
    private Button VoltarButton;

    @FXML
    private Button NovoCarroButton;

    @FXML
    private Button BuscarButton;

    @FXML
    private ChoiceBox<String> CategoriaCarroChoiceBox;

    @FXML
    private ChoiceBox<String> CategoriaPrecoChoiceBox;

    @FXML
    private TextField TextPlaca;

    @FXML
    private ListView<String> ListResultados;

    private Carro[] carros;

    @FXML
    public void listarCarros(){
        Fachada fachada = Fachada.getInstance();
        Carro[] lista = fachada.getListaCarros();
        carros = lista;

        for(int i = 0; i < lista.length; i++) {
            ListResultados.getItems().add(lista[i].adicionarNaLista());
        }
    }

    @FXML
    public void atualizarLista(){
        String categoria = CategoriaCarroChoiceBox.getSelectionModel().getSelectedItem();
        String categoriaPreco = CategoriaPrecoChoiceBox.getSelectionModel().getSelectedItem();
        Carro[] listaAux = new Carro[carros.length];
        float precoMaisBaixo = 0;
        float precoMaisAlto = 0;
        int auxInt = 0;

        if(categoria.equals("Categoria") && categoriaPreco.equals("Faixa de Preço")){
            for(int i = 0; i < carros.length; i++) {
                ListResultados.getItems().add(carros[i].adicionarNaLista());
            }
        }

        try {
            switch (categoriaPreco) {
                case "Popular":
                    precoMaisBaixo = 0f;
                    precoMaisAlto = 150f;
                    break;

                case "Médio":
                    precoMaisBaixo = 151f;
                    precoMaisAlto = 500f;
                    break;

                case "Luxo":
                    precoMaisBaixo = 501f;
                    precoMaisAlto = 10000000f;
                    break;

                default:
                    throw new OperacaoInvalidaException();
            }

            if(categoriaPreco.equals("Faixa de Preço")){
                throw new OperacaoInvalidaException();
            }

            for (int i = 0; i < listaAux.length; i++) {
                if (carros[i].getModelo().equals(categoria) && precoMaisBaixo < carros[i].getPreco()
                        && precoMaisAlto > carros[i].getPreco()) {
                    listaAux[auxInt] = carros[i];
                    auxInt++;
                }
            }

            ListResultados.getItems().clear();
            int i = 0;
            do {
                ListResultados.getItems().add(listaAux[i].adicionarNaLista());
            } while(listaAux[i+1] != null);

        } catch(OperacaoInvalidaException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Operação inválida");
            alert.setContentText("Preencha todos os campos antes de buscar");
            alert.show();
        }
    }

    public void initialize(){
        CategoriaCarroChoiceBox.setValue("Categoria");
        CategoriaCarroChoiceBox.getItems().clear();
        CategoriaCarroChoiceBox.getItems().add("HatchBack");
        CategoriaCarroChoiceBox.getItems().add("Sedan");
        CategoriaCarroChoiceBox.getItems().add("Pickup");
        CategoriaCarroChoiceBox.getItems().add("SUV");

        CategoriaPrecoChoiceBox.setValue("Faixa de Preço");
        CategoriaPrecoChoiceBox.getItems().clear();
        CategoriaPrecoChoiceBox.getItems().add("Popular");
        CategoriaPrecoChoiceBox.getItems().add("Médio");
        CategoriaPrecoChoiceBox.getItems().add("Luxo");

        this.listarCarros();
    }

    @FXML
    void handleVoltarButtonAction(ActionEvent event){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("PainelDeControle.fxml",
                "Painel de Controle");
    }

    @FXML
    void handleNovoCarroButton(ActionEvent event){
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.changeScreen("AddCarro.fxml", "Adicionar Carro");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initialize();
    }
}
