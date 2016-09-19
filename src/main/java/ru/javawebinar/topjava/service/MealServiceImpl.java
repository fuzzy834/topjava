package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException {
        if (repository.get(id).getUserId() != userId)
            throw new NotFoundException("Meal Not Found " + id);
        repository.delete(id);
    }

    @Override
    public Meal get(int userId, int id) throws NotFoundException {
        Meal meal = repository.get(id);
        if (meal.getUserId() != userId)
            throw new NotFoundException("Meal Not Found " + id);
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> res = repository.getAll().stream()
                .filter(meal -> meal.getUserId()==userId).sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
        return res;
    }

    @Override
    public void update(int userId, Meal meal) {
        repository.save(userId, meal);
    }
}
