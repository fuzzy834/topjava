package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealsDAO;
import ru.javawebinar.topjava.DAO.MealsDAOImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Vitalii on 9/13/2016.
 */
public class EditServlet extends HttpServlet {
    private MealsDAO mealsDAO = MealsDAOImpl.getMealsDAO();
    private static final Logger LOG = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("editing meal");
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
