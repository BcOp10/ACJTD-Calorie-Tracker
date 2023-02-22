package HealthyAndFit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Initializable is used to initialize a controller after the root elements have been processed
public class CalorieAppScene implements Initializable{

    @FXML
    private Button addFoodBtn;

    @FXML
    private TableColumn<FoodList, Integer> calCol;

    @FXML
    private Text calText;

    @FXML
    private TableColumn<FoodList, String> foodCol;

    @FXML
    private TableView<FoodList> foodTable;

    @FXML
    private Text foodText;

    @FXML
    private TextField inputCal;

    @FXML
    private TextField inputFood;

    @FXML
    private TextField inputMeal;

    @FXML
    private TableColumn<FoodList, String> mealCol;

    @FXML
    private Text mealText;

    @FXML
    private Button removeBtn;

    @FXML 
    private Text calCountTxt;

    @FXML
    private Text titleCal;

    @FXML
    private Button newWinBtn;


    /*The ResourceBundle allows the application to load distinct data files.
        URL is used to also access other data.
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        foodCol.setCellValueFactory(new PropertyValueFactory<FoodList, String>("foodName"));
        calCol.setCellValueFactory(new PropertyValueFactory<FoodList, Integer>("calNumber"));
        mealCol.setCellValueFactory(new PropertyValueFactory<FoodList, String>("mealTime"));
    }

    /*Adds food name, number of calories, and meal time to a table. 
        It will also clear the TextFields to get ready for other inputs help from.
        Uses the calCounter() method to also update the number of calories consumed.
    */
    @FXML
    void addFoodBtn(ActionEvent event) {
        FoodList foodItems = new FoodList(inputFood.getText(), Integer.parseInt(inputCal.getText()), inputMeal.getText());
        ObservableList<FoodList> foodObservList = foodTable.getItems();
        foodObservList.add(foodItems);
        foodTable.setItems(foodObservList);
        calCounter();
        inputFood.clear();
        inputCal.clear();
        inputMeal.clear();
    }


    /*Removes data from the foodTable. It will also substract from the calories consumed.
     This will get the selction from the table and will remove it.
     The button will also substract from the calorie count.
    */
    @FXML
    void removeBtn(ActionEvent event) {
        int foodTableID = foodTable.getSelectionModel().getSelectedIndex();
        foodTable.getItems().remove(foodTableID);
    }

    /*This method uses IntegerBinding to bind the calorie column to a list.
        The binding is associating an indentifier (in this case foodTable),
        to the variables in this method.
     */
    public void calCounter(){
            IntegerBinding totalCount = Bindings.createIntegerBinding(() -> {
                int counter = 0 ;
                for (FoodList value : foodTable.getItems()) {
                    counter = counter + value.getCalNumber();
                }
                return counter;
            },foodTable.getItems());

            calCountTxt.textProperty().bind(totalCount.asString());
    }


    //Source in reference document
    @FXML
    void handleBtnNewWindow(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("NewTableViewScene.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add your Entries");
        primaryStage.setScene(scene);

        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(newWinBtn.getScene().getWindow());
        primaryStage.show();
    }







}