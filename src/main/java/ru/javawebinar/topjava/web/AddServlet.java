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
 * Created by Vitalii on 9/14/2016.
 */
public class AddServlet extends HttpServlet {
    private MealsDAO mealsDAO = MealsDAOImpl.getMealsDAO();
    private static final Logger LOG = getLogger(UserServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("adding meal");
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("date"));
        String description = req.getParameter("desc");
        int calories = Integer.parseInt(req.getParameter("cal"));
        mealsDAO.save(new Meal(dateTime, description, calories));
    }
}
