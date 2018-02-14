package Entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Panier {

    private int id;
    private User acheteur_id;
    private Date date_modif;
    private List<Article> articleList;

    public Panier(User acheteur_id, Date date_modif, List<Article> articleList) {
        this.acheteur_id = acheteur_id;
        this.date_modif = date_modif;
        this.articleList = articleList;
    }

    public Panier(int id, User acheteur_id, Date date_modif, List<Article> articleList) {
        this.id = id;
        this.acheteur_id = acheteur_id;
        this.date_modif = date_modif;
        this.articleList = articleList;
    }

    public Panier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAcheteur_id() {
        return acheteur_id;
    }

    public void setAcheteur_id(User acheteur_id) {
        this.acheteur_id = acheteur_id;
    }

    public Date getDate_modif() {
        return date_modif;
    }

    public void setDate_modif(Date date_modif) {
        this.date_modif = date_modif;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Panier)) return false;
        Panier panier = (Panier) o;
        return getId() == panier.getId() &&
                Objects.equals(getAcheteur_id(), panier.getAcheteur_id()) &&
                Objects.equals(getDate_modif(), panier.getDate_modif());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getAcheteur_id(), getDate_modif());
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", acheteur_id=" + acheteur_id +
                ", date_modif=" + date_modif +
                ", articleList=" + articleList +
                '}';
    }
}
