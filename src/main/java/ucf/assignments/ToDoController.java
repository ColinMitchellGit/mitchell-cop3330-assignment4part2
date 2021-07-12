/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Colin Mitchell
 */

package ucf.assignments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class ToDoController {

    //Store primary stage
    private Stage stage;

    ObservableList<ToDoList> mainLists = FXCollections.observableArrayList();

    //Main to-do list TableView to display all lists
    @FXML
    public TableView<ToDoList> tableView;
    @FXML
    public TableColumn<ToDoList, String> titleColumn;

    @FXML
    public void initialize() {
        //Setting main to-do list table
        if(titleColumn != null && tableView != null) {
            titleColumn.setCellValueFactory(new PropertyValueFactory<ToDoList, String>("title"));
            tableView.setItems(mainLists);
        }
    }

    @FXML
    public void openNewList(ActionEvent actionEvent) {
        switchSceneNew(actionEvent);
    }

    @FXML
    public void exportAllLists(ActionEvent actionEvent) {
        try {
            //Creating json file and writing to it
            Writer writer = new FileWriter("lists.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(mainLists, writer);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create lists.json
        //For each to-do list in the ObservableList:
            //Get title and items from list
            //Use json library to convert to json object
            //Write object to list.json
    }

    @FXML
    public void loadAllLists(ActionEvent actionEvent) {
        try {
            //Create file reader
            Reader reader = Files.newBufferedReader(Paths.get("lists.json"));

            //Convert JSON to ArrayList of ToDoLists
            ArrayList<ToDoList> lists = new Gson().fromJson(reader, new TypeToken<ArrayList<ToDoList>>() {}.getType());

            //Add all lists from the imported JSON to the main list of to-do lists
            mainLists.addAll(lists);

            //Close file reader
            reader.close();
        } catch (Exception e) {
        }
    }

    @FXML
    public void viewSelectedList(ActionEvent actionEvent) {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            //Get selected to-do list's index from tableView
            int index = tableView.getSelectionModel().getSelectedIndex();

            //Set scene to the view to-do list window and pass the required list info
            switchSceneView(index, actionEvent);
        }
    }

    @FXML
    public void removeSelectedList(ActionEvent actionEvent) {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            //Get the selected object from the tableview
            ToDoList tobeRemoved = tableView.getSelectionModel().getSelectedItem();

            //Remove the to-do list from the main ObservableList
            mainLists.remove(tobeRemoved);
        }
    }

    public void switchSceneNew(ActionEvent event) {
        try {
            //Load provided FXML scene file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewToDoList.fxml"));
            Parent root = loader.load();

            NewToDoController controller = loader.getController();
            controller.passMainList(mainLists);

            Scene switcher = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            //Switch scene to provided fxml scene file
            stage.setScene(switcher);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchSceneView(int index, ActionEvent event) {
        try {
            //Load provided FXML scene file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewToDoList.fxml"));
            Parent root = loader.load();

            ViewToDoController controller = loader.getController();

            controller.passMainList(mainLists);
            controller.setSelectedListIndex(index);
            controller.display();

            Scene switcher = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            //Switch scene to provided fxml scene file
            stage.setScene(switcher);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        //Set primary stage for switching scenes
        stage = primaryStage;
    }

    //Pass main list of to-do items through each scene
    public void passMainList(ObservableList<ToDoList> list) {
        for (ToDoList toDoList : list) {
            mainLists.add(toDoList);
        }
    }
}
