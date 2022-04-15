<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Edit</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
${index == null ? "<h2>Add meals</h2>" : "<h2>Edit meals</h2>"}
<form action="edit" method="POST">
    DateTime: <input type="datetime-local" name="dateTime" value="${dateTime}"/>
    <br><br>
    Description: <input type="text" name="description" value="${description}"/>
    <br><br>
    Calories: <input type="text" name="calories" value="${calories}"/>
    <br><br>
    <input type="submit" value="Save"/>
    <button onclick="window.location.href = '/topjava/meals';" value="true" name="cancel">Cancel</button>
    <input type="hidden" name="index" value="${index}"/>
</form>
</body>
</html>
