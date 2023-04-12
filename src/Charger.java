import java.io.*;
import java.net.*;
import java.util.*;

import models.Course;

/**
 * La classe charger contient une méthode qui permet au client de visualiser la liste de cours offerts
 * pour la session qu'il a indiquée. Selon son choix, la requête est envoyée au serveur (dans main),
 * qui renvoie la liste de cours correspondant à la session. Cette dernière
 */
public class Charger {
    private  ObjectOutputStream outputStream;
    private  ObjectInputStream inputStream;
    private  String commande;

    //    public Charger(ArrayList<Course> courseList) throws FileNotFoundException, IOException {
//        this.listeCours = courseList;
//    }
        // create a constructor that uses the user's session as a parameter, as well as the socket's variable
    public Charger(Socket s, String session) throws IOException, ClassNotFoundException {
        this.outputStream = new ObjectOutputStream(s.getOutputStream());
        this.inputStream = new ObjectInputStream(s.getInputStream());
        this.commande = "CHARGER " + session;
    }

    // add a try/catch block here somewhere but for now you just wanna see that it runs
    public ArrayList<Course> loadCourses() throws Exception {
        // send the info to the server
        outputStream.writeObject(commande);
        outputStream.flush();
        // recuperer l'info du serveur (liste de cours filtrée)
        ArrayList<Course> courseList = (ArrayList<Course>) inputStream.readObject();

        try {
            int i = 1;
            // iterate through the course list
            for (Course c : courseList) {
                System.out.println(i + ". " + c.getCode() + c.getName());
                i++;
            }

        } catch (Exception e) {

        }
        return courseList;
    }
}







