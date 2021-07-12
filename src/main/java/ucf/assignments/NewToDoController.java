/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Colin Mitchell
 */

package ucf.assignments;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class NewToDoController {
    //Storing all the lists
    ObservableList<ToDoList> allLists = FXCollections.observableArrayList();

    //Declare a temporary ObservableList for new items
    ObservableList<ToDoListItem> tempItems = FXCollections.observableArrayList();

    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private DatePicker dueDateField;

    @FXML
    private TableView<ToDoListItem> newTableView;
    @FXML
    private TableColumn<ToDoListItem, String> newDescriptionColumn;
    @FXML
    private TableColumn<ToDoListItem, String> newDueDateColumn;

    @FXML
    public void initialize() {
        //Setting new to-do list item table
        if(newDescriptionColumn != null && newDueDateColumn != null && newTableView != null) {
            newDescriptionColumn.setCellValueFactory(new PropertyValueFactory<ToDoListItem, String>("description"));
            newDueDateColumn.setCellValueFactory(new PropertyValueFactory<ToDoListItem, String>("duedate"));
            newTableView.setItems(tempItems);
        }

        //Adding a listener to the description textfield to limit the input length to 256 characters
        limitDescription(descriptionField);
    }

    @FXML
    public void addItemToTempListNew(ActionEvent actionEvent) {
        //Checks to see if user entered data for new list
        if(!descriptionField.getText().equals("") && !dueDateField.getValue().equals("")) {
            //Get description and due date from textfield
            String description = descriptionField.getText();
            LocalDate duedate = dueDateField.getValue();

            //Create new item using description and due date
            ToDoListItem newItem = new ToDoListItem(description, duedate.toString());

            //Add to the temporary ObservableList
            tempItems.add(newItem);

            //Clear fields for next item input
            descriptionField.clear();
            dueDateField.setValue(null);
        }
    }

    @FXML
    public void saveNewList(ActionEvent actionEvent) {
        //Get title from title field
        String title = titleField.getText();

        //Create temp ArrayList which converts the tempItems
        ArrayList<ToDoListItem> temp = new ArrayList<ToDoListItem>();
        temp.addAll(tempItems);

        //Create new ToDoList and add it to the main ObservableList
        ToDoList newList = new ToDoList(title, temp);
        allLists.add(newList);

        //Remove all items from temp ObservableList
        tempItems.removeAll();

        //Switch scene back to the main to-do scene
        switchSceneMain(actionEvent);
    }

    @FXML
    public void cancelNewItem(ActionEvent actionEvent) {
        //Just return to main to-do screen
        switchSceneMain(actionEvent);
    }

    @FXML
    public void loadList(ActionEvent actionEvent) {
        try {
            //Create ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            //Read in ToDoList from list.json
            ToDoList list = mapper.readValue(new File("list.json"), ToDoList.class);

            //Add the read in ToDoList to the main list
            allLists.add(list);

            //Switch back to the main screen
            switchSceneMain(actionEvent);
        } catch (Exception e) {
        }
    }

    @FXML
    public void removeSelectedItem(ActionEvent actionEvent) {
        //Get selected item from TableView on clicked event
        if(newTableView.getSelectionModel().getSelectedItem() != null) {
            //Get the index of the selected item
            int index;
            index = newTableView.getSelectionModel().getSelectedIndex();

            //Remove the item at that index of the tempItems list
            tempItems.remove(index);
        }
    }

    //Switches scene to the main to-do scene and passes the main list of to-do items
    public void switchSceneMain(ActionEvent event) {
        try {
            //Load the main to-do fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ToDo.fxml"));
            Parent root = loader.load();
            Scene toDoScene = new Scene(root);

            //Get the controller to pass the main list of to-do items
            ToDoController controller = loader.getController();
            controller.passMainList(allLists);

            //Switch scene to provided fxml scene file
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(toDoScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Pass main list of to-do items through each scene
    public void passMainList(ObservableList<ToDoList> list) {
        for (ToDoList toDoList : list) {
            allLists.add(toDoList);
        }
    }

    //Limit the length of the description textfield to 256 characters
    public static void limitDescription(final TextField text) {
        text.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (text.getText().length() > 256) {
                    String s = text.getText().substring(0, 256);
                    text.setText(s);
                }
            }
        });
    }
}
