package Entity;

import java.util.Objects;

public class Article {
    private int id;
    private String categorie;
    private String nom;
    private String description;
    private float prix;

    public Article() {
    }

    public Article(int id, String categorie, String nom, String description, float prix) {
        this.id = id;
        this.categorie = categorie;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    public Article(String categorie, String nom, String description, float prix) {
        this.categorie = categorie;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return getId() == article.getId() &&
                Float.compare(article.getPrix(), getPrix()) == 0 &&
                Objects.equals(getCategorie(), article.getCategorie()) &&
                Objects.equals(getNom(), article.getNom()) &&
                Objects.equals(getDescription(), article.getDescription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getCategorie(), getNom(), getDescription(), getPrix());
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", categorie='" + categorie + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
