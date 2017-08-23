package me.nibo.todo.repository;

import me.nibo.todo.model.ToDoItem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InMemoryToDoRespositoryTest {
    private ToDoRepository inMemoryToDoRepository;

    @Before
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
        assertEquals(newToDoItem, persistedToDoItem);
    }
}
