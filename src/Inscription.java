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

    public Inscription( Socket s, ArrayList<Course> courseList ) throws IOException {
        this.s = s;
        this.courseList = courseList;
        for (Course c : courseList){
            courseCodes.add(c.getCode());
        }
    }

    public void register(Socket s) throws IOException {
   // 1- The client creates a socket object and connects to the server's IP address and port number.
          // create a constructor with the outputStream and the server defined in main
           // Le client donne les informations pour le cours désiré
           ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

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
           if (courseCodes.contains(codeCours)){
               // courseList has a list of objects, make the corresponding (index) element as a variable (type Course)
               // that variable will become the course parameter that i'll send to registrationform, and with registrationform
               // send the info to inscription.txt
           }


           // use the info from charger
           // create a main method & use the info given in charger to then use it as the course for inscription
           // in the main method make a constructor for this???

           // you need all the information to make course (session, name & code) to make the call work
           RegistrationForm form = new RegistrationForm(prenom, nom, email, matricule, course);
           os.writeObject(form);

           os.flush();
           os.close();
           scanner.close();
           s.close();

           /* TODO
                verify if the course given is in cours.txt (server-client interaction??)
                make the server handle the request accordingly
                write the file to inscription (in server) and then print the confirmation in client
            */

    }}

