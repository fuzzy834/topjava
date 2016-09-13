package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * Created by Vitalii on 9/11/2016.
 */
public interface MealsDAO {
    void save(Meal meal);
    void remove(int id);
    Meal get(int id);
    Collection<Meal> getList();
}
