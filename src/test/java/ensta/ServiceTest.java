package ensta;

import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceTest {
    public static void main(String[] args) throws ServiceException {
        MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
        EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
        LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
        List<Membre> listOfMembers = membreServiceImpl.getList();
        System.out.println("La liste de tous les membres  : ");
        for (Membre membre : listOfMembers) {
            System.out.println(membre);
        }
        System.out.println("La liste de tous les livres : ");
        ArrayList<Livre> listOfBooks = (ArrayList<Livre>) livreServiceImpl.getList();
        for (Livre livre : listOfBooks) {
            System.out.println(livre);
        }
        System.out.println("La liste de tous les emprunts : ");
        ArrayList<Emprunt> listOfEmprunts = (ArrayList<Emprunt>) empruntServiceImpl.getList();
        for (Emprunt emprunt : listOfEmprunts) {
            System.out.println(emprunt);
        }

        Membre membre = new Membre(1,
                "Mahdii",
                "cheik",
                "91120",
                "cheikh@",
                "289",
                Membre.Abonnement.PREMIUM);
        Livre livre = new Livre(3,
                "titreee",
                "issa",
                "idk");
        LocalDate start = LocalDate.of(Integer.parseInt("2021"), Integer.parseInt("3"), Integer.parseInt("9"));
        LocalDate finish = LocalDate.of(Integer.parseInt("2021"), Integer.parseInt("5"), Integer.parseInt("9"));
        Emprunt emprunt = new Emprunt(1,
                membre,
                livre,
                start,
                finish);

        int nvMembreId = membreServiceImpl.create(membre.getNom(),
                membre.getPrenom(),
                membre.getAdresse(),
                membre.getEmail(),
                membre.getTelephone());
        int nvLivreId = livreServiceImpl.create(livre.getTitre(),
                livre.getAuteur(),
                livre.getIsbn());
        empruntServiceImpl.create(nvMembreId,
                nvLivreId,
                emprunt.getDateEmprunt());
        //membreDaoImpl.delete(14);
        System.out.println("affichage aprés l'insertion : ");
        listOfMembers = (ArrayList<Membre>) membreServiceImpl.getList();
        System.out.println("La liste de tous les membres  : ");
        for (
                Membre mem : listOfMembers) {
            System.out.println(mem);
        }
        System.out.println("La liste de tous les livres : ");
        listOfBooks = (ArrayList<Livre>) livreServiceImpl.getList();
        for (
                Livre liv : listOfBooks) {
            System.out.println(liv);
        }
        System.out.println("La liste de tous les emprunts : ");
        listOfEmprunts = (ArrayList<Emprunt>) empruntServiceImpl.getList();
        for (
                Emprunt emp : listOfEmprunts) {
            System.out.println(emp);
        }
    }
}
