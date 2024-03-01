package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {
    private static final String SEP = System.lineSeparator();

    private static final String MENU = "1. Добавить элемент в корень меню" + SEP
            + "2. Добавить элемент к родительскому элементу" + SEP
            + "3. Вызвать действие, привязанное к пункту меню" + SEP
            + "4. Вывести меню в консоль" + SEP
            + "5. Выйти из приложения" + SEP;

    public static String getMenu() {
        return MENU;
    }

    public void run(Menu menu, MenuPrinter printer) {
        Scanner scanner = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            System.out.println(getMenu());
            String s = scanner.nextLine();
            switch (s) {
                case "1":
                    System.out.println("Введите имя корневого элемента");
                    String rootElement = scanner.nextLine();
                    System.out.println("Введите действие");
                    String action = scanner.nextLine();
                    addRoot(menu, rootElement, () -> System.out.println(action));
                    break;
                case "2":
                    System.out.println("Введите имя нового элемента");
                    String childElement = scanner.nextLine();
                    System.out.println("Введите действие для нового элемента");
                    String action2 = scanner.nextLine();
                    System.out.println("Введите имя родителя для нового элемента");
                    String parentElement = scanner.nextLine();
                    addParent(menu, childElement, () -> System.out.println(action2), parentElement);
                    break;
                case "3":
                    System.out.println("Введите имя элемента");
                    String element = scanner.nextLine();
                    action(menu, element);
                    break;
                case "4":
                    runMenu(printer, menu);
                    break;
                case "5":
                    bool = false;
                    break;
                default:
                    throw new IllegalArgumentException("Такого пункта не существует");
            }
        }
    }

    private void addRoot(Menu menu, String element, ActionDelegate actionDelegate) {
        menu.add(Menu.ROOT, element, actionDelegate);
    }

    private void addParent(Menu menu, String element, ActionDelegate actionDelegate, String parentName) {
        menu.add(parentName, element, actionDelegate);
    }

    private void action(Menu menu, String element) {
        menu.select(element)
                .ifPresent(item -> item.getActionDelegateInfo().delegate());
    }

    private void runMenu(MenuPrinter printer, Menu menu) {
        printer.print(menu);
    }

    public static void main(String[] args) {
        TodoApp app = new TodoApp();
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new Printer();
        app.run(menu, printer);
    }
}
