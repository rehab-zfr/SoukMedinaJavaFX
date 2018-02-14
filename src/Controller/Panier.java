package Controller;

import Entity.Article;
import Entity.Commande;
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
import java.util.ResourceBundle;

public class Panier implements Initializable {

    @FXML
    private JFXListView<HBox> listView;

    @FXML
    private JFXButton enleverBtn;

    @FXML
    private JFXButton payementBtn;

    @FXML
    private JFXButton viderPanierBtn;

    private Entity.Panier panier;

    public void intiData(Entity.Panier panier) {
        this.panier = panier;
        for (Article article : panier.getArticleList()) {
            listView.getItems().add(initializeHboxListView(article));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    void enleverArticle(ActionEvent event) {
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void payementAction(ActionEvent event) throws IOException {
        Commande commande = new Commande(panier.getAcheteur_id(), "", "", "", panier, new Date(new java.util.Date().getTime()), 0);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/CommandePaiement.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        CommandePaiement cmdPai = loader.getController();
        cmdPai.initData(commande);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }

    @FXML
    void viderPanier(ActionEvent event) {
        listView.getItems().clear();
        panier.getArticleList().clear();   }


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
}
