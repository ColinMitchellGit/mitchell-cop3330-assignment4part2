/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Colin Mitchell
 */

package ucf.assignments;

public class ToDoListItem {
    private String completed = "No";
    private String description;
    private String duedate;

    public ToDoListItem(){

    }
    public ToDoListItem(String description, String duedate) {
        this.description = description;
        this.duedate = duedate;
    }

    public String getCompleted() {
        return completed;
    }

    public String getDescription() {
        return description;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted() {
        this.completed = "Yes";
    }
}