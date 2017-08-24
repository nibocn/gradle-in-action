package me.nibo.todo.repository;

import me.nibo.todo.model.ToDoItem;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class InMemoryToDoRepositoryNGTest {
    private ToDoRepository inMemoryToDoRepository;

    @BeforeClass
    public void setUp() {
        inMemoryToDoRepository = new InMemoryToDoRepository();
    }

    @Test
    public void insertToDoItem() {
        ToDoItem newToDoItem = new ToDoItem();
        newToDoItem.setName("测试");
        Long newId = inMemoryToDoRepository.insert(newToDoItem);
        assertNotNull(newId);
        ToDoItem persistedToDoItem = inMemoryToDoRepository.findById(newId);
        assertNotNull(persistedToDoItem);
        assertEquals(persistedToDoItem, newToDoItem);
    }
}
