package me.nibo.todo.repository;

import me.nibo.todo.model.ToDoItem;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class H2ToDoRepositoryIntegTest {
    private ToDoRepository h2ToDoRepository;

    @Before
    public void setUp() {
        h2ToDoRepository = new H2ToDoRepository();
    }

    @Test
    public void testInsertToDoItem() {
        ToDoItem newToDoItem = new ToDoItem();
        newToDoItem.setName("集成测试");
        Long newId = h2ToDoRepository.insert(newToDoItem);
        assertNotNull(newId);

        ToDoItem persistedToDoItem = h2ToDoRepository.findById(newId);
        assertNotNull(persistedToDoItem);
        assertEquals(persistedToDoItem, newToDoItem);
    }
}
