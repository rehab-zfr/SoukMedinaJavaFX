package DAO;

import Entity.Commande;
import tools.DataSource;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandeDao {

    private Connection connection;

    public CommandeDao() {
        connection = DataSource.getInstance().getConnection();
    }

    public void add(Commande t) {
        try {
            String req = "INSERT INTO `commande`(`acheteur_id`, `adress_liv`, `meth_paiment`, `meth_livraison`, `panier_id`, `date`, `etat`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, t.getAcheteur().getId());
            ps.setString(2, t.getAdress_liv());
            ps.setString(3, t.getMethode_paiment());
            ps.setString(4, t.getMethode_livraison());
            ps.setInt(5, t.getPanier().getId());
            ps.setDate(6, t.getDate());
            ps.setInt(7, t.getEtat());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Commande findById(int id) {
        {
            Commande commande = new Commande();

            String requete = "select * from commande where id=?";
            try {
                PreparedStatement ps = connection.prepareStatement(requete);
                ps.setInt(1, id);

                ResultSet resultat = ps.executeQuery();
                while (resultat.next()) {
                    commande.setId(resultat.getInt("id"));
                    commande.setAcheteur(new UserDao().findById(resultat.getInt("acheteur_id")));
                    commande.setAdress_liv(resultat.getString("adress_liv"));
                    commande.setMethode_paiment(resultat.getString("meth_paiment"));
                    commande.setMethode_livraison(resultat.getString("meth_livraison"));
                    commande.setPanier(new PanierDao().findById(resultat.getInt("panier_id")));
                    commande.setDate(resultat.getDate("date"));
                    commande.setEtat(resultat.getInt("etat"));

                }
                return commande;

            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    }

    public void update(Commande t) {
        try {
            String req = "UPDATE `commande` SET `adress_liv`=?,`meth_paiment`=?,`meth_livraison`=?,`date`=?,`etat`=? WHERE `id`=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, t.getAdress_liv());
            ps.setString(2, t.getMethode_paiment());
            ps.setString(3, t.getMethode_livraison());
            ps.setDate(4, t.getDate());
            ps.setInt(5, t.getEtat());
            ps.setInt(6, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int findTheId() {

        int id = 0;

        String requete = "SELECT `id` FROM `commande` WHERE `id` = (SELECT MAX(id) FROM `commande`)";
        try {
            Statement ps = connection.prepareStatement(requete);

            ResultSet resultat = ps.executeQuery(requete);
            while (resultat.next()) {
                id = resultat.getInt("id");

            }
            return id;

        } catch (SQLException ex) {
            Logger.getLogger(CommandeDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }


}
