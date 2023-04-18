package mvc;

/**
 * Cette classe lie le modèle avec la vue.
 */
public class ClientController {
    private ClientModel modele;
    private ClientView vue;
    private String selectedSemester;

    public ClientController(ClientModel m, ClientView v) {
        this.modele = m;
        this.vue = v;


    }

    public String getSelectedSemester(){
        return selectedSemester;
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
