package main;

import java.io.*;
import java.net.*;
import java.util.*;
import main.models.Course;

/**
 * Permet au client de récupérer la liste des cours disponibles pour une session donnée. Le client envoie une requête
 * "CHARGER"" au serveur. Le serveur doit récupérer la liste des cours du fichier cours.txt et l’envoie au client.
 * Le client récupère les cours et les affiche.
 */

public class Charger {
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private String commande;
    private String session;
    private String nomSession;
    private Socket s;
    private ArrayList<Course> courseList;


    /**
     * Instancie Charger en ouvrant la connexion au Socket et en envoyant une requête qui contient l'entrée de
     * l'utilisateur (de Main.java). Si l'utilisateur a entré le nom de la session (automne, ete, hiver) au lieu d'un
     * chiffre (1,2,3), change la variable en son chiffre respectif.
     * Lit aussi le inputStream du serveur.
     * @param port
     * @param session
     */
    public Charger(int port, String session) throws Exception {

        this.s = new Socket("localhost", port);
        this.session = session;

        switch (session.toLowerCase()) {
            case "automne":
                session = "1";
                break;
            case "hiver":
                session = "2";
                break;
            case "ete":
                session = "3";
                break;
            default:

                this.courseList = new ArrayList<>();
                this.commande = "CHARGER " + session;

                outputStream = new ObjectOutputStream(s.getOutputStream());
                outputStream.writeObject(commande);
                outputStream.flush();

                this.inputStream = new ObjectInputStream(s.getInputStream());

        }
    }

    /**
     * Retourne la liste de cours pour la session choisie par l'utilisateur. Cette liste sera utilisée dans
     * Inscription.java pour valider le choix de cours.
     * Lit le inputStream (obtenu et enregistré dans le constructeur), qui est une liste de Strings identifiant
     * les paramètres d'un objet Course. Découpe ensuite le tableau pour faire sortir les valeurs des variables qu'on
     * associera à une instance de Course, qui sera ajoutée a une liste d'objets Course, contenant les
     * informations des cours donnés pendant la session choisie.
     *
     * @return la liste des cours pour une session donnée
     */
    public ArrayList<Course> loadCourses(){
        try {
            ArrayList<String> obj = (ArrayList<String>) inputStream.readObject();
            System.out.println(obj);
            for(String i : obj){
                Course cours;
                String varAssignments = i.substring(i.indexOf("{") + 1, i.indexOf("}"));
                String[] variables = varAssignments.split(",\\s*");
                String name = "";
                String code = "";
                String session = "";

                String[] varA = variables[0].split("=");
                name = varA[1];

                String[] varB = variables[1].split("=");
                code = varB[1];

                String[] varC = variables[2].split("=");
                session = varC[1];
                cours = new Course(name, code, session);
                this.courseList.add(cours);
            }
        } catch (ClassNotFoundException | IOException | ClassCastException e ) {
            System.out.println("Erreur lors du chargement des cours. " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (inputStream != null) { // the inputstream could be null, poor deserialisation
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        switch (session) {
            case "1":
                this.nomSession = "automne";
                break;
            case "2":
                this.nomSession = "hiver";
                break;
            case "3":
                this.nomSession = "ete";
                break;
            default:
                throw new IllegalArgumentException("Session invalide: " + session);
        }
        System.out.println("Les cours offerts pour la session d'" + nomSession + " sont:");
        System.out.println(courseList);
        int i = 1;
        for (Course c : courseList) {
            System.out.println(i + ". " + " " + c.getCode() + " " + c.getName());
            i++;
        }
        return courseList;
    }
}

