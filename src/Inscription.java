/**
 * F1: une première fonctionnalité qui permet au client de récupérer
 * la liste des cours disponibles pour une session donnée. Le client
 * envoie une requête charger au serveur. Le serveur doit récupérer
 * la liste des cours du fichier cours.txt et l’envoie au client.
 * Le client récupère les cours et les affiche.
 */
import java.io.*;
import java.net.*;
import java.util.*;

import models.RegistrationForm;
import models.Course;

public class Inscription implements Serializable {

    private Socket s;
    private ObjectOutputStream os;
    private Course course;
    private ArrayList<Course> courseList;
    private ArrayList<String> courseCodes;
    private String commande;

    public Inscription( Socket s, ArrayList<Course> courseList ) throws IOException {
        this.s = s;
        this.courseList = courseList;
        this.os = new ObjectOutputStream(s.getOutputStream());
        for (Course c : courseList){
            courseCodes.add(c.getCode());
        }
        this.commande = "INSCRIRE ";
    }

    public void register(Socket s) throws Exception {
        // Le client donne les informations pour le cours désiré

        // d'abord, envoyer la requête "INSCRIRE" au serveur
        os.writeObject(commande);
        os.flush();

           Scanner scanner = new Scanner(System.in);

           System.out.println("Veuillez saisir votre prénom: ");
           String prenom = scanner.nextLine();

           System.out.println("Veuillez saisir votre nom: ");
           String nom = scanner.nextLine();

           System.out.println("Veuillez saisir votre email: ");
           String email = scanner.nextLine();

           System.out.println("Veuillez saisir votre matricule: ");
           String matricule = scanner.nextLine();

           System.out.println("Veuillez saisir le code du cours: ");
           String codeCours = scanner.nextLine();
           // verification de l'entree de l'utilisateur
           if (courseCodes.contains(codeCours)) {
               int index = courseCodes.indexOf(codeCours);
               this.course = courseList.get(index);
               System.out.println("Félicitations! Inscription réussie de " + nom + " au cours " + codeCours); // si le cours est disponible, afficher directement le message
           } else {
               throw new Exception("Le cours fourni n'est pas disponible pour la session choisie. ");
           }

           RegistrationForm form = new RegistrationForm(prenom, nom, email, matricule, course);
           os.writeObject(form);
           os.flush();
           os.close();
           scanner.close();
           s.close();
    }

}

