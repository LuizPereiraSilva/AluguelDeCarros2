package com.example.alugueldecarros2.Controllers;

import com.example.alugueldecarros2.Negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

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
    private Button baixarPDF;


    @FXML
    void btnVoltarClicked(ActionEvent event) {
        SceneManager.getInstance().changeScreen("PainelDeControle.fxml",
                "Painel de Controle");
    }

    @FXML
    void btnBuscarFaturamento(ActionEvent event) {
        if(DataInicio.getValue() != null || DataFinal.getValue() != null) {
            if(DataInicio.getValue().isBefore(DataFinal.getValue())) {
                float[] valores = Fachada.getInstance().
                        getFaturamentoNoPeriodo(DataInicio.getValue(), DataFinal.getValue());

                LabelTotalAlugueis.setText(String.valueOf(valores[1]) + " reservas");
                LabelTotalFaturamento.setText("R$ " + String.valueOf(valores[0]));
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro ao buscar faturamento");
                alert.setHeaderText("");
                alert.setContentText("Seleção de datas inválida!");
                alert.show();
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao buscar faturamento");
            alert.setHeaderText("");
            alert.setContentText("Preencha os campos corretamente");
            alert.show();
        }
    }

    public void setFieldsNull(){
        LabelTotalAlugueis.setText("");
        LabelTotalFaturamento.setText("");
    }

    public void baixarPDFClicked() throws IOException {

        try {


            File diretorio = new File(".\\newpdf");

            if (!diretorio.exists()) {

                diretorio.mkdirs();
            }

            PDDocument Faturamentopdf = new PDDocument();

            PDPage Pagina = new PDPage(PDRectangle.A4);

            Faturamentopdf.addPage(Pagina);

            PDPageContentStream conteudo = new PDPageContentStream(Faturamentopdf, Pagina);

            conteudo.setFont(PDType1Font.COURIER, 14);

            conteudo.beginText();
            conteudo.newLineAtOffset(50, 700);

            conteudo.showText("Total de Alugueis: " + LabelTotalAlugueis.getText());

            conteudo.endText();

            conteudo.setFont(PDType1Font.COURIER, 14);

            conteudo.beginText();
            conteudo.newLineAtOffset(50, 600);

            conteudo.showText("Faturamento: " + LabelTotalFaturamento.getText());

            conteudo.endText();

            conteudo.setFont(PDType1Font.TIMES_ROMAN, 20);
            conteudo.beginText();
            conteudo.newLineAtOffset(200, 800);
            conteudo.showText("-Relatório de Faturamento- " );
            conteudo.endText();

            conteudo.close();

            Faturamentopdf.save(".\\newpdf\\Faturamentopdf.pdf");

            Faturamentopdf.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF Gerado");
            alert.setHeaderText(null);
            alert.setContentText("PDF foi gerado e salvo com sucesso.");
            alert.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Notify the user that an error occurred
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Falha em criar PDF");
            alert.setContentText("Um erro ocorreu ao tentar gerar o PDF: " + e.getMessage());
            alert.show();
        }






    }
}


