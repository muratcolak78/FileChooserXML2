package com.example.filechooserxml2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileChooserController {

    @FXML
    private Label welcomeText;
    @FXML
    private Stage mainStage;
    @FXML
    private TextArea textArea;

    @FXML
    private String absolutePath;
    @FXML
    private File selectedFile;
    @FXML
    private String fileName;

    @FXML
    public void onHelloButtonClick() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        selectedFile = fileChooser.showOpenDialog(mainStage);
        absolutePath = selectedFile.getAbsolutePath();
        fileName = selectedFile.getName();

        if (selectedFile != null) {

            String content = new String(Files.readAllBytes(Paths.get(absolutePath)));

            welcomeText.setText(fileName);
            textArea.setText(content);
            System.out.println(content);
        } else {
            System.out.println("file is not valid");
        }

    }

    public void onNameClik() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


        try {


            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);


            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(absolutePath));


            //doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println(doc.getDocumentElement().getTextContent());
            textArea.setText(doc.getDocumentElement().getTextContent());
            System.out.println("------");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
