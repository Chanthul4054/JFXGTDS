module com.iit.tutorials.jfxgtds {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.iit.tutorials.jfxgtds to javafx.fxml;
    exports com.iit.tutorials.jfxgtds;
}