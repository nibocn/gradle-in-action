package me.nibo.todo.repository

import me.nibo.todo.model.ToDoItem
import spock.lang.Specification

class InMemoryToDoRepositorySpec extends Specification {
    def "Insert To Do item"() {
        setup:
        ToDoRepository inMemoryToDoRepository = new InMemoryToDoRepository()
        when:
        ToDoItem newToDoItem = new ToDoItem()
        newToDoItem.setName("测试")
        Long newId = inMemoryToDoRepository.insert(newToDoItem)
        then:
        newId != null
        ToDoItem persistedToDoItem = inMemoryToDoRepository.findById(newId)
        persistedToDoItem != null
        newToDoItem == persistedToDoItem
    }
}
