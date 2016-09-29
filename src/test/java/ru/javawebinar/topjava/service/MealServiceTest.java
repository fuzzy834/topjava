package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by Vitalii on 9/28/2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    protected MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundGet(){
        service.get(2, 100000);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete(){
        service.delete(2, 100000);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate(){
        Meal newMeal = service.get(1, 100000);
        service.update(newMeal, 100001);
    }

    @Test
    public void get() throws Exception {
        MATCHER.assertEquals(USER_MEAL, service.get(1, 100000));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(1, 100000);
        Assert.assertEquals(0, service.getAll(100000).size());
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        List<Meal> meals = (List<Meal>) service.getBetweenDates(LocalDate.of(2014, 5, 21), LocalDate.of(2017, 10, 21), 100000);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL), meals);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        List<Meal> meals = (List<Meal>) service.getBetweenDateTimes(LocalDateTime.of(2014, 5, 21, 10, 11, 0), LocalDateTime.of(2017, 10, 21, 10, 11, 0), 100000);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL), meals);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL), service.getAll(100000));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal newMeal = new Meal(1, LocalDateTime.now(), "Supper", 1500);
        service.update(newMeal, 100000);
        MATCHER.assertEquals(service.get(1, 100000), newMeal);
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.now(), "Supper", 1500);
        Meal created = service.save(newMeal, 100001);
        MATCHER.assertCollectionEquals(Arrays.asList(created, ADMIN_MEAL), service.getAll(100001));
    }

}