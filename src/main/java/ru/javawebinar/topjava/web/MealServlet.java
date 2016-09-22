package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    //private MealRepository repository;
    private ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
    private MealRestController mealController =  appCtx.getBean(MealRestController.class);

    @Override
    public void destroy() {
        super.destroy();
        appCtx.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getSession().getAttribute("user")==null){
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        String action = request.getParameter("action");
        if ("filter".equals(action)) {
            LOG.info("Filtering meals");
            String startDate = request.getParameter("startDate");
            LocalDate sDate = startDate.isEmpty() ? LocalDate.MIN : LocalDate.parse(startDate);

            String endDate = request.getParameter("endDate");
            LocalDate eDate = endDate.isEmpty() ? LocalDate.MAX : LocalDate.parse(endDate);

            String startTime = request.getParameter("startTime");
            LocalTime sTime = startTime.isEmpty() ? LocalTime.MIN : LocalTime.parse(startTime);

            String endTime = request.getParameter("endTime");
            LocalTime eTime = endTime.isEmpty() ? LocalTime.MAX : LocalTime.parse(endTime);

            List<MealWithExceed> mealList = TimeUtil
                    .filterByDateTime(mealController.unfilteredWithExceed(), sDate, eDate, sTime, eTime);
            request.setAttribute("mealList", mealList);
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
        else {
            String id = request.getParameter("id");
            Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")), AuthorizedUser.id());

            LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);
            mealController.save(meal);
            response.sendRedirect("meals");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (request.getSession().getAttribute("user")==null){
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList",
                    mealController.unfilteredWithExceed());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            mealController.delete(id);
            response.sendRedirect("meals");

        } else if ("create".equals(action) || "update".equals(action)) {
            final Meal meal = action.equals("create") ?
                    new Meal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000, AuthorizedUser.id()) :
                    mealController.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
