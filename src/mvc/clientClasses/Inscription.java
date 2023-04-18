package mvc.clientClasses;

import mvc.models.Course;
import mvc.models.RegistrationForm;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe inscription permet au client de se connecter au serveur et lui envoyer l'information nécessaire pour
 * s'inscrire à un cours.
 * Elle contient l'information du socket (le port), le String pour la requête au serveur, la liste de cours
 * (créée dans Charger.java) ainsi que le outputStream, qui enverra la requête et l'information de
 * l'utilisateur au serveur.
 */

public class Inscription implements Serializable {

    private Socket s;
    private ObjectOutputStream outputStream;

    private Course course;
    private ArrayList<Course> courseList;
    private ArrayList<String> courseCodes;
    private String commande;

    /**
     * Crée une instance d'Inscription selon le port et la liste des cours, fournis dans Main.java.
     * Connecte le client au serveur via le socket et instancie une liste contenant uniquement les codes des cours,
     * qui nous permettra de vérifier l'entrée de l'utilisateur plus tard.
     * Envoie également la requête "INSCRIRE" au serveur, pour que ce dernier entame le traitement.
     *
     * @param port
     * @param courseList
     * @throws IOException
     */
    public Inscription(int port, ArrayList<Course> courseList ) throws IOException {
        try {
            this.s = new Socket("localhost", port);
        } catch (IOException e) {
            throw new IOException("Impossible de se connecter au serveur sur le port " + port);
        }

        this.courseCodes = new ArrayList<String>();
        this.courseList = courseList;
        for (Course c : courseList){
            courseCodes.add(c.getCode());
        }

        this.commande = "INSCRIRE 0";
        try {
            this.outputStream = new ObjectOutputStream(s.getOutputStream());
            outputStream.writeObject(commande);
            outputStream.flush();
        } catch (IOException e) {
            throw new IOException("Erreur lors de l'écriture sur le flux de sortie.");
        }
    }

    /**
     * Lit les informations du client via un Scanner et crée une instance de RegistrationForm. Ensuite, envoie
     * l'objet au serveur après avoir validé les entrées de l'utilisateur.
     * Ne retourne aucune valeur.
     *
     * @throws Exception
     */
    public void register() throws Exception {

        // Le client donne les informations pour le cours désiré
        try{
           Scanner scanner = new Scanner(System.in);

           System.out.println("Veuillez saisir votre prénom: ");
           String prenom = scanner.nextLine();

           System.out.println("Veuillez saisir votre nom: ");
           String nom = scanner.nextLine();

           System.out.println("Veuillez saisir votre email: ");
           String email = scanner.nextLine();
            try {
                String regexPattern = "[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?";
                if (!email.matches(regexPattern)) {
                    throw new Exception("Format de l'email invalide.");
                }
            } catch(Exception e){
                System.out.println("Error: " + e.getMessage());
                throw new Exception("Format de l'email invalide.");
            }

        System.out.println("Veuillez saisir votre matricule: ");
           String matricule = scanner.nextLine();
        try {
            String regexPattern = "^[0-9]{8}$";
            if (!matricule.matches(regexPattern)) {
                throw new Exception("Format du matricule invalide.");
            }
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            throw new Exception("Format du matricule invalide.");
        }

           System.out.println("Veuillez saisir le code du cours: ");
           String codeCours = scanner.nextLine();

           // verification du choix de cours
           if (courseCodes.contains(codeCours)) {
               int index = courseCodes.indexOf(codeCours);
               this.course = courseList.get(index);
               System.out.println("Félicitations! Inscription réussie de " + prenom + " au cours " + codeCours); // si le cours est disponible, afficher directement le message de confirmation
           } else {
               throw new Exception("Le cours fourni n'est pas disponible pour la session choisie. ");
           }

           RegistrationForm form = new RegistrationForm(prenom, nom, email, matricule, course);

           outputStream.writeObject(form);
           outputStream.flush();

    } catch(Exception e){
            e.printStackTrace();
        }
    }
}

