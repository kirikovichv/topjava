package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealListDAO {
    List<Meal> getMeals();

    void addMeal(Meal meal);

    Meal getMeal(int index);

    void updateMeal(Meal meal, int index);

    void deleteMeal(int index);
}
