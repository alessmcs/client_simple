import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créez un objet StackPane pour votre scène
        StackPane root = new StackPane();

        // Créez un bouton pour la fenêtre
        Button btn = new Button("Cliquez ici !");
        root.getChildren().add(btn);

        // Créez une scène pour votre fenêtre
        Scene scene = new Scene(root, 300, 250);

        // Ajoutez la scène à la fenêtre
        primaryStage.setScene(scene);

        // Affichez la fenêtre
        primaryStage.show();


        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
