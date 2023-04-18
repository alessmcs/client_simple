package main;

import main.models.Course;
import java.util.*;

/**
 * Contient le fonctionnement d'un client en ligne de commande simple et non graphique qui permet à l'utilisateur
 * de se connecter au serveur, de visionner le choix de cours et de s'inscrire à un cours.
 * Elle lance aussi les exceptions qui n'ont pas été attrapées dans les classes Charger.java et Inscription.java.
 */
public class Main {

    public final static int PORT = 1338;
    private static ArrayList<Course> listeCours;

    public static void main (String args[]) {
    try(Scanner scanner = new Scanner(System.in);)
    {
        // par defaut, le client est toujours demandé de choisir une session
        System.out.println(" *** Bienvenue au portail d'inscription de cours de l'UDEM ***");
        System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste des cours: \n" +
                "1. Automne \n2. Hiver \n3. Ete ");

        String inputSession = scanner.nextLine();
        System.out.println("> Choix: " + inputSession);

        Charger charger = new Charger(PORT, inputSession);
        listeCours = charger.loadCourses();

        // boucle infinie, pour permettre à l'utilisateur de faire plusieur choix
        boolean bool = true;
        while(bool) {
            System.out.println("""
                    > Choix:
                    1. Consulter les cours offerts pour une autre session
                    2. Inscription à un cours""");
            String option = scanner.nextLine();
            System.out.println("> Choix: " + option);

            switch(option){
                case "2":
                    Inscription inscription = new Inscription(PORT, listeCours);
                    inscription.register();
                    break;

                case "1":
                      System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste des cours: \n" +
                              "1. Automne \n2. Hiver \n3. Ete ");
                    inputSession = scanner.nextLine();
                    charger = new Charger(PORT, inputSession);
                    System.out.println("> Choix: " + inputSession);
                    listeCours = charger.loadCourses();
                default:
                    System.out.println("Option invalide, svp réessayer.");
                    break;
            }
        }

        } catch(Exception e){
        e.printStackTrace();
    }
}
}


