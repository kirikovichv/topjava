<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<h3><a href="/topjava/meals?action=add">Add Meal</a></h3>
<table rules="all" border="3" cellpadding="10">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th class="but"></th>
        <th class="but"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${meals}">
        <c:if test="${meal.excess}">
            <tr>
                <td><font color="red"> ${meal.date} ${meal.time}</font></td>
                <td><font color="red"> ${meal.description}</font></td>
                <td><font color="red"> ${meal.calories}</font></td>
                <td><a href="/topjava/meals?index=${meal.id}&action=update">Update</a></td>
                <td><a href="/topjava/meals?index=${meal.id}&action=delete">Delete</a></td>
            </tr>
        </c:if>
        <c:if test="${!meal.excess}">
            <tr>
                <td><font color="green"> ${meal.date} ${meal.time}</font></td>
                <td><font color="green"> ${meal.description}</font></td>
                <td><font color="green"> ${meal.calories}</font></td>
                <td><a href="/topjava/meals?index=${meal.id}&action=update">Update</a></td>
                <td><a href="/topjava/meals?index=${meal.id}&action=delete">Delete</a></td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
