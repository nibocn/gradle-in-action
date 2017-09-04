package me.nibo.todo.repository;

import me.nibo.todo.model.ToDoItem;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InMemoryToDoRepositoryTest {
    private ToDoRepository inMemoryToDoRepository;

    @Before
    public void setUp() {
        inMemoryToDoRepository = new InMemoryToDoRepository();
    }

    @Test
    public void testInsertToDoItem() {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName("Write unit tests");
        inMemoryToDoRepository.insert(toDoItem);
        List<ToDoItem> toDoItems = inMemoryToDoRepository.findAll();

        assertEquals(1, toDoItems.size());
        assertEquals(toDoItem, toDoItems.get(0));
    }
}
