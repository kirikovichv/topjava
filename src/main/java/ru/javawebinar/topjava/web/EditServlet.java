package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class EditServlet extends HttpServlet {
    private MealListDAO meals;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        meals = (MealListImpl) request.getAttribute("meals");
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
        Meal meal = new Meal(localDateTime, description, calories);
        if (!request.getParameter("index").equals("")) {
            meals.updateMeal(meal, Integer.parseInt(request.getParameter("index")));
        } else {
            meals.addMeal(meal);
        }
        response.sendRedirect("/topjava/meals");
    }
}
