package ru.javawebinar.topjava.web;

import org.json.JSONArray;
import org.json.JSONObject;
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
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Vitalii on 9/11/2016.
 */
public class MealServlet extends HttpServlet{
    private MealsDAO mealsDAO = new MealsDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealWithExceed> mealWithExceedList = MealsUtil.getFilteredWithExceeded(
                mealsDAO.getList(), LocalTime.MIN, LocalTime.MAX, 2000);

        req.setCharacterEncoding("UTF-8");
        req.setAttribute("mealWithExceedList", mealWithExceedList);;
        req.getRequestDispatcher("/mealList.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealWithExceed> mealWithExceedList = MealsUtil.getFilteredWithExceeded(
                mealsDAO.getList(), LocalTime.MIN, LocalTime.MAX, 2000);
        JSONArray jsonMeals = new JSONArray();
        for (MealWithExceed meal : mealWithExceedList){
            JSONObject tempMeal = new JSONObject();
            tempMeal.append("dateTime", meal.getDateTime().toString());
            tempMeal.append("description", meal.getDescription());
            tempMeal.append("calories", meal.getCalories());
            jsonMeals.put(tempMeal);
        }
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(jsonMeals);
    }
}
