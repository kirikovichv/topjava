package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealsDAO {
    Collection<Meal> getMeals();

    void addMeal(Meal meal);

    Meal getMeal(Integer id);

    void deleteMeal(Integer id);
}
