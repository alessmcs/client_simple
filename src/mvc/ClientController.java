package mvc;

import mvc.models.Course;

import java.io.IOException;
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
                this.inscrire();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void charger() throws Exception {
        this.modele.newCharger(port, selectedSemester);
        this.modele.makeListeCours();
    }

    public void inscrire() throws Exception {
        this.courseList = this.modele.getCourseList();
        this.modele.newInscription(port,courseList);
        this.modele.register();
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
}
