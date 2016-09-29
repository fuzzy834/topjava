package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

    public static final Meal USER_MEAL = new Meal(1, LocalDateTime.of(2015, 5, 11, 12, 0, 0), "Dinner", 1000);
    public static final Meal ADMIN_MEAL = new Meal(2, LocalDateTime.of(2015, 5, 12, 7, 0, 0), "Breakfast", 1200);

}
