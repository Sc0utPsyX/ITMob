package test1;

public class Main {

    public static void main(String[] args) {
        System.out.println("��������� �������� ����������� ����� � ����� �����������");

        Cat cat = new Cat();
        cat.age = 3;
        cat.name = "������";

        System.out.println("������� ���� �� ����� " + cat.name + ", ��� �������" + cat.age);
    }
}
