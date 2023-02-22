package HealthyAndFit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



public class NewWindowScene implements Initializable{
    
    @FXML
    private TableView<PresetList> entryTable;

    @FXML
    private TableColumn<PresetList, String> entryFood = new TableColumn<>("newFoodName");

    @FXML
    private TableColumn<PresetList, Integer> entryCal = new TableColumn<>("newCal");

    @FXML
    private Button addEntriesBtn;

    @FXML
    private Button userInput;

    @FXML
    private TextField userFoodInput;

    @FXML
    private TextField userCalInput;

    @FXML
    private Button rmvBtn;


    //sources for this can be found in the references document
    //This gets the data from two seperate text files to display in a Table Column
   @Override
   public void initialize(URL url, ResourceBundle resourceBundle){
    entryFood.setCellValueFactory(new PropertyValueFactory<PresetList, String>("newFoodName"));
    entryCal.setCellValueFactory(new PropertyValueFactory<PresetList, Integer>("newCal"));

    List<String> foodList = getfileData("NamesOfFood.txt");
    List<String> calorieList = getfileData("NumberOfCalories.txt");

    for (int i = 0; i < foodList.size() && i < calorieList.size(); i++) {
        entryTable.getItems().add(new PresetList(foodList.get(i), Integer.parseInt(calorieList.get(i))));
        }
   }

   //This method reads data from a text file
    private List<String> getfileData(String filename) {
    List<String> dataList = new ArrayList<>();
    try {
        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        String line;
        while ((line = br.readLine()) != null) {
            dataList.add(line);
        }
        br.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return dataList;
}



//Source can be found in references document
@FXML
void addEntry(ActionEvent event) {
    String getFoodInput = userFoodInput.getText();
    int getCalInput = Integer.parseInt(userCalInput.getText());

    // Has to append to true or else the pre-existing text files gets overwritten
    try (FileWriter enter1 = new FileWriter("NamesOfFood.txt", true);
         FileWriter enter2 = new FileWriter("NumberOfCalories.txt", true);
         BufferedWriter buff1 = new BufferedWriter(enter1);
         BufferedWriter buff2 = new BufferedWriter(enter2);
         PrintWriter output1 = new PrintWriter(buff1);
         PrintWriter output2 = new PrintWriter(buff2)) {
        output1.println(getFoodInput);
        output2.println(getCalInput);
    } catch (IOException e) {
        e.printStackTrace();
    }

    userFoodInput.clear();
    userCalInput.clear();

    // Updates the table to reflect new entries
    entryTable.getItems().add(new PresetList(getFoodInput, getCalInput));
}



@FXML
void removeEntry(ActionEvent event) {
    int selectedRowIndex = entryTable.getSelectionModel().getSelectedIndex();
    entryTable.getItems().remove(selectedRowIndex);

    // Update the text files
    try (FileWriter enter1 = new FileWriter("NamesOfFood.txt");
         FileWriter eneter2 = new FileWriter("NumberOfCalories.txt");
         BufferedWriter br1 = new BufferedWriter(enter1);
         BufferedWriter br2 = new BufferedWriter(eneter2);
         PrintWriter output1 = new PrintWriter(br1);
         PrintWriter output2 = new PrintWriter(br2)) {
        for (PresetList entry : entryTable.getItems()) {
            output1.println(entry.getNewFoodName());
            output2.println(entry.getNewCal());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}















}