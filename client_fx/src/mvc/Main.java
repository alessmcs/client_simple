package mvc;

public class Main {
    public static void main(String[] args){
        ClientModel modele = new ClientModel();
        ClientView vue = new ClientView();
        ClientController controleur = new ClientController(modele, vue);
    }
}
