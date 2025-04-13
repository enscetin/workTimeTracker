module org.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;
    requires java.sql;


    opens controllers to javafx.fxml;
    opens model to com.google.gson;
    exports controllers;
    exports model to com.google.gson;
}

