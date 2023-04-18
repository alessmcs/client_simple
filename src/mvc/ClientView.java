package mvc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;


    public class ClientView extends Application {

        private TableView table = new TableView();
        private Button boutonCharger;
        private Button boutonInscrire;
        private ComboBox<String> liste;

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
            this.liste = new ComboBox<>();
            liste.getItems().addAll("Hiver", "Automne", "Été");
            liste.setValue("Hiver");
            // add a listener to the combo box to detect a change in the selected value
            // tie that instance to the controller class
            liste.valueProperty().addListener((obs, oldVal, newVal) -> {
                ClientController controller = null;
                String sem = liste.getValue();
                controller.setSelectedSemester(sem);
            });

            this.boutonCharger = new Button("charger");

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
            hbox.getChildren().addAll(liste, boutonCharger);
            vbox.getChildren().addAll(listeCours, table, horizontal, hbox);

            Label formulaireIns = new Label("Formulaire d'inscription");
            formulaireIns.setFont(new Font("Arial", 21));

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

            this.boutonInscrire = new Button("envoyer");
            formGrid.addRow(5, boutonInscrire);

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
        public Button getBoutonCharger(){
            return this.boutonCharger;
        }

        public Button getBoutonInscrire(){
            return this.boutonInscrire;
        }

        public String getSem(){
            return liste.getValue();
        }
    }
