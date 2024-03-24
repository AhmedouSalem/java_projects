<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Votre page JSP</title>
    <!-- Inclure le code CSS et JavaScript pour le Snackbar -->
    <style>
        /* Styles pour le Snackbar */
        .snackbar {
            visibility: hidden; /* Par défaut, le Snackbar est caché */
            min-width: 250px;
            margin-left: -125px;
            background-color: #333;
            color: #fff;
            text-align: center;
            border-radius: 2px;
            padding: 16px;
            position: fixed;
            z-index: 1;
            left: 50%;
            top: 30px;
        }

        /* Styles pour le bouton de fermeture */
        .snackbar-close {
            cursor: pointer;
            position: absolute;
            right: 10px;
            top: 10px;
            color: #fff;
        }
    </style>
</head>
<body onload="showSnackbar()">
    <!-- Contenu de votre page JSP -->

    <!-- Snackbar -->
    <div id="snackbar" class="snackbar">
        Bienvenue sur notre site web !
        <span class="snackbar-close" onclick="closeSnackbar()">&times;</span>
    </div>

    <!-- JavaScript pour afficher et fermer le Snackbar -->
    <script>
        // Fonction pour afficher le Snackbar
        function showSnackbar() {
            var snackbar = document.getElementById("snackbar");
            snackbar.style.visibility = "visible";
            // Cacher le Snackbar après 3 secondes
            setTimeout(function() {
                snackbar.style.visibility = "hidden";
            }, 3000);
        }

        // Fonction pour fermer le Snackbar
        function closeSnackbar() {
            var snackbar = document.getElementById("snackbar");
            snackbar.style.visibility = "hidden";
        }
    </script>
</body>
</html>
