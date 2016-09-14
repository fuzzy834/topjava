package ru.javawebinar.topjava.web;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealsDAO;
import ru.javawebinar.topjava.DAO.MealsDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Vitalii on 9/11/2016.
 */
public class MealServlet extends HttpServlet{
    private static final Logger LOG = getLogger(UserServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           req.getRequestDispatcher("/mealList.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("creating meal list");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(MealsUtil.parseMealListToJSON());
    }
}
