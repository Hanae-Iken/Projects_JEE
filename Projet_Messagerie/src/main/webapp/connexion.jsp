<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion - Application Messagerie</title>
    <style>
        :root {
            --primary: #3f51b5;
            --primary-dark: #303f9f;
            --secondary: #ff4081;
            --light-grey: #f5f5f5;
            --dark-grey: #757575;
            --text-dark: #212121;
            --text-light: #ffffff;
            --success: #4caf50;
            --danger: #f44336;
            --shadow: 0 2px 5px rgba(0,0,0,0.1);
            --border-radius: 8px;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: var(--light-grey);
            color: var(--text-dark);
            line-height: 1.6;
            min-height: 100vh;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
        }

        header {
            background-color: var(--primary);
            color: var(--text-light);
            padding: 1rem 0;
            box-shadow: var(--shadow);
            position: sticky;
            top: 0;
            z-index: 100;
        }

        header .container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo {
            font-size: 1.8rem;
            font-weight: bold;
            letter-spacing: 1px;
        }

        nav {
            display: flex;
            gap: 20px;
        }

        nav a {
            color: var(--text-light);
            text-decoration: none;
            font-weight: 500;
            padding: 0.5rem 1rem;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        nav a:hover {
            background-color: rgba(255, 255, 255, 0.1);
        }

        .content {
            padding: 2rem 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .page-title {
            margin-bottom: 1.5rem;
            color: var(--primary-dark);
            font-size: 2rem;
            text-align: center;
        }

        .form-card {
            background-color: white;
            border-radius: var(--border-radius);
            box-shadow: var(--shadow);
            padding: 2rem;
            width: 100%;
            max-width: 500px;
        }

        .alert {
            padding: 1rem;
            border-radius: var(--border-radius);
            margin-bottom: 1.5rem;
            font-weight: 500;
        }

        .alert-danger {
            background-color: rgba(244, 67, 54, 0.1);
            color: var(--danger);
            border: 1px solid rgba(244, 67, 54, 0.3);
        }

        .alert-success {
            background-color: rgba(76, 175, 80, 0.1);
            color: var(--success);
            border: 1px solid rgba(76, 175, 80, 0.3);
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
            color: var(--primary-dark);
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 0.8rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus, input[type="password"]:focus {
            outline: none;
            border-color: var(--primary);
            box-shadow: 0 0 0 2px rgba(63, 81, 181, 0.2);
        }

        .btn {
            display: inline-block;
            padding: 0.8rem 1.5rem;
            border-radius: 4px;
            text-decoration: none;
            font-weight: 500;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
            border: none;
            font-size: 1rem;
            width: 100%;
        }

        .btn:hover {
            transform: translateY(-2px);
        }

        .btn-primary {
            background-color: var(--primary);
            color: var(--text-light);
        }

        .btn-primary:hover {
            background-color: var(--primary-dark);
        }

        .form-footer {
            text-align: center;
            margin-top: 1.5rem;
            color: var(--dark-grey);
        }

        .form-footer a {
            color: var(--primary);
            text-decoration: none;
            font-weight: 500;
        }

        .form-footer a:hover {
            text-decoration: underline;
        }
        footer {
            background-color: var(--primary-dark);
            color: var(--text-light);
            text-align: center;
            padding: 1rem 0;
            margin-top: auto;
            font-size: 0.9rem;
        }

        @media (max-width: 768px) {
            header .container {
                flex-direction: column;
                gap: 1rem;
            }

            nav {
                width: 100%;
                justify-content: space-between;
            }
        }
    </style>
</head>
<body>
<header>
    <div class="container">
        <div class="logo">MessageApp</div>
        <nav>
            <a href="${pageContext.request.contextPath}/accueil">Accueil</a>
            <a href="${pageContext.request.contextPath}/sujets">Sujets</a>
            <a href="${pageContext.request.contextPath}/connexion">Connexion</a>
            <a href="${pageContext.request.contextPath}/inscription">Inscription</a>
        </nav>
    </div>
</header>

<div class="container content">
    <h1 class="page-title">Connexion</h1>

    <div class="form-card">
        <c:if test="${not empty erreur}">
            <div class="alert alert-danger">${erreur}</div>
        </c:if>

        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/connexion" method="post">
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

            <button type="submit" class="btn btn-primary">Se connecter</button>
        </form>

        <div class="form-footer">
            <p>Pas encore inscrit ? <a href="${pageContext.request.contextPath}/inscription">S'inscrire</a></p>
        </div>
    </div>
</div>
<footer>
    <div class="container">
        © 2025 - Application Messagerie | Projet académique réalisé à l'ENSA
    </div>
</footer>
</body>
</html>
