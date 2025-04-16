<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sujets - Application Messagerie</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            line-height: 1.6;
        }
        header {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            text-align: center;
            margin-bottom: 20px;
        }
        nav {
            margin-bottom: 20px;
        }
        nav a {
            margin-right: 15px;
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
        }
        ul {
            list-style-type: circle;
            padding-left: 20px;
        }
        li {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<header>
    <h1>Application Messagerie</h1>
</header>

<nav>
    <a href="${pageContext.request.contextPath}/accueil">Accueil</a>
    <a href="${pageContext.request.contextPath}/sujets">Sujets</a>
    <a href="${pageContext.request.contextPath}/connexion">Connexion</a>
    <a href="${pageContext.request.contextPath}/inscription">Inscription</a>
</nav>

<h2>Sujets des Messages Publics</h2>

<c:if test="${empty sujets}">
    <p>Aucun sujet public disponible.</p>
</c:if>

<ul>
    <c:forEach var="sujet" items="${sujets}">
        <li>${sujet}</li>
    </c:forEach>
</ul>
</body>
</html>