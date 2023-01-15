package com.example.filechooserxml2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FileChooserApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FileChooserApplication.class.getResource("fileChooser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 765, 659);
        stage.setTitle("FileChooser XML!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}