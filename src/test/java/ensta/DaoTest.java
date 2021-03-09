package ensta;

import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

import java.time.LocalDate;
import java.util.ArrayList;

public class DaoTest {
    public static void main(String [] args) throws DaoException {
        MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
        EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
        LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
        ArrayList<Membre> listOfMembers = (ArrayList<Membre>) membreDaoImpl.getList();
        System.out.println("La liste de tous les membres  : ");
        for(Membre membre : listOfMembers){
            System.out.println(membre);
        }
        System.out.println("La liste de tous les livres : ");
        ArrayList<Livre> listOfBooks = (ArrayList<Livre>) livreDaoImpl.getList();
        for(Livre livre : listOfBooks){
            System.out.println(livre);
        }
        System.out.println("La liste de tous les emprunts : ");
        ArrayList<Emprunt> listOfEmprunts = (ArrayList<Emprunt>) empruntDaoImpl.getList();
        for(Emprunt emprunt : listOfEmprunts){
            System.out.println(emprunt);
        }
        Membre  membre = new Membre(1,
                "mahdi",
                "cheikhrouhou",
                "91120",
                "cheikh@",
                "289",
                Membre.Abonnement.PREMIUM);
        Livre livre = new Livre(3,
                "titreeee",
                "issa",
                "idk");
        LocalDate start = LocalDate.of(Integer.parseInt("2021"), Integer.parseInt("3"), Integer.parseInt("9"));
        LocalDate finish = LocalDate.of(Integer.parseInt("2021"), Integer.parseInt("5"), Integer.parseInt("9"));
        Emprunt emprunt = new Emprunt(1,
                membre,
                livre,
                start,
                finish);

        int nvMembreId = membreDaoImpl.create(membre.getNom(),
                membre.getPrenom(),
                membre.getAdresse(),
                membre.getEmail(),
                membre.getTelephone());
        int nvLivreId = livreDaoImpl.create(livre.getTitre(),
                livre.getAuteur(),
                livre.getIsbn());
        empruntDaoImpl.create(nvMembreId, nvLivreId,emprunt.getDateEmprunt());
        System.out.println("affichage aprés l'insertion : ");
        listOfMembers = (ArrayList<Membre>) membreDaoImpl.getList();
        System.out.println("La liste de tous les membres  : ");
        for(Membre mem : listOfMembers){
            System.out.println(mem);
        }
        System.out.println("La liste de tous les livres : ");
        listOfBooks = (ArrayList<Livre>) livreDaoImpl.getList();
        for(Livre liv : listOfBooks){
            System.out.println(liv);
        }
        System.out.println("La liste de tous les emprunts : ");
        listOfEmprunts = (ArrayList<Emprunt>) empruntDaoImpl.getList();
        for(Emprunt emp : listOfEmprunts){
            System.out.println(emp);
        }
    }
}
