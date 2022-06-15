module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;
    requires java.sql;
    requires java.prefs;
    requires javafx.web;
    requires java.desktop;

    opens com.example.demo to javafx.fxml,javafx.graphics;
    exports com.example.demo;
}