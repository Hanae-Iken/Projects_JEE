<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mon Compte - Application Messagerie</title>
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
        .message {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 5px;
        }
        .message h3 {
            margin-top: 0;
            color: #4CAF50;
        }
        .message p {
            margin-bottom: 0;
        }
        .user-info {
            background-color: #f9f9f9;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
        }
        .btn-deconnexion {
            background-color: #f44336;
            color: white;
            padding: 8px 15px;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
        }
        .btn-deconnexion:hover {
            background-color: #d32f2f;
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
    <c:if test="${empty sessionScope.utilisateur}">
        <a href="${pageContext.request.contextPath}/connexion">Connexion</a>
        <a href="${pageContext.request.contextPath}/inscription">Inscription</a>
    </c:if>
    <c:if test="${not empty sessionScope.utilisateur}">
        <a href="${pageContext.request.contextPath}/compte">Mon Compte</a>
    </c:if>
</nav>

<div class="user-info">
    <h2>Bienvenue, ${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}</h2>
    <a href="${pageContext.request.contextPath}/deconnexion" class="btn-deconnexion">Déconnexion</a>
</div>

<h2>Mes Messages</h2>

<c:if test="${empty messages}">
    <p>Vous n'avez pas encore de messages.</p>
</c:if>

<c:forEach var="message" items="${messages}">
    <div class="message">
        <h3>${message.sujet}</h3>
        <p>${message.contenu}</p>
    </div>
</c:forEach>
</body>
</html>