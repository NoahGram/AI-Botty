module com.example.aiassistent {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.aiassistent to javafx.fxml;
    exports com.example.aiassistent;
}