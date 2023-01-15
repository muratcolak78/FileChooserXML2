package com.example.filechooserxml2;

import javafx.fxml.FXML;
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
    public void onHelloButtonClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        selectedFile = fileChooser.showOpenDialog(mainStage);
        absolutePath = selectedFile.getAbsolutePath();
        String fileName = selectedFile.getName();

        if (selectedFile != null) {

            String content = new String(Files.readAllBytes(Paths.get(absolutePath)));

            welcomeText.setText(fileName);
            textArea.setText(content);
            System.out.println(content);
        } else {
            System.out.println("file is not valid");
        }

    }


    public void onNameClik() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(absolutePath));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <staff>
            NodeList list = doc.getElementsByTagName("staff");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get staff's attribute
                    String id = element.getAttribute("id");

                    // get text
                    String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
                    String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
                    String nickname = element.getElementsByTagName("nickname").item(0).getTextContent();
                    String salary = element.getElementsByTagName("salary").item(0).getTextContent();

                    NodeList salaryNodeList = element.getElementsByTagName("salary");


                    // get salary's attribute
                    String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

                    textArea.setText("Current Element :" + node.getNodeName() + "\n" +
                            "Staff Id : " + id + "\n" +
                            "First Name : " + firstname + "\n" +
                            "Last Name : " + lastname + "\n" +
                            "Nick Name : " + nickname + "\n" +
                            "Salary [Currency] : " + salary);

                    System.out.println("Current Element :" + node.getNodeName());
                    System.out.println("Staff Id : " + id);
                    System.out.println("First Name : " + firstname);
                    System.out.println("Last Name : " + lastname);
                    System.out.println("Nick Name : " + nickname);
                    System.out.printf("Salary [Currency] : " + salary);
                }
            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}
