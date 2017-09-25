package me.nibo.todo.web;

import me.nibo.todo.model.ToDoItem;
import me.nibo.todo.repository.InMemoryToDoRepository;
import me.nibo.todo.repository.ToDoRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToDoServlet extends HttpServlet {
    private static final String FIND_ALL_SERVLET_PATH = "/all";
    private static final String INDEX_PAGE = "/jsp/todo-list.jsp";
    private ToDoRepository toDoRepository = new InMemoryToDoRepository();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();
        String view = processRequest(servletPath, req);
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }

    private String processRequest(String servletPath, HttpServletRequest req) {
        if (servletPath.equals(FIND_ALL_SERVLET_PATH)) {
            List<ToDoItem> toDoItems = toDoRepository.findAll();
            req.setAttribute("toDoItems", toDoItems);
            req.setAttribute("stats", determineStats(toDoItems));
            req.setAttribute("filter", "all");
            return INDEX_PAGE;
        } else if (servletPath.equals("/active")) {
            List<ToDoItem> toDoItems = toDoRepository.findAll();
            req.setAttribute("toDoItems", filterBasedOnStatus(toDoItems, true));
            req.setAttribute("stats", determineStats(toDoItems));
            req.setAttribute("filter", "active");
            return INDEX_PAGE;
        } else if (servletPath.equals("/completed")) {
            List<ToDoItem> toDoItems = toDoRepository.findAll();
            req.setAttribute("toDoItems", filterBasedOnStatus(toDoItems, false));
            req.setAttribute("stats", determineStats(toDoItems));
            req.setAttribute("filter", "active");
            return INDEX_PAGE;
        }
        if (servletPath.equals("/insert")) {
            ToDoItem toDoItem = new ToDoItem();
            toDoItem.setName(req.getParameter("name"));
            toDoRepository.insert(toDoItem);
            return "/" + req.getParameter("filter");
        } else if (servletPath.equals("/update")) {
            ToDoItem toDoItem = toDoRepository.findById(Long.parseLong(req.getParameter("id")));
            if (toDoItem != null) {
                toDoItem.setName(req.getParameter("name"));
                toDoRepository.update(toDoItem);
            }
            return "/" + req.getParameter("filter");
        } else if (servletPath.equals("/delete")) {
            ToDoItem toDoItem = toDoRepository.findById(Long.parseLong(req.getParameter("id")));
            if (toDoItem != null) {
                toDoRepository.delete(toDoItem);
            }
            return "/" + req.getParameter("filter");
        } else if (servletPath.equals("/toggleStatus")) {
            ToDoItem toDoItem = toDoRepository.findById(Long.parseLong(req.getParameter("id")));
            if (toDoItem != null) {
                boolean completed = "on".equals(req.getParameter("toggle"));
                toDoItem.setCompleted(completed);
                toDoRepository.update(toDoItem);
            }
            return "/" + req.getParameter("filter");
        } else if (servletPath.equals("/clearCompleted")) {
            List<ToDoItem> toDoItems = toDoRepository.findAll();
            for (ToDoItem toDoItem : toDoItems) {
                if (toDoItem.isCompleted()) {
                    toDoRepository.delete(toDoItem);
                }
            }
            return "/" + req.getParameter("filter");
        }
        return FIND_ALL_SERVLET_PATH;
    }

    private List<ToDoItem> filterBasedOnStatus(List<ToDoItem> toDoItems, boolean active) {
        List<ToDoItem> filteredToDoItems = new ArrayList<>();
        for (ToDoItem toDoItem : toDoItems) {
            if (toDoItem.isCompleted() != active) {
                filteredToDoItems.add(toDoItem);
            }
        }
        return filteredToDoItems;
    }

    private ToDoListStats determineStats(List<ToDoItem> toDoItems) {
        ToDoListStats toDoListStats = new ToDoListStats();
        for (ToDoItem toDoItem : toDoItems) {
            if (toDoItem.isCompleted()) {
                toDoListStats.addCompleted();
            } else {
                toDoListStats.addActive();
            }
        }
        return toDoListStats;
    }

    public class ToDoListStats {
        private int completed;
        private int active;

        public void addCompleted() {
            completed++;
        }

        public void addActive() {
            active++;
        }

        public int getActive() {
            return active;
        }

        public int getCompleted() {
            return completed;
        }

        public int getAll() {
            return completed + active;
        }
    }
}
