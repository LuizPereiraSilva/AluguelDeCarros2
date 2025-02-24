module com.example.alugueldecarrosteste2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;


    opens com.example.alugueldecarros2 to javafx.fxml;
    exports com.example.alugueldecarros2;
    exports com.example.alugueldecarros2.Controllers;
    opens com.example.alugueldecarros2.Controllers to javafx.fxml;
    exports com.example.alugueldecarros2.Negocio.Basico;
    opens com.example.alugueldecarros2.Negocio.Basico to javafx.fxml;
}