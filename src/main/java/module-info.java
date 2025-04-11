module org.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;
    requires java.sql;


    opens org.example.demo2 to javafx.fxml;
    opens model to com.google.gson;
    exports org.example.demo2;
    exports model to com.google.gson;
}

