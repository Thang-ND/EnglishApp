package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin {
    @FXML
    private ImageView imageview1;
    @FXML
    private Text target;
    @FXML
    private StackPane stackPane;
    @FXML
    private Text textChallenge;
    private boolean verifyLogin = true;
    public void Summit(ActionEvent event) throws IOException {
        changeTop();
    }
    public void changeTop(){
        ObservableList<Node> list = this.stackPane.getChildren();
        if(list.size() >1){
            Node topNode = list.get(list.size()-1);
            Node newNode = list.get(list.size()-2);
            topNode.setVisible(false);
            topNode.toBack();
            newNode.setVisible(true);
        }
    }
    // vào màn hình chính
    public void enter(ActionEvent event) throws IOException {
        setSceneHome(event);
    }
    public void click(ActionEvent event) {
        try{
            if(verifyLogin ==false){
                Error();
            }
            if(verifyLogin == true){
                setSceneHome(event);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setSceneHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,1083,690);
        window.getIcons().add(new Image(getClass().getResourceAsStream("book.png")));
        window.setTitle("Hust English App");
        window.setScene(scene);
        window.show();
    }
    //error login
    public void Error(){
        this.target.setFill(Color.RED);
        this.target.setText("Sign in button pressed");
    }
    public void setSceneChallenge(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("challenge.fxml"));
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,1083,690);
        window.getIcons().add(new Image(getClass().getResourceAsStream("book.png")));
        window.setTitle("Hust English App");
        window.setScene(scene);
        window.show();
    }

    public void setSceneHistory(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("History.fxml"));
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1083, 690);
        window.getIcons().add(new Image(getClass().getResourceAsStream("book.png")));
        window.setTitle("Hust English App");
        window.setScene(scene);
        window.show();
    }
    public void undo(ActionEvent event) throws IOException {
        setSceneHome(event);
    }

    public void setSceneLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Hust English App");
        Scene scene = new Scene(root, 400, 600);
        scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

        Image image = new Image(getClass().getResourceAsStream("book.png"));
        primaryStage.getIcons().add(image);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void logout(ActionEvent event) throws IOException {
        setSceneLogin(event);
    }

    public void setTextChallenge1(ActionEvent event){
        this.textChallenge.setText("Level 1");
    }
    public void setTextChallenge2(ActionEvent event){
        this.textChallenge.setText("Level 2");
    }
    public void setTextChallenge3(ActionEvent event){
        this.textChallenge.setText("Level 3");
    }
}
