package Services;

import Entities.Reclamation;
import Entities.Utilisateur;
import Tools.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ReclamationService implements IService<Reclamation> {
    Connection cnx = MaConnexion.getInstance().getCnx();
    PreparedStatement store;

    @Override
    public void ajouter(Reclamation reclamation) throws SQLException {
        Connection cnx = MaConnexion.getInstance().getCnx();
        String query = "INSERT INTO reclamation (id_reclamation,nom_utilisateur,prenom_utilisateur," +
                "email_utilisateur,description_reclamation,etat_reclamation) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, reclamation.getId_reclamation());
            ste.setString(2, reclamation.getNom_utilisateur());
            ste.setString(3, reclamation.getPrenom_utilisateur());
            ste.setString(4, reclamation.getEmail_utilisateur());
            ste.setString(5, reclamation.getDescription_reclamation());
            ste.setString(6, "Pending");
            ste.executeUpdate();
            System.out.println("Reclamation Added Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addRec(Reclamation reclamation, int id) {
        String query = "INSERT INTO reclamation (id_reclamation,nom_utilisateur,prenom_utilisateur," +
                "email_utilisateur,description_reclamation,etat_reclamation,id_utilisateur) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, reclamation.getId_reclamation());
            ste.setString(2, reclamation.getNom_utilisateur());
            ste.setString(3, reclamation.getPrenom_utilisateur());
            ste.setString(4, reclamation.getEmail_utilisateur());
            ste.setString(5, reclamation.getDescription_reclamation());
            ste.setString(6, "Pending");
            ste.setInt(7, id);
            ste.executeUpdate();
            System.out.println("Reclamation Added Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Reclamation> afficher() {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
        String query = "SELECT * FROM reclamation where etat_reclamation='Pending'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.executeQuery();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {

                Reclamation reclamation = new Reclamation();
                reclamation.setId_reclamation(rs.getInt("id_reclamation"));
                reclamation.setDescription_reclamation(rs.getString("description_reclamation"));
                reclamation.setPrenom_utilisateur(rs.getString("prenom_utilisateur"));
                reclamation.setNom_utilisateur(rs.getString("nom_utilisateur"));
                reclamation.setEmail_utilisateur(rs.getString("email_utilisateur"));
                reclamation.setEtat_reclamation(rs.getString("etat_reclamation"));

                reclamations.add(reclamation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reclamations;


    }

    @Override
    public void modifier(Reclamation reclamation) {

    }

    @Override
    public void supprimer(Reclamation reclamation) {

    }

    public boolean CloseRec(String email) {
        String update = "UPDATE RECLAMATION set etat_reclamation='Replied' where email_utilisateur= '" + email + "'";
        try {
            store = cnx.prepareStatement(update);

            store.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}