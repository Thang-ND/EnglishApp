package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;


public class HistoryController implements Initializable {
    @FXML
    StackPane stackPane;

    @FXML
    LineChart<String, Number> lineChart;

    @FXML
    private TableView<History> table;

    @FXML
    private TableColumn<History, String> userNameColumn;

    @FXML
    private TableColumn<History, Integer> scoreColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        History test = new History();
        test.addHistory(100);
        test.addHistory(99);
        test.addHistory(0);
        test.addHistory(900);
        test.addHistory(10);
        XYChart.Series<String, Number> series = getSeries(test);
        lineChart.getData().add(series);
    }

    public XYChart.Series<String, Number> getSeries(History a){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(int i = 0; i<a.getNow(); i++){
            series.getData().add(new XYChart.Data<>("Vong " + (i+1), a.getdiemthi(i)));
        }
        series.setName("Diem tung vong");
        return series;
    }

    public void logout(ActionEvent event) throws IOException {
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

    public void changToTable(){
        ObservableList<Node> list = this.stackPane.getChildren();
        Node bang = list.get(list.size()-1);
        Node bieudo = list.get(list.size()-2);
        bieudo.setVisible(false);
        bang.setVisible(true);
    }

    public void changeToChart(){
        ObservableList<Node> list = this.stackPane.getChildren();
        Node bang = list.get(list.size()-1);
        Node bieudo = list.get(list.size()-2);
        bang.setVisible(false);
        bieudo.setVisible(true);
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
}
