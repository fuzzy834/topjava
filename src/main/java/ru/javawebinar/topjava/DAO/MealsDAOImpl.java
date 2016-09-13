package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vitalii on 9/11/2016.
 */
public class MealsDAOImpl implements MealsDAO{
    private static MealsDAOImpl mealsDAO;
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger(0);

    {
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    private MealsDAOImpl() {
    }

    public static MealsDAOImpl getMealsDAO(){
        if (mealsDAO == null)
            mealsDAO = new MealsDAOImpl();
        return mealsDAO;
    }

    @Override
    public void save(Meal meal) {
        if (meal.isNew()){
            meal.setId(id.incrementAndGet());
        }
        System.out.println("I'm here");
        repository.put(meal.getId(), meal);
        repository.values().forEach(System.out::println);
    }

    @Override
    public void remove(int id) {
        repository.remove(id);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getList() {
        return repository.values();
    }
}
