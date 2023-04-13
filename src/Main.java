import models.Course;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;


public class Main {

    public final static int PORT = 1337;
    private static ArrayList<Course> listeCours;

    public static void optionCharger(Scanner scanner, Socket s) throws Exception {
        System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste des cours: \n" +
                "1. Automne \n2. Hiver \n3. Ete ");
        String session = scanner.nextLine();    // l'utilisateur choisit encore une fois la session
        System.out.println("> Choix: " + session);
        Charger charger = new Charger(s, session);
        listeCours = charger.loadCourses(); // redefine listeCours every time
    }
    public static void main (String args[]) throws Exception {
    try{
        Socket s = new Socket("localhost", PORT);

        // envoyer "CHARGER" au client en premier pcq il faut d'abord demander au client de regarder les cours par default
        // CMD = CHARGER ; ARG = MOT DE LA SESSION
        Scanner scanner = new Scanner(System.in);
//        System.out.println(" *** Bienvenue au portail d'inscription de cours de l'UDEM *** \n" +
//                "Veuillez choisir la session pour laquelle vous voulez consulter la liste des cours: \n" +
//                "1. Automne \n2. Hiver \n3. Ete ");
//        String inputSession = scanner.nextLine();
//        System.out.println("> Choix: " + inputSession); // inputsession can be an Int or a name for the semester but theyre both strings anyways
//
//        Charger charger = new Charger(s, inputSession);
//        listeCours = charger.loadCourses(); // does the method and returns a value that comes back to main
        optionCharger(scanner, s);

        while(true) {
            System.out.println("""
                    > Choix:
                    1. Consulter les cours offerts pour une autre session
                    2. Inscription à un cours""");
            String option = scanner.nextLine();
            System.out.println("> Choix: " + option);

            switch(option){
                case "2":
                    Inscription inscription = new Inscription(s, listeCours); // so that inscription also has the course list information
                    inscription.register(s);
                    break;
                case "1":
//                    System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste des cours: \n" +
//                            "1. Automne \n2. Hiver \n3. Ete ");
//                    String session = scanner.nextLine();    // l'utilisateur choisit encore une fois la session
//                    System.out.println("> Choix: " + session);
//                    charger = new Charger(s, session);
//                    listeCours = charger.loadCourses();
                    optionCharger(scanner, s);
                default:
                    System.out.println("Option invalide, svp réessayer."); // maybe add a real exception for this one so the program stops but doesnt crash
                    break;
            }}
        // message d'echec en cas d'exception but do i keep trying the program or do i break
        } catch(Exception e){
        e.printStackTrace();
    }

}}
    // TODO - EXCEPTIONS!!!! bc sinon ca va planter, also Javadoc & jar file

