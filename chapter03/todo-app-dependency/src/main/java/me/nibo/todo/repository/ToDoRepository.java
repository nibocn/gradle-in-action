package me.nibo.todo.repository;

import me.nibo.todo.model.ToDoItem;

import java.util.List;

public interface ToDoRepository {
    List<ToDoItem> findAll();

    ToDoItem findById(long id);

    long insert(ToDoItem toDoItem);

    void delete(ToDoItem toDoItem);

    void update(ToDoItem toDoItem);

}
