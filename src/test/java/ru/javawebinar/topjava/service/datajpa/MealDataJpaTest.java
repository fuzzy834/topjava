package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.datajpa.CrudMealRepository;
import ru.javawebinar.topjava.service.MealServiceTest;
import ru.javawebinar.topjava.service.UserService;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by Vitalii on 10/10/2016.
 */
@ActiveProfiles(Profiles.DATAJPA)
public class MealDataJpaTest extends MealServiceTest {

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Autowired
    private UserService userService;

    @Override
    public void testDelete() throws Exception {
        super.testDelete();
    }

    @Override
    public void testDeleteNotFound() throws Exception {
        super.testDeleteNotFound();
    }

    @Override
    public void testSave() throws Exception {
        super.testSave();
    }

    @Override
    public void testGet() throws Exception {
        super.testGet();
    }

    @Override
    public void testGetNotFound() throws Exception {
        super.testGetNotFound();
    }

    @Override
    public void testUpdate() throws Exception {
        super.testUpdate();
    }

    @Override
    public void testNotFoundUpdate() throws Exception {
        super.testNotFoundUpdate();
    }

    @Override
    public void testGetAll() throws Exception {
        super.testGetAll();
    }

    @Override
    public void testGetBetween() throws Exception {
        super.testGetBetween();
    }

    @Test
    public void testGetMealWithUser() throws Exception{
        Meal meal = crudMealRepository.getMealWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        UserTestData.MATCHER.assertEquals(userService.get(ADMIN_ID), meal.getUser());
    }
}
