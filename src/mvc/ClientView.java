package mvc;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mvc.models.Course;
import java.util.*;


public class ClientView extends Application {

        private TableView table = new TableView();
        private Button boutonCharger;
        private Button boutonInscrire;
        private ComboBox<String> liste;

        private TextField nom;
        private TextField prenom;
        private TextField email;
        private TextField matricule;
        private ClientController controller;
    private TableColumn<Course, String> codeCol;
    private TableColumn<Course, String> coursCol;

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
            this.codeCol = new TableColumn("Code");
            this.coursCol = new TableColumn("Cours");
            // Créer un listener pour identifier le cours choisi par l'utilisateur
            table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                if (newValue != null) {
                    controller.setCours(newValue);
                }
            });

            table.getColumns().addAll(coursCol, codeCol);
            table.setPrefSize(250, 320);
            this.liste = new ComboBox<>();
            liste.getItems().addAll("Hiver", "Automne", "Été");
            liste.setValue("Hiver");
            // Créer un listener pour indiquer la session choisie par l'utilisateur
            liste.valueProperty().addListener((obs, oldVal, newVal) -> {
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
            this.nom = new TextField();
            formGrid.addRow(0, nomLabel, nom);

            Label prenomLabel = new Label("Prénom");
            this.prenom = new TextField();
            formGrid.addRow(1, prenomLabel, prenom);

            Label emailLabel = new Label("Email");
            this.email = new TextField();
            formGrid.addRow(2, emailLabel, email);

            Label matriculeLabel = new Label("Matricule");
            this.matricule = new TextField();
            formGrid.addRow(3, matriculeLabel, matricule);

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

        public String getNom(){
            return nom.getText();
        }

        public String getPrenom(){
            return prenom.getText();
        }

        public String getEmail(){
            return email.getText();
        }

        public String getMatricule(){
            return matricule.getText();
        }

        public void updateTable(ArrayList<Course> courseList) { // updates the table based on the arraylist being given from models layer
            ObservableList<Course> courses = FXCollections.observableArrayList(courseList);
            codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
            coursCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            // add new data inside the table
            table.setItems(courses);
        }
    }
