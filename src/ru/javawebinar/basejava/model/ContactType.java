package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Номер телефона"),
    SKYPE("Аккаунт Скайп"),
    EMAIL("Почтовый ящик"),
    LINKEDIN("Профиль на LinkedIn"),
    GITHUB("Профиль на github.com"),
    STACKOVERFLOW("Профиль на StackOverflow"),
    HOMEPAGE("Домашняя страница");

    private String name;

    ContactType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
