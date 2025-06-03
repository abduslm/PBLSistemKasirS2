module com.kedai.temeji {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;
    requires jasperreports;
    requires javafx.graphics;
    
    opens com.kedai.temeji to javafx.fxml;
    exports com.kedai.temeji;
}
