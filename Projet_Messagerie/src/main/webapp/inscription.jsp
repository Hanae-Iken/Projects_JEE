<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription - Application Messagerie</title>
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
        form {
            max-width: 400px;
            margin: 0 auto;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            margin-bottom: 15px;
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

<h2>Inscription</h2>

<c:if test="${not empty erreur}">
    <div class="error">${erreur}</div>
</c:if>

<form action="${pageContext.request.contextPath}/inscription" method="post">
    <div class="form-group">
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" required>
    </div>

    <div class="form-group">
        <label for="prenom">Prénom:</label>
        <input type="text" id="prenom" name="prenom" required>
    </div>

    <div class="form-group">
        <label for="motDePasse">Mot de passe:</label>
        <input type="password" id="motDePasse" name="motDePasse" required>
    </div>

    <button type="submit">S'inscrire</button>
</form>
</body>
</html>