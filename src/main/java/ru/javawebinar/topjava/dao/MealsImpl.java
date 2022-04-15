package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsImpl implements MealsDAO {

    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    private AtomicInteger id = new AtomicInteger(0);

    {
        addMeal(new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY,
                30, 10, 0), "Завтрак", 500));
        addMeal(new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY,
                30, 13, 0), "Обед", 1000));
        addMeal(new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY,
                30, 20, 0), "Ужин", 500));
        addMeal(new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY,
                31, 0, 0), "Еда на граничное значение", 100));
        addMeal(new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY,
                31, 10, 0), "Завтрак", 1000));
        addMeal(new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY,
                31, 13, 0), "Обед", 500));
        addMeal(new Meal(id.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY,
                31, 20, 0), "Ужин", 410));
    }

    @Override
    public Collection<Meal> getMeals() {
        return this.meals.values();
    }

    @Override
    public void addMeal(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(id.getAndIncrement());
        }
        this.meals.put(meal.getId(), meal);
    }

    @Override
    public Meal getMeal(Integer id) {
        return this.meals.get(id);
    }

    @Override
    public void deleteMeal(Integer id) {
        this.meals.remove(id);
    }

    public AtomicInteger getId() {
        return id;
    }

    public void setId(AtomicInteger id) {
        this.id = id;
    }
}
