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
    public Meal save(Meal meal){
        LOG.info("save meal " + meal);
        int userId = AuthorizedUser.id();
        if (meal.getUserId() == null || meal.getUserId() != userId)
            meal.setUserId(userId);
        return service.save(userId, meal);
    }

    public void delete(int id){
        LOG.info("delete meal " + id);
        int userId = AuthorizedUser.id();
        service.delete(userId, id);
    }

    public Meal get(int id){
        LOG.info("get meal " + id);
        int userId = AuthorizedUser.id();
        Meal meal = service.get(userId, id);
        return meal;
    }

    public List<Meal> getAll(){
        LOG.info("getAll");
        int userId = AuthorizedUser.id();
        List<Meal> userMeals =  service.getAll(userId);
        return userMeals.isEmpty() ? Collections.emptyList() : userMeals;
    }

    public void update(Meal meal) {
        LOG.info("update meal " + meal);
        save(meal);
    }

    public List<MealWithExceed> unfilteredWithExceed(){
        return AuthorizedUser.id() == null ? Collections.emptyList()
                : MealsUtil.getWithExceeded(getAll(), AuthorizedUser.getCaloriesPerDay());
    }
}
