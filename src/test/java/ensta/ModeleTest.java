package ensta;

import com.ensta.librarymanager.models.*;
import com.ensta.librarymanager.models.Membre;

import java.time.LocalDate;

public class ModeleTest {
    public static void main(String [] args){
        Membre  membre = new Membre(1,"mahdi","cheikhrouhou","91120","cheikh@","289",
                Membre.Abonnement.PREMIUM);
        Livre livre = new Livre(3,"titreeee","issa","idk");
        LocalDate start = LocalDate.of(Integer.parseInt("2021"), Integer.parseInt("3"), Integer.parseInt("9"));
        LocalDate finish = LocalDate.of(Integer.parseInt("2021"), Integer.parseInt("5"), Integer.parseInt("9"));
        Emprunt emprunt = new Emprunt(1, membre, livre, start,finish);
        System.out.println(membre);
        System.out.println(livre);
        System.out.println(emprunt);
    }
}
