import models.Course;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;


public class Main {

    public final static int PORT = 1337;

    public static void main (String args[]) throws Exception {

        Socket s = new Socket("localhost", PORT);

        // envoyer "CHARGER" au client en premier pcq il faut d'abord demander au client de regarder les cours par default
        // CMD = CHARGER ; ARG = NUMERO DU INPUT
        Scanner scanner = new Scanner(System.in);

        System.out.println(" *** Bienvenue au portail d'inscription de cours de l'UDEM *** \n" +
                "Veuillez choisir la session pour laquelle vous voulez consulter la liste des cours: \n" +
                "1. Automne \n2. Hiver \n3. Ete ");
        String inputSession = scanner.nextLine();
        System.out.println("> Choix: " + inputSession);

        // Envoyer la requête CHARGER au serveur
        Charger charger = new Charger(s, inputSession);
        ArrayList<Course> listeCours = charger.loadCourses(); // does the method and returns a value that comes back to main

        System.out.println("""
                > Choix:
                1. Consulter les cours offerts pour une autre session
                2. Inscription à un cours""");
        String input = scanner.nextLine();
        System.out.println("> Choix: " + input);

        Inscription inscription = new Inscription(s, listeCours); // so that inscription also has the course list information

        // if input = 1 -> redo charger but with this input as the variable
        // make a big while true loop si that it can repeat indefinitely if i want it to
        // if input = 2 -> instantiate inscription & do the method in the thing, everything that has to do w the server is
        // in the CLASS
        // dont forget verification in Inscription, so add the liste de cours from course (something returns????)
        // so that i'm able to add it to the constructor
        // message d'echec en cas d'exception but do i keep trying the program or do i break



        // call inscription class/method w/ a constructor
        // inscription needs to verify if the course choice is correct
        // also use local variables w the inputs & the returns of the functions to get the confirmation message at the end
        //Inscription inscrire = new Inscription(os, s);

    }

}
