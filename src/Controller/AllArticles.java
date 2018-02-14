package Controller;

import DAO.ArticleDao;
import DAO.PanierDao;
import DAO.UserDao;
import Entity.Article;
import Entity.Panier;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AllArticles implements Initializable {


    @FXML
    private JFXListView<HBox> listView;
    @FXML
    private JFXButton pannierBtn;
    @FXML
    private Label panierlbl;

    private ArticleDao articleDao = new ArticleDao();
    private Panier panier = new Panier(new UserDao().findById(1), new Date(new java.util.Date().getTime()), new ArrayList<Article>());
    private PanierDao panierDao = new PanierDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Article> articles = articleDao.findAll();

        for (Article article : articles) {
            listView.getItems().add(initializeHboxListView(article));
        }
    }


    private HBox initializeHboxListView(Article article) {
        HBox hBox = new HBox();

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 20, 10, 10));
        Label lblid = new Label(Integer.toString(article.getId()));
        Label lblNom = new Label(article.getNom());
        Label lblCategorie = new Label(article.getCategorie());
        Text lblDescription = new Text(article.getDescription());
        Label lblPrix = new Label(Float.toString(article.getPrix()));
        vBox.setSpacing(4);
        vBox.getChildren().addAll(lblid, lblNom, lblDescription, lblCategorie, lblPrix);
        hBox.getChildren().add(vBox);
        return hBox;
    }


    @FXML
    public void goToPanier(ActionEvent event) throws IOException {
        panierDao.add(panier);
        panier.setId(panierDao.findTheId());
        if (panier.getId() != 0) {
            panierDao.addArticleToPanier(panier);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/Panier.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Controller.Panier panierController = loader.getController();
            panierController.intiData(panier);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    @FXML
    void addToPanier(ActionEvent event) {
        VBox vBox = (VBox) ((HBox) listView.getSelectionModel().getSelectedItem()).getChildren().get(0);
        int id = Integer.parseInt(((Label) vBox.getChildren().get(0)).getText());
        Article article = articleDao.findById(id);
        panier.getArticleList().add(article);
        pannierBtn.setText("Pannier (" + panier.getArticleList().size() + ")");
        panierlbl.setText("Votre Panier ( " + panier.getArticleList().size() + " )");

    }
}
