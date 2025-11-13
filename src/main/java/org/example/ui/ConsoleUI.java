package org.example.ui;

import org.example.service.TaskService;
import org.example.model.Task;

import java.util.Scanner;

public class ConsoleUI {
    private final TaskService taskService = new TaskService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n==== TASK TRACKER ====");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать все задачи");
            System.out.println("3. Показать невыполненные задачи");
            System.out.println("4. Отметить задачу как выполненную");
            System.out.println("5. Удалить задачу");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addTask();
                case 2 -> showAllTasks();
                case 3 -> showIncompleteTasks();
                case 4 -> markTaskDone();
                case 5 -> deleteTask();
                case 0 -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Неверный ввод!");
            }
        }
    }

    private void addTask() {
        System.out.print("Введите название задачи: ");
        String title = scanner.nextLine();
        System.out.print("Введите описание: ");
        String description = scanner.nextLine();
        taskService.addTask(title, description);
        System.out.println("Задача добавлена!");
    }

    private void showAllTasks() {
        for (Task t : taskService.getAllTasks()) {
            System.out.println(t);
        }
    }

    private void showIncompleteTasks() {
        for (Task t : taskService.getIncompleteTasks()) {
            System.out.println(t);
        }
    }

    private void markTaskDone() {
        System.out.print("Введите ID задачи: ");
        int id = Integer.parseInt(scanner.nextLine());
        taskService.markAsDone(id);
        System.out.println("Задача отмечена как выполненная!");
    }

    private void deleteTask() {
        System.out.print("Введите ID задачи: ");
        int id = Integer.parseInt(scanner.nextLine());
        taskService.deleteTask(id);
        System.out.println("Задача удалена!");
    }
}
