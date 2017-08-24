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
    public void insertToDoItem() {
        System.out.println(System.getProperty("name"));

        int items = System.getProperty("items") != null ?
                Integer.parseInt(System.getProperty("items")) : 1;
        createAndInsertToDoItems(items);
        List<ToDoItem> toDoItems = inMemoryToDoRepository.findAll();
        assertEquals(items, toDoItems.size());
    }

    private void createAndInsertToDoItems(int items) {
        System.out.println("Creating " + items + " To Do items.");
        for (int i = 0; i < items; i++) {
            ToDoItem toDoItem = new ToDoItem();
            toDoItem.setName("To Do task " + i);
            inMemoryToDoRepository.insert(toDoItem);
        }
    }
}
