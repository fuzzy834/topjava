package ru.javawebinar.topjava.MockDAO;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * Created by Vitalii on 9/10/2016.
 */
public interface MealsDAO {
    Meal addMeal(Meal meal);

    Meal getMeal(int id);

    void removeMeal(int id);

    Collection<Meal> getMealsList();
}
