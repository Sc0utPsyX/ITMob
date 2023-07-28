package ru.geekbrains.spring.ITMob.validators;

public interface Validator<E> {
    void validate(E e);
}
