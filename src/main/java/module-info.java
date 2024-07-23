module com.example.textprocessing {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.textprocessing to javafx.fxml;
    exports com.example.textprocessing;
}