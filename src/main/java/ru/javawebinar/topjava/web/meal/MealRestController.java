package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.MealServlet;

import java.util.Collections;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;
    public Meal save(Meal meal, int userId){
        LOG.info("save meal " + meal);
        if (meal.getUserId() == null || meal.getUserId() != userId)
            meal.setUserId(userId);
        return service.save(userId, meal);
    }

    public void delete(int id, int userId){
        LOG.info("delete meal " + id);
        service.delete(userId, id);
    }

    public Meal get(int id, int userId){
        LOG.info("get meal " + id);
        Meal meal = service.get(userId, id);
        return meal;
    }

    public List<Meal> getAll(int userId){
        LOG.info("getAll");
        List<Meal> userMeals =  service.getAll(userId);
        return userMeals.isEmpty() ? Collections.emptyList() : userMeals;
    }

    public void update(Meal meal, int userId) {
        LOG.info("update meal " + meal);
        save(meal, userId);
    }

    public List<MealWithExceed> unfilteredWithExceed(Integer userId){
        return userId == null ? Collections.emptyList()
                : MealsUtil.getWithExceeded(getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }
}
