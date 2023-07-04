package test1;

public class Main {

    public static void main(String[] args) {
        System.out.println("Тестируем создание собственной ветки в общем репозитории");

        Cat cat = new Cat();
        cat.age = 3;
        cat.name = "Мурзик";

        System.out.println("Создали кота по имени " + cat.name + ", его возраст" + cat.age);
    }
}
