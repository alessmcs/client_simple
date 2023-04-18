import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.*;

import javafx.scene.layout.GridPane;

import javafx.scene.control.TextField;



public class MainWindow extends Application {

    private TableView table = new TableView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Color bgColor = Color.BEIGE;



        Scene scene = new Scene(new Group(), bgColor);
        stage.setTitle("Inscription UdeM");
        stage.setWidth(650);
        stage.setHeight(500);

        Label listeCours = new Label("Liste des Cours");
        listeCours.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn codeCol = new TableColumn("Code");
        TableColumn classCol = new TableColumn("Cours");

        table.getColumns().addAll(classCol, codeCol);
        table.setPrefSize(250, 320);
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Hiver", "Automne", "Été");
        comboBox.setValue("Hiver");

        Button chargerButton = new Button("charger");


        Separator vertical = new Separator();
        vertical.setPrefHeight(10);
        vertical.setStyle("-fx-background-color: white;");
        vertical.setOrientation(Orientation.VERTICAL);
        vertical.setValignment(VPos.CENTER);

        Separator horizontal = new Separator();
        horizontal.setMaxWidth(250);
        vertical.setPrefHeight(10);
        horizontal.setStyle("-fx-background-color: white;");
        horizontal.setOrientation(Orientation.HORIZONTAL);


        Line line = new Line();
        line.setStartX(0.0f); line.setStartY(0.0f); line.setEndX(100.0f); line.setEndY (100.0f);

        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 20, 20));

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10));
        hbox.getChildren().addAll(comboBox, chargerButton);
        vbox.getChildren().addAll(listeCours, table, horizontal, hbox);

        Label formulaireIns = new Label("Formulaire d'inscription");
        formulaireIns.setFont(new Font("Arial", 21));

        // Remplacez le HBox formHBox par un GridPane
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(20));

        Label nomLabel = new Label("Nom");
        TextField nomTextField = new TextField();
        formGrid.addRow(0, nomLabel, nomTextField);

        Label prenomLabel = new Label("Prénom");
        TextField prenomTextField = new TextField();
        formGrid.addRow(1, prenomLabel, prenomTextField);

        Label emailLabel = new Label("Email");
        TextField emailTextField = new TextField();
        formGrid.addRow(2, emailLabel, emailTextField);

        Label matriculeLabel = new Label("Matricule");
        TextField matriculeField = new TextField();
        formGrid.addRow(3, matriculeLabel, matriculeField);

        Button inscrireButton = new Button("envoyer");
        formGrid.addRow(5, inscrireButton);

        VBox formVBox = new VBox();
        formVBox.setAlignment(Pos.CENTER);
        formVBox.setSpacing(5);
        formVBox.setPadding(new Insets(10, 20, 20, 10));
        formVBox.getChildren().addAll(formulaireIns, formGrid);

        HBox mainHBox = new HBox();
        mainHBox.setAlignment(Pos.CENTER);
        mainHBox.setSpacing(10);
        mainHBox.setPadding(new Insets(10));
        mainHBox.getChildren().addAll(vbox, vertical, formVBox);

        ((Group) scene.getRoot()).getChildren().addAll(mainHBox);

        stage.setScene(scene);
        stage.show();
    }
}


