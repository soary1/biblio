<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Liste des Livres</title>
</head>
<body>
    <h2>Liste des Livres</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>NumExemplaire</th>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Image</th>
            <th>Action</th>
        </tr>
        <c:forEach var="livre" items="${livres}">
            <tr>
                <td>${livre.id}</td>
                <td>${livre.numExemplaire}</td>
                <td>${livre.titre}</td>
                <td>${livre.auteur}</td>
                <td>${livre.image}</td>


                <td>
                    <c:if test="${profil ne 'admin'}">
                        <form action="${pageContext.request.contextPath}/reserver-livre" method="post">
                            <input type="hidden" name="idLivre" value="${livre.id}" />
                            <label for="dateReservation">Date réservation :</label>
                            <input type="date" name="dateReservation" required />
                            <button type="submit">Réserver</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
                <c:if test="${not empty message}">
    <div style="color: red; font-weight: bold;">${message}</div>
</c:if>
    <c:if test="${profil == 'admin'}">
        <a href="${pageContext.request.contextPath}/ajout-abonnement">Ajouter un abonnement</a>
        <a href="${pageContext.request.contextPath}/ajout-emprunt">Ajouter un emprunt</a>
        <a href="${pageContext.request.contextPath}/livres">&#8592; Retour à l'ajout d'emprunt</a>
        <a href="${pageContext.request.contextPath}/reservations">Gérer les réservations</a>

    </c:if>
</body>
</html>
