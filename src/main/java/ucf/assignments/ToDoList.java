/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Colin Mitchell
 */

package ucf.assignments;

import java.util.ArrayList;

public class ToDoList {
    private String title;
    private ArrayList<ToDoListItem> items = new ArrayList<ToDoListItem>();

    public ToDoList() {

    }
    //To-do list constructor
    public ToDoList(String title, ArrayList<ToDoListItem> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setItems(ArrayList<ToDoListItem> newItems) {
        this.items.removeAll(items);
        this.items.addAll(newItems);
    }

    //Getter which returns all items for TableView
    public ArrayList<ToDoListItem> getItems() {
        return items;
    }
}
