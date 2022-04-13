package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.*;
import static org.slf4j.LoggerFactory.getLogger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    public static final Logger log = getLogger(UserServlet.class);

    public static final int MAX_CALORIES_PER_DAY = 2000;

    private MealListDAO meals = new MealListImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        List<MealTo> mealsTo = MealsUtil.filteredByStreams(meals.getMeals(), LocalTime.MIN, LocalTime.MAX, MAX_CALORIES_PER_DAY);
        log.debug("redirect to meals.jsp");

        switch (action == null ? "show" : action) {
            case ("show"):
                request.setAttribute("index", 0);
                request.setAttribute("meals", mealsTo);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case ("delete"):
                int index = Integer.parseInt(request.getParameter("index"));
                meals.deleteMeal(index);
                response.sendRedirect("/topjava/meals");
                break;
            case ("update"):
                request.setAttribute("meals", meals);
                request.setAttribute("index", request.getParameter("index"));
                request.getRequestDispatcher("/edit").forward(request, response);
                break;
            case ("add"):
                request.setAttribute("meals", meals);
                request.setAttribute("index", null);
                request.getRequestDispatcher("/edit").forward(request, response);
                break;
        }
    }
}
