package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumberInfo() + i.getNameInfo()));
    }

    @Test
    public void whenAddThenCheckSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Жим", STUB_ACTION);
        menu.add(Menu.ROOT, "Тяга", STUB_ACTION);
        menu.add(Menu.ROOT, "Приседание", STUB_ACTION);
        menu.add("Жим", "Жим лежа", STUB_ACTION);
        menu.add("Жим", "Жим сидя", STUB_ACTION);
        menu.add("Жим лежа", "Гантели", STUB_ACTION);
        menu.add("Жим лежа", "Штанга", STUB_ACTION);
        menu.add("Тяга", "Штанги", STUB_ACTION);
        menu.add("Штанги", "В наклоне", STUB_ACTION);
        menu.add("Приседание", "Классические", STUB_ACTION);
        menu.add("Классические", "Со штангой", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Жим",
                List.of("Жим лежа", "Жим сидя"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Жим").get());
        assertThat(new Menu.MenuItemInfo("Приседание", List.of("Классические"), STUB_ACTION, "3."))
                .isEqualTo(menu.select("Приседание").get());
        assertThat(new Menu.MenuItemInfo("Тяга",
                List.of("Штанги"), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Тяга").get());
    }
}