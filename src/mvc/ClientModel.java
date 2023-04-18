package mvc;
import mvc.clientClasses.*;
import  mvc.models.*;

import java.io.IOException;
import  java.util.*;


public class ClientModel {
    private Charger charger;
    private Inscription inscription;
    private ArrayList<Course> courseList;

    // Ajouter les méthodes qui sont présentes dans Main.java du côté client pour répliquer la fonctionnalité.

// --------- CHARGER
//    System.out.println(" *** Bienvenue au portail d'inscription de cours de l'UDEM ***");
//    System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste des cours: \n" +
//                "1. Automne \n2. Hiver \n3. Ete ");
//
//    String inputSession = scanner.nextLine();
//        System.out.println("> Choix: " + inputSession);
//
//    main.Charger charger = new main.Charger(PORT, inputSession);
//    listeCours = charger.loadCourses();
    public void newCharger(int port, String selectedValue) throws Exception {
        this.charger = new Charger(port, selectedValue);
    }

    public void makeListeCours(){
        this.courseList = charger.loadCourses();
    }
    public ArrayList<Course> getCourseList(){ return this.courseList;}

//    main.Inscription inscription = new main.Inscription(PORT, listeCours);
//    inscription.register();
    public void newInscription(int port, ArrayList<Course> courseList) throws IOException {
        this.inscription = new Inscription(port, courseList);
    }

    public void register() throws Exception {
        inscription.register();
    }
}
