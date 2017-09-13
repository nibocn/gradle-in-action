package me.nibo.todo.model

import groovy.transform.Canonical

class ToDoItem implements Comparable<ToDoItem> {
    Long id
    String name
    boolean completed

    int compareTo(ToDoItem toDoItem) {
        this.id.compareTo(toDoItem.id)
    }
}