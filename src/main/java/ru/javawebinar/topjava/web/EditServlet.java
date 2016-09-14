package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.DAO.MealsDAO;
import ru.javawebinar.topjava.DAO.MealsDAOImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by Vitalii on 9/13/2016.
 */
public class EditServlet extends HttpServlet {
    private MealsDAO mealsDAO = MealsDAOImpl.getMealsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("value");
        String elementClass = req.getParameter("element_class");
        int id = Integer.parseInt(req.getParameter("element_id"));

        Meal meal = mealsDAO.get(id);
        if (elementClass.equals("date_time"))
            meal.setDateTime(LocalDateTime.parse(value));
        else if(elementClass.equals("description"))
            meal.setDescription(value);
        else {
            meal.setCalories(Integer.parseInt(value));
        }
        mealsDAO.save(meal);
    }
}
