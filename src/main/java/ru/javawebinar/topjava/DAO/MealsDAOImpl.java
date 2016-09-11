package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vitalii on 9/11/2016.
 */
public class MealsDAOImpl implements MealsDAO{
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger(0);

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()){
            meal.setId(id.incrementAndGet());
        }
        return repository.put(meal.getId(), meal);
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
