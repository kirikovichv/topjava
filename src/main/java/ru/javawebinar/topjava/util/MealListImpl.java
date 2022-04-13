package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.*;
import java.util.*;

public class MealListImpl implements MealListDAO {

    private List<Meal> meals;

    public MealListImpl() {
        meals = new ArrayList<>(Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        ));
    }

    @Override
    public List<Meal> getMeals() {
        return this.meals;
    }

    @Override
    synchronized public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    @Override
    public Meal getMeal(int index) {
        return this.meals.get(index);
    }

    @Override
    synchronized public void updateMeal(Meal meal, int index) {
        this.meals.set(index, meal);
    }

    @Override
    synchronized public void deleteMeal(int index) {
        this.meals.remove(index);
    }
}
