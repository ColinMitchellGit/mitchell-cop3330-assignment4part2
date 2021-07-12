/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Colin Mitchell
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class ToDoControllerTest {

    @Test
    void addNewList() {
        //Create title
        String title = "Shopping";

        //Create a couple new ToDoListItems
        ToDoListItem item1 = new ToDoListItem("Buy milk", "2021-7-25");
        ToDoListItem item2 = new ToDoListItem("Buy cereal", "2021-7-25");

        //Store new items in ObservableList
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();
        items.add(item1);
        items.add(item2);

        //Create new ToDoList using title and ObservableList
        ToDoList list = new ToDoList(title, items);

        //Store new ToDoList in ObservableList
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        lists.add(list);

        //Create a null ObservableList
        ObservableList<ToDoList> Null =  FXCollections.observableArrayList();

        //assertNotEquals the null ObservableList and the ObservableList with the ToDoList in it
        assertNotEquals(Null, lists);
    }

    @Test
    void removeList() {
        //Create title
        String title = "Shopping";

        //Create a couple new ToDoListItems
        ToDoListItem item1 = new ToDoListItem("Buy milk", "2021-7-25");
        ToDoListItem item2 = new ToDoListItem("Buy cereal", "2021-7-25");

        //Store new items in ObservableList
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();
        items.add(item1);
        items.add(item2);

        //Create new ToDoList using title and ObservableList
        ToDoList list = new ToDoList(title, items);

        //Store new ToDoList in ObservableList
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        lists.add(list);

        //Store the same ToDoList in another ObservableList
        ObservableList<ToDoList> other = FXCollections.observableArrayList();
        other.add(list);

        //Use the ObservableList method .remove() to remove the ToDoList from the first ObservableList
        lists.remove(list);

        //assertNotEquals the ObservableList with the list removed against the ObservableList with the list still in it
        assertNotEquals(lists, other);
    }

    @Test
    void editTitle() {
        //Create title
        String title = "Shopping";

        //Create a couple new ToDoListItems
        ToDoListItem item1 = new ToDoListItem("Buy milk", "2021-7-25");
        ToDoListItem item2 = new ToDoListItem("Buy cereal", "2021-7-25");

        //Store new items in ObservableList
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();
        items.add(item1);
        items.add(item2);

        //Create new ToDoList using title and ObservableList
        ToDoList list = new ToDoList(title, items);

        //Store new ToDoList in ObservableList
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        lists.add(list);

        //Use ToDoList method setTitle() to change the list's title in the ObservableList
        lists.get(0).setTitle("Exercise");

        //assertNotEquals the original title variable with the getTitle() method of the list
        assertNotEquals(title, lists.get(0).getTitle());
    }

    @Test
    void addNewItem() {
        //Create title
        String title = "Shopping";

        //Create a couple new ToDoListItems
        ToDoListItem item1 = new ToDoListItem("Buy milk", "2021-7-25");
        ToDoListItem item2 = new ToDoListItem("Buy cereal", "2021-7-25");

        //Store new items in ObservableList
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();
        items.add(item1);
        items.add(item2);

        //Create new ToDoList using title and ObservableList
        ToDoList list = new ToDoList(title, items);

        //Store new ToDoList in ObservableList
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        lists.add(list);

        //Create a new item
        ToDoListItem item3 = new ToDoListItem("Buy house", "2021-07-25");

        //try catch adding the new item to the list by setting a new list of items
        try{
            items.add(item3);
            lists.get(0).setItems(items);
        }catch(Exception e) {
        }
    }

    @Test
    void removeItem() {
        //Create title
        String title = "Shopping";

        //Create a couple new ToDoListItems
        ToDoListItem item1 = new ToDoListItem("Buy milk", "2021-7-25");
        ToDoListItem item2 = new ToDoListItem("Buy cereal", "2021-7-25");

        //Store new items in ObservableList
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();
        items.add(item1);
        items.add(item2);

        //Create new ToDoList using title and ObservableList
        ToDoList list = new ToDoList(title, items);

        //Store new ToDoList in ObservableList
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        lists.add(list);

        //try catch removing the item and re-setting the items to the list
        try{
            items.remove(item2);
            lists.get(0).setItems(items);
        }catch(Exception e) {
        }
    }

    @Test
    void editDescription() {
        //Create title
        String title = "Shopping";

        //Create a couple new ToDoListItems
        ToDoListItem item1 = new ToDoListItem("Buy milk", "2021-7-25");
        ToDoListItem item2 = new ToDoListItem("Buy cereal", "2021-7-25");

        //Store new items in ObservableList
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();
        items.add(item1);
        items.add(item2);

        //Create new ToDoList using title and ObservableList
        ToDoList list = new ToDoList(title, items);

        //Store new ToDoList in ObservableList
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        lists.add(list);

        //Create new description
        String description = "Buy new house";

        //try catch using the ToDoListItem method setDescription() with the new description on the first item
        try{
            lists.get(0).getItems().get(0).setDescription(description);
        }catch(Exception e) {
        }
    }

    @Test
    void editDueDate() {
        //Create title
        String title = "Shopping";

        //Create a couple new ToDoListItems
        ToDoListItem item1 = new ToDoListItem("Buy milk", "2021-7-25");
        ToDoListItem item2 = new ToDoListItem("Buy cereal", "2021-7-25");

        //Store new items in ObservableList
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();
        items.add(item1);
        items.add(item2);

        //Create new ToDoList using title and ObservableList
        ToDoList list = new ToDoList(title, items);

        //Store new ToDoList in ObservableList
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        lists.add(list);

        //Create new due date
        String duedate = "2021-07-25";

        //try catch use the ToDoListItem method setDuedate() with the new due date on one of the items
        try{
            lists.get(0).getItems().get(0).setDuedate(duedate);
        }catch(Exception e) {
        }
    }

    @Test
    void markItemAsComplete() {
        //Create title
        String title = "Shopping";

        //Create a couple new ToDoListItems
        ToDoListItem item1 = new ToDoListItem("Buy milk", "2021-7-25");
        ToDoListItem item2 = new ToDoListItem("Buy cereal", "2021-7-25");

        //Store new items in ArrayList
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();
        items.add(item1);
        items.add(item2);

        //Create new ToDoList using title and ArrayList
        ToDoList list = new ToDoList(title, items);

        //Store new ToDoList in ObservableList
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        lists.add(list);

        //try catch use the ToDoListItem method setCompleted() the item in the ToDoList
        try{
            lists.get(0).getItems().get(0).setCompleted();
        }catch(Exception e) {
        }
    }

    @Test
    void displayAllItems() {
        //Create title
        String title = "Shopping";

        //Create a couple new ToDoListItems
        ToDoListItem item1 = new ToDoListItem("Buy milk", "2021-07-25");
        ToDoListItem item2 = new ToDoListItem("Buy cereal", "2021-07-25");

        //Store new items in ArrayList
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();
        items.add(item1);
        items.add(item2);

        //Create new ToDoList using title and ObservableList
        ToDoList list = new ToDoList(title, items);

        //Store new ToDoList in ObservableList
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        lists.add(list);

        //try catch use the ObservableList method forEach() for the items in the ToDoList to print to console
        try{
            for (ToDoListItem item : lists.get(0).getItems()) {
                System.out.print("Item Description: " + item.getDescription() + ", Item Due Date: " + item.getDuedate() + "\n");
            }
        }catch(Exception e){
        }
    }

    @Test
    void displayIncompletedItems() {
        //Create title
        String title = "Shopping";

        //Create a couple new ToDoListItems
        ToDoListItem item1 = new ToDoListItem("Buy milk", "2021-07-25");
        ToDoListItem item2 = new ToDoListItem("Buy cereal", "2021-07-25");
        item2.setCompleted();

        //Store new items in ArrayList
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();
        items.add(item1);
        items.add(item2);

        //Create new ToDoList using title and ObservableList
        ToDoList list = new ToDoList(title, items);

        //Store new ToDoList in ObservableList
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        lists.add(list);

        //Go through the list to see which items are not completed and add those to a separate filtered list
        ObservableList<ToDoListItem> filtered = FXCollections.observableArrayList();

        for (ToDoListItem item : lists.get(0).getItems()) {
            if(item.getCompleted().equals("No")) {
                filtered.add(item);
            }
        }

        //Display only uncompleted items (print to console)
        System.out.println("Displaying only non-completed items");
        System.out.println("-----------------------------------");
        for (ToDoListItem toDoListItem : filtered) {
            System.out.println("Item Description: " + toDoListItem.getDescription());
        }
    }

    @Test
    void displayCompleted() {
        //Create title
        String title = "Shopping";

        //Create a couple new ToDoListItems
        ToDoListItem item1 = new ToDoListItem("Buy milk", "2021-07-25");
        ToDoListItem item2 = new ToDoListItem("Buy cereal", "2021-07-25");
        item2.setCompleted();

        //Store new items in ArrayList
        ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();        items.add(item1);
        items.add(item2);

        //Create new ToDoList using title and ObservableList
        ToDoList list = new ToDoList(title, items);

        //Store new ToDoList in ObservableList
        ObservableList<ToDoList> lists = FXCollections.observableArrayList();
        lists.add(list);

        //Go through the list to see which items are completed and add those to a separate filtered list
        ObservableList<ToDoListItem> filtered = FXCollections.observableArrayList();

        for (ToDoListItem item : lists.get(0).getItems()) {
            if(item.getCompleted().equals("Yes")) {
                filtered.add(item);
            }
        }

        //Display only uncompleted items (print to console)
        System.out.println("Displaying only non-completed items");
        System.out.println("-----------------------------------");
        for (ToDoListItem toDoListItem : filtered) {
            System.out.println("Item Description: " + toDoListItem.getDescription());
        }
    }

    @Test
    void saveSingleList() {
        //Create title
        //Create a couple new ToDoListItems
        //Store new items in ObservableList
        //Create new ToDoList using title and ObservableList
        //Store new ToDoList in ObservableList
        //Create new JSON file in directory
        //try catch use a json library to output the ToDoList into file as json
        //assertTrue using File method exists() to ensure that the file was created
    }

    @Test
    void saveAllLists() {
        //Create a couple ToDoLists
        //Store them in an ObservableList
        //Create new JSON file in directory
        //Create a JSON object arraylist
        //use forEach() Observable method:
            //try catch use a json library to convert each ToDoList to an object
            //Store the object in the json arraylist
        //use forEach() ArrayList method:
            //output each object to the json file
        //assertTrue using File method exists() to ensure that the file was created
    }

    @Test
    void loadSingleList() {
        //Create title
        //Create a couple new ToDoListItem
        //Store new items in ObservableList
        //Create new ToDoList using title and ObservableList
        //Store new ToDoList in ObservableList
        //Create new JSON file in directory
        //try catch use a json library to output the ToDoList into file as json
        //Use the json library to load them back into another ObservableList
        //assertEquals the first ObservableList against the ObservableList with the json loaded ToDoList
    }

    @Test
    void loadAllLists() {
        //Create a couple ToDoLists
        //Store them in an ObservableList
        //Create new JSON file in directory
        //Create a JSON object arraylist
        //use forEach() Observable method:
            //use a json library to convert each ToDoList to an object
            //Store the object in the json arraylist
        //use forEach() ArrayList method:
            //output each object to the json file
        //Use the same json library to read back each ToDoList object from the file into a new ObservableList
        //assertEquals the first ObservableList against the new ObservableList with the json loaded ToDoLists
    }
}