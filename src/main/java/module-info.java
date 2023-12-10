module com.example.boxchat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.boxchat to javafx.fxml;
    exports com.example.boxchat;
}