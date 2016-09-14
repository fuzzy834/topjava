package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealsDAO;
import ru.javawebinar.topjava.DAO.MealsDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Vitalii on 9/14/2016.
 */
public class DeleteServlet extends HttpServlet{
    private MealsDAO mealsDAO = MealsDAOImpl.getMealsDAO();
    private static final Logger LOG = getLogger(UserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("deleting meal");
        int id = Integer.parseInt(req.getParameter("element_id"));
        mealsDAO.remove(id);
    }
}
