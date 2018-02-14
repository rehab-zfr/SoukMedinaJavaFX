package Entity;

import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String adresse;
    private String photoProf;
    private String typeUtilisateur;
    private String ville;
    private String nom;
    private String prenom;

    public User(int id, String username, String email, String password, String role, String adresse, String photoProf, String typeUtilisateur, String ville, String nom, String prenom) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.adresse = adresse;
        this.photoProf = photoProf;
        this.typeUtilisateur = typeUtilisateur;
        this.ville = ville;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User(String username, String email, String password, String role, String adresse, String photoProf, String typeUtilisateur, String ville, String nom, String prenom) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.adresse = adresse;
        this.photoProf = photoProf;
        this.typeUtilisateur = typeUtilisateur;
        this.ville = ville;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhotoProf() {
        return photoProf;
    }

    public void setPhotoProf(String photoProf) {
        this.photoProf = photoProf;
    }

    public String getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(String typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getRole(), user.getRole()) &&
                Objects.equals(getAdresse(), user.getAdresse()) &&
                Objects.equals(getPhotoProf(), user.getPhotoProf()) &&
                Objects.equals(getTypeUtilisateur(), user.getTypeUtilisateur()) &&
                Objects.equals(getVille(), user.getVille()) &&
                Objects.equals(getNom(), user.getNom()) &&
                Objects.equals(getPrenom(), user.getPrenom());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), getRole(), getAdresse(), getPhotoProf(), getTypeUtilisateur(), getVille(), getNom(), getPrenom());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", adresse='" + adresse + '\'' +
                ", photoProf='" + photoProf + '\'' +
                ", typeUtilisateur='" + typeUtilisateur + '\'' +
                ", ville='" + ville + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
