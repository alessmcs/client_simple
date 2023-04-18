package mvc;

import javafx.scene.control.TextField;
import mvc.models.Course;

import java.util.ArrayList;

/**
 * Cette classe lie le modèle avec la vue.
 */
public class ClientController {
    private ClientModel modele;
    private ClientView vue;
    private String selectedSemester;

    private int port = 1337;

    private ArrayList<Course> courseList;

    private String nom;
    private String prenom;
    private String email;
    private String matricule;
    private Course cours;

    public ClientController(ClientModel m, ClientView v) {
        this.modele = m;
        this.vue = v;

        this.vue.getBoutonCharger().setOnAction((action)->{
            String val = this.vue.getSem();
            this.setSelectedSemester(val);
            try {
                this.charger();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        this.vue.getBoutonInscrire().setOnAction((action)->{
            try {
                this.setCoordonnees();
                this.inscrire();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void charger() throws Exception {
        this.modele.newCharger(port, selectedSemester);
        this.modele.makeListeCours();
        this.vue.updateTable(modele.getCourseList()); // update the course table
    }

    public void inscrire() throws Exception {
        this.courseList = this.modele.getCourseList();
        this.modele.newInscription(port,courseList); // envoie la requête au serveur
        this.modele.register(prenom, nom, email, matricule, cours.getCode());
    }

    // Gets the selected semester from the javaFx interface, when the user chooses an item in the list
    public void setSelectedSemester(String selectedValue){
        switch(selectedValue){
            case "Hiver":
                this.selectedSemester = "1";
            case "Automne":
                this.selectedSemester = "2";
            case "Ete":
                this.selectedSemester = "3";
            default:
        }
    }

    public void setCoordonnees(){
         this.nom = this.vue.getNom();
         this.prenom = this.vue.getPrenom();
         this.email = this.vue.getEmail();
         this.matricule = this.vue.getMatricule();
    }

    public void setCours(Object item){
        this.cours = (Course) item;
    }
}
