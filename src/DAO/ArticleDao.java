package DAO;

import Entity.Article;
import Entity.User;
import tools.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleDao {

    private Connection connection;

    public ArticleDao() {
        connection = DataSource.getInstance().getConnection();
    }

    public void add(Article t) {
        try {
            String req = "INSERT INTO `article`(`categorie`, `nom`, `description`, `prix`) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, t.getCategorie());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getDescription());
            ps.setFloat(4, t.getPrix());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Article findById(int id) {
        {
            Article article = new Article();

            String requete = "select * from article where id=?";
            try {
                PreparedStatement ps = connection.prepareStatement(requete);
                ps.setInt(1, id);

                ResultSet resultat = ps.executeQuery();
                while (resultat.next()) {
                    article.setId(resultat.getInt("id"));
                    article.setCategorie(resultat.getString("categorie"));
                    article.setNom(resultat.getString("nom"));
                    article.setDescription(resultat.getString("description"));
                    article.setPrix(resultat.getFloat("prix"));
                }
                return article;

            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    }

    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();
        String requete = "select * from article";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                Article article = new Article();
                article.setId(resultat.getInt(1));
                article.setCategorie(resultat.getString(2));
                article.setNom(resultat.getString(3));
                article.setDescription(resultat.getString(4));
                article.setPrix(resultat.getFloat(5));
                articleList.add(article);
            }
            return articleList;
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
