package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.AuthorizedUser;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class UserServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forward to userList");
        request.getRequestDispatcher("/userList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String user = req.getParameter("user");
        String logout = req.getParameter("action");
        if ("logout".equals(logout)) {
            AuthorizedUser.setId(null);
            req.getSession().invalidate();
            req.setAttribute("authorized", false);
        } else {
            AuthorizedUser.setId(Integer.parseInt(user));
            req.getSession().setAttribute("authorized", true);
            req.getSession().setAttribute("user", user);
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
