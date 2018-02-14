package DAO;

import Entity.Article;
import Entity.Panier;
import tools.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PanierDao {


    private Connection connection;

    public PanierDao() {
        connection = DataSource.getInstance().getConnection();
    }

    public void add(Panier t) {
        try {
            String req = "INSERT INTO `panier`(`acheteur_id`, `date_modif`) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, t.getAcheteur_id().getId());
            ps.setDate(2, t.getDate_modif());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addArticleToPanier(Panier t) {
        for (Article article : t.getArticleList()) {
            try {
                String req = "INSERT INTO `panier_articles`(`panier_id`, `article_id`) VALUES (?,?)";
                PreparedStatement ps = connection.prepareStatement(req);
                ps.setInt(1, t.getId());
                ps.setInt(2, article.getId());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Panier findById(int id) {
        {
            Panier panier = new Panier();

            String requete = "select * from article where id=?";
            try {
                PreparedStatement ps = connection.prepareStatement(requete);
                ps.setInt(1, id);

                ResultSet resultat = ps.executeQuery();
                while (resultat.next()) {
                    panier.setId(resultat.getInt("id"));
                    panier.setAcheteur_id(new UserDao().findById(resultat.getInt("acheteur_id")));
                    panier.setDate_modif(resultat.getDate("dat_modif"));
                }
                panier.setArticleList(findAllArticlePanier(id));
                return panier;

            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    }

    public int findTheId() {
        {
            int id = 0;

            String requete = "SELECT `id` FROM `panier` WHERE `id` = (SELECT MAX(id) FROM `panier`)";
            try {
                Statement ps = connection.prepareStatement(requete);

                ResultSet resultat = ps.executeQuery(requete);
                while (resultat.next()) {
                    id = resultat.getInt("id");

                }

                return id;

            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }

    }


    public List<Article> findAllArticlePanier(int id) {
        List<Article> articleList = new ArrayList<>();
        String requete = "SELECT `article`.`id` , `categorie` , `article`.`nom` , `article`.`description`, `prix` FROM `article` , `panier_articles` WHERE `article_id`=`article`.`id` AND `panier_id`=" + id;
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
