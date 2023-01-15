module com.example.filechooserxml2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.scripting;


    opens com.example.filechooserxml2 to javafx.fxml;
    exports com.example.filechooserxml2;
}