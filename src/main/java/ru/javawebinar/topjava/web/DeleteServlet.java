package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.DAO.MealsDAO;
import ru.javawebinar.topjava.DAO.MealsDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vitalii on 9/14/2016.
 */
public class DeleteServlet extends HttpServlet{
    private MealsDAO mealsDAO = MealsDAOImpl.getMealsDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("element_id"));
        mealsDAO.remove(id);
    }
}
