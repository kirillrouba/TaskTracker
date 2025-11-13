package org.example.service;

import org.example.model.Task;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private final String FILE_NAME = "tasks.txt";

    public TaskService() {
        loadTasks();
    }

    public void addTask(String title, String description) {
        int id = tasks.size() + 1;
        Task task = new Task(id, title, description);
        tasks.add(task);
        saveTasks();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void markAsDone(int id) {
        tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .ifPresent(t -> t.setCompleted(true));
        saveTasks();
    }

    public void deleteTask(int id) {
        tasks.removeIf(t -> t.getId() == id);
        saveTasks();
    }

    public List<Task> getIncompleteTasks() {
        return tasks.stream()
                .filter(t -> !t.isCompleted())
                .toList();
    }

    @SuppressWarnings("unchecked")
    private void loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (List<Task>) ois.readObject();
        } catch (FileNotFoundException e) {
            tasks = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
