package ru.javawebinar.topjava.web;

import org.json.JSONArray;
import org.slf4j.Logger;
import ru.javawebinar.topjava.MockDAO.MealsDAO;
import ru.javawebinar.topjava.MockDAO.MealsStorage;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Vitalii on 9/9/2016.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);
    private MealsDAO repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new MealsStorage();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        Meal userMeal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        repository.addMeal(userMeal);
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("Get All");
            request.setAttribute("mealList",
                    MealsUtil.getFilteredWithExceeded(repository.getMealsList(), LocalTime.MIN, LocalTime.MAX, 2000));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
        else if (action.equals("delete")){
            int id = getId(request);
            repository.removeMeal(id);
            response.sendRedirect("meals");
        }
        else{
            Meal meal = action.equals("create") ?
                    new Meal(LocalDateTime.now(), "", 1000) : repository.getMeal(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
