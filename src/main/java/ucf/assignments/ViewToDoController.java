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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class ViewToDoController {

    //Storing all the lists
    ObservableList<ToDoList> allLists = FXCollections.observableArrayList();

    //Temporary list of to-do items
    ObservableList<ToDoListItem> tempItems = FXCollections.observableArrayList();

    //Temporary list for sorting by complete
    ObservableList<ToDoListItem> sorted = FXCollections.observableArrayList();

    //Store selected to-do list's index in the main ObservableList
    int selectedListIndex;

    @FXML
    private TextField viewTitle;
    @FXML
    private TextField viewdescriptionField;
    @FXML
    private DatePicker viewDueDateField;

    //TableView elements for displaying list items
    @FXML
    private TableView<ToDoListItem> viewItemTableView;
    @FXML
    private TableColumn<ToDoListItem, String> viewDescriptionColumn;
    @FXML
    private TableColumn<ToDoListItem, String> viewDueDateColumn;
    @FXML
    private TableColumn<ToDoListItem, String> viewCompleteColumn;

    @FXML
    private Button sortCompletedButton;

    @FXML
    public void initialize() {
        //Setting view to-do list item table
        if(viewDescriptionColumn != null && viewDueDateColumn != null && viewCompleteColumn != null) {
            viewDescriptionColumn.setCellValueFactory(new PropertyValueFactory<ToDoListItem, String>("description"));
            viewDueDateColumn.setCellValueFactory(new PropertyValueFactory<ToDoListItem, String>("duedate"));
            viewCompleteColumn.setCellValueFactory(new PropertyValueFactory<ToDoListItem, String>("completed"));
            viewItemTableView.setItems(tempItems);
        }

        //Adding a listener to the description textfield to limit the input length to 256 characters
        limitDescription(viewdescriptionField);
    }

    @FXML
    public void addItemToTempListView(ActionEvent actionEvent) {
        //Checks to see if user entered dat for new list
        if(!viewdescriptionField.getText().equals("") && !viewDueDateField.getValue().equals("")) {
            //Get description and due date from textfield
            String description = viewdescriptionField.getText();
            LocalDate duedate = viewDueDateField.getValue();

            //Create new item using description and due dae
            ToDoListItem newItem = new ToDoListItem(description, duedate.toString());

            //Add to the temporary ObservableList
            tempItems.add(newItem);

            //Clear fields for next item input
            viewdescriptionField.clear();
            viewDueDateField.setValue(null);
        }
    }

    @FXML
    public void exportCurrentList(ActionEvent actionEvent) {
        //Create ToDoList object for currently selected list
        ToDoList list = allLists.get(selectedListIndex);

        //Create objectmapper
        ObjectMapper mapper = new ObjectMapper();

        //Write ToDoList to list.json
        try {
            mapper.writeValue(Paths.get("list.json").toFile(), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToMain(ActionEvent event) {
        goMainScreen(event);
    }

    @FXML
    public void markItemAsComplete(ActionEvent actionEvent) {
        if(viewItemTableView.getSelectionModel().getSelectedItem() != null) {
            //Get the index of the selected item
            int index;
            index = viewItemTableView.getSelectionModel().getSelectedIndex();

            //Set the item at that index to completed
            tempItems.get(index).setCompleted();

            //Refresh tableview to show item as complete
            viewItemTableView.refresh();
        }
    }

    @FXML
    public void completedSort(ActionEvent actionEvent) {
        if(sortCompletedButton.getText().equals("Sort by Completed")) {
            sorted.setAll(tempItems);
            //Loop over all items, if item is completed add it to sorted list
            for(ToDoListItem item : viewItemTableView.getItems()) {
                if(item.getCompleted().equals("No")) {
                    sorted.remove(item);
                }
            }
            //Set table to sorted list and refresh the table
            viewItemTableView.setItems(sorted);
            viewItemTableView.refresh();
            sortCompletedButton.setText("Sort by not Completed");
        }
        else if(sortCompletedButton.getText().equals("Sort by not Completed")) {
            sorted.setAll(tempItems);
            //Loop over all items, if item is completed remove it from the sorted list
            for(ToDoListItem item : tempItems) {
                if(item.getCompleted().equals("Yes")) {
                    sorted.remove(item);
                }
            }
            //Set table to sorted list and refresh the table
            viewItemTableView.setItems(sorted);
            viewItemTableView.refresh();
            sortCompletedButton.setText("Reset Sorting");
        }
        else if(sortCompletedButton.getText().equals("Reset Sorting")) {
            //Set the table back to the default of all items
            viewItemTableView.setItems(tempItems);
            viewItemTableView.refresh();
            sortCompletedButton.setText("Sort by Completed");
        }
    }

    @FXML
    public void removeSelectedItemView(ActionEvent actionEvent) {
        if(viewItemTableView.getSelectionModel().getSelectedItem() != null) {
            //Get selected item from TableView on clicked event
            ToDoListItem toBeRemoved = viewItemTableView.getSelectionModel().getSelectedItem();

            //Remove it from the temporary item list
            tempItems.remove(toBeRemoved);

            //If the items are currently being sorted by completed,
            //also remove it from the sorted list
            if(!sortCompletedButton.getText().equals("Sort by Completed")) {
                sorted.remove(toBeRemoved);
            }
        }
    }

    @FXML
    public void saveCurrentList(ActionEvent actionEvent) {
        //Get title from textfield
        String title = viewTitle.getText();

        ArrayList<ToDoListItem> temp = new ArrayList<ToDoListItem>();
        temp.addAll(tempItems);

        //Use setters in ToDoList class to set changes in the main to-do list
        allLists.get(selectedListIndex).setTitle(title);
        allLists.get(selectedListIndex).setItems(temp);

        //Go to the main to-do list screen
        goMainScreen(actionEvent);
    }

    //Pass main list of to-do items through each scene
    public void passMainList(ObservableList<ToDoList> list) {
        allLists.addAll(list);
    }

    //Sets the title textfield and items tableview to the selected list's information
    public void display() {
        viewTitle.setText(allLists.get(selectedListIndex).getTitle());
        tempItems.addAll(allLists.get(selectedListIndex).getItems());
    }

    //Sets the index of the item that was selected on the main to-do list screen
    public void setSelectedListIndex(int index) {
        selectedListIndex = index;
    }

    public void goMainScreen(ActionEvent event) {
        try {
            //Load the main to-do fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ToDo.fxml"));
            Parent root = loader.load();
            Scene toDoScene = new Scene(root);

            //Get the controller to pass the main list of to-do items
            ToDoController controller = loader.getController();
            controller.passMainList(allLists);

            //Switch scene to provided fxml scene file
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(toDoScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
