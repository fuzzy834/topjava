package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.datajpa.CrudUserRepository;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.List;

import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Vitalii on 10/10/2016.
 */
@ActiveProfiles(Profiles.DATAJPA)
public class UserDataJpaTest extends UserServiceTest {

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void testSave() throws Exception {
        super.testSave();
    }

    @Override
    public void testDuplicateMailSave() throws Exception {
        super.testDuplicateMailSave();
    }

    @Override
    public void testDelete() throws Exception {
        super.testDelete();
    }

    @Override
    public void testNotFoundDelete() throws Exception {
        super.testNotFoundDelete();
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
    public void testGetByEmail() throws Exception {
        super.testGetByEmail();
    }

    @Override
    public void testGetAll() throws Exception {
        super.testGetAll();
    }

    @Override
    public void testUpdate() throws Exception {
        super.testUpdate();
    }

    @Test
    public void testGetUserWithMeals(){
        User user = crudUserRepository.getUserWithMeals(USER_ID);
        List<Meal> meals = user.getMeals();
        System.out.println(meals.isEmpty());
        MealTestData.MATCHER.assertCollectionEquals(meals, MealTestData.MEALS);
    }
}
