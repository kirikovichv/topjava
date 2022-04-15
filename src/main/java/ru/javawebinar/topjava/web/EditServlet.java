package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.*;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class EditServlet extends HttpServlet {
    private MealsDAO meals;
    public static final Logger log = getLogger(EditServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to edit.jsp");
        meals = (MealsImpl) request.getAttribute("meals");
        if (request.getAttribute("index") != null) {
            int index = Integer.parseInt((String) request.getAttribute("index"));
            request.setAttribute("dateTime", meals.getMeal(index).getDateTime());
            request.setAttribute("description", meals.getMeal(index).getDescription());
            request.setAttribute("calories", meals.getMeal(index).getCalories());
        } else {
            request.setAttribute("dateTime", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
            request.setAttribute("description", "");
            request.setAttribute("calories", 0);
        }
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("cancel") != null) {
            response.sendRedirect("/topjava/meals");
            return;
        }
        LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Integer id = request.getParameter("index").equals("") ? null
                : Integer.parseInt(request.getParameter("index"));
        Meal meal = new Meal(id, localDateTime, description, calories);
        meals.addMeal(meal);
        response.sendRedirect("/topjava/meals");
    }
}
