module com.example.alugueldecarrosteste2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.alugueldecarrosteste2 to javafx.fxml;
    exports com.example.alugueldecarrosteste2;
}