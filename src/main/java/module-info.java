module com.heshanthenura.packagedemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.heshanthenura.packagedemo to javafx.fxml;
    exports com.heshanthenura.packagedemo;
}