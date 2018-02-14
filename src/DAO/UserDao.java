package DAO;

import Entity.User;
import tools.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    private Connection connection;

    public UserDao() {
        connection = DataSource.getInstance().getConnection();
    }

    public void add(User t) {
        try {
            String req = "INSERT INTO " +
                    "`user`(`username`, `email`, `password`, `roles`, `adresse`, `photoProf`, `type_utilisateur`,`ville`, `nom`, `prenom`)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, t.getUsername());
            ps.setString(2, t.getEmail());
            ps.setString(3, t.getPassword());
            ps.setString(4, t.getRole());
            ps.setString(5, t.getAdresse());
            ps.setString(6, t.getPhotoProf());
            ps.setString(7, t.getTypeUtilisateur());
            ps.setString(8, t.getVille());
            ps.setString(9, t.getNom());
            ps.setString(10, t.getPrenom());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User findById(int id) {
        {
            User user = new User();

            String requete = "select * from user where id=?";
            try {
                PreparedStatement ps = connection.prepareStatement(requete);
                ps.setInt(1, id);

                ResultSet resultat = ps.executeQuery();
                while (resultat.next()) {
                    user.setId(resultat.getInt("id"));
                    user.setUsername(resultat.getString("username"));
                    user.setEmail(resultat.getString("email"));
                    user.setPassword(resultat.getString("password"));
                    user.setRole(resultat.getString("roles"));
                    user.setAdresse(resultat.getString("adresse"));
                    user.setRole(resultat.getString("roles"));
                    user.setPhotoProf(resultat.getString("photoProf"));
                    user.setTypeUtilisateur(resultat.getString("type_utilisateur"));
                    user.setVille(resultat.getString("ville"));
                    user.setNom(resultat.getString("nom"));
                    user.setPrenom(resultat.getString("prenom"));
                }
                return user;

            } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'utilisateur " + ex.getMessage());
                return null;
            }
        }

    }

}
