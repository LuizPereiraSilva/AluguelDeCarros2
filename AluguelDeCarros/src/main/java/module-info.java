module com.example.alugueldecarrosteste2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.alugueldecarros2 to javafx.fxml;
    exports com.example.alugueldecarros2;
    exports com.example.alugueldecarros2.Controllers;
    opens com.example.alugueldecarros2.Controllers to javafx.fxml;
}