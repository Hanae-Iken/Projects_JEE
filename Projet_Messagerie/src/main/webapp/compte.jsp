<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Compte - Application Messagerie</title>
    <style>
        :root {
            --primary: #3f51b5;
            --primary-dark: #303f9f;
            --secondary: #ff4081;
            --danger: #f44336;
            --danger-dark: #d32f2f;
            --success: #4caf50;
            --light-grey: #f5f5f5;
            --dark-grey: #757575;
            --text-dark: #212121;
            --text-light: #ffffff;
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
            display: flex;
            flex-direction: column;
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
            flex: 1;
        }

        .page-title {
            margin-bottom: 1.5rem;
            color: var(--primary-dark);
            font-size: 2rem;
        }

        .user-card {
            background-color: white;
            border-radius: var(--border-radius);
            box-shadow: var(--shadow);
            padding: 2rem;
            margin-bottom: 2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .user-info h2 {
            color: var(--primary);
            margin-bottom: 0.5rem;
        }

        .btn {
            display: inline-block;
            padding: 0.6rem 1.2rem;
            border-radius: 4px;
            text-decoration: none;
            font-weight: 500;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
        }

        .btn:hover {
            transform: translateY(-2px);
        }

        .btn-danger {
            background-color: var(--danger);
            color: var(--text-light);
        }

        .btn-danger:hover {
            background-color: var(--danger-dark);
        }

        .message-list {
            display: grid;
            grid-gap: 1.5rem;
        }

        .message {
            background-color: white;
            border-radius: var(--border-radius);
            box-shadow: var(--shadow);
            padding: 1.5rem;
            transition: transform 0.2s;
        }

        .message:hover {
            transform: translateY(-3px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.15);
        }

        .message-subject {
            color: var(--primary);
            margin-bottom: 0.5rem;
            font-size: 1.2rem;
        }

        .message-content {
            color: var(--text-dark);
        }

        .empty-message {
            background-color: white;
            border-radius: var(--border-radius);
            padding: 2rem;
            text-align: center;
            color: var(--dark-grey);
            box-shadow: var(--shadow);
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

            .user-card {
                flex-direction: column;
                align-items: flex-start;
                gap: 1rem;
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
            <c:if test="${empty sessionScope.utilisateur}">
                <a href="${pageContext.request.contextPath}/connexion">Connexion</a>
                <a href="${pageContext.request.contextPath}/inscription">Inscription</a>
            </c:if>
            <c:if test="${not empty sessionScope.utilisateur}">
                <a href="${pageContext.request.contextPath}/compte">Mon Compte</a>
            </c:if>
        </nav>
    </div>
</header>

<div class="container content">
    <div class="user-card">
        <div class="user-info">
            <h2>Bienvenue, ${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}</h2>
            <p>Gérez vos messages personnels</p>
        </div>
        <a href="${pageContext.request.contextPath}/deconnexion" class="btn btn-danger">Déconnexion</a>
    </div>

    <h1 class="page-title">Mes Messages</h1>

    <c:if test="${empty messages}">
        <div class="empty-message">
            <p>Vous n'avez pas encore de messages.</p>
        </div>
    </c:if>

    <div class="message-list">
        <c:forEach var="message" items="${messages}">
            <div class="message">
                <h3 class="message-subject">${message.sujet}</h3>
                <p class="message-content">${message.contenu}</p>
            </div>
        </c:forEach>
    </div>
</div>

<footer>
    <div class="container">
        © 2025 - Application Messagerie | Projet académique réalisé à l'ENSA
    </div>
</footer>
</body>
</html>
