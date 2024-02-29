package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo i : menu) {
            StringBuilder s = new StringBuilder();
            int count = 0;
            for (Character c : i.getNumberInfo().toCharArray()) {
                if (c == '.') {
                    count++;
                }
            }
            s.append("---".repeat(Math.max(0, count - 1)));
            System.out.println(s + i.getNameInfo() + " " + i.getNumberInfo());
        }
    }
}
