package me.nibo.todo.repository

import me.nibo.todo.model.ToDoItem

interface ToDoRepository {
    List<ToDoItem> findAll()

    List<ToDoItem> findAllActive()

    List<ToDoItem> findAllCompleted()

    ToDoItem findById(Long id)

    Long insert(ToDoItem toDoItem)

    void update(ToDoItem toDoItem)

    void delete(ToDoItem toDoItem)
}