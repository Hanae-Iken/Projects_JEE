<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil - Application Messagerie</title>
    <style>
        :root {
            --primary: #3f51b5;
            --primary-dark: #303f9f;
            --secondary: #ff4081;
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
            flex: 1;
            padding: 2rem 0;
        }

        .page-title {
            margin-bottom: 1.5rem;
            color: var(--primary-dark);
            font-size: 2rem;
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
            font-size: 1.2rem;
            font-weight: bold;
            color: var(--primary-dark);
            margin-bottom: 0.5rem;
        }

        .message-content {
            margin-top: 0.5rem;
            font-size: 1rem;
            color: var(--text-dark);
            line-height: 1.5;
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
    <h1 class="page-title">Messages Publics</h1>

    <c:if test="${empty messages}">
        <div class="empty-message">
            <p>Aucun message public disponible pour le moment.</p>
        </div>
    </c:if>

    <div class="message-list">
        <c:forEach var="message" items="${messages}">
            <div class="message">
                <div class="message-subject">
                    <strong>Sujet :</strong> ${message.sujet}
                </div>
                <hr style="margin: 10px 0;">
                <div class="message-content">
                    <strong>Contenu :</strong><br>
                    <p>${message.contenu}</p>
                </div>
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
