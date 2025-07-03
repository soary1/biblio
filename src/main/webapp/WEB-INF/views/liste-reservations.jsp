<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Réservations</title></head>
<body>
<h2>Liste des réservations</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom utilisateur</th>
        <th>Livre</th>
        <th>Date</th>
        <th>Statut</th>
        <th>Action</th>
    </tr>
    <c:forEach var="res" items="${reservations}">
        <tr>
            <td>${res.idReservation}</td>
            <td>${res.signIn.userName}</td>
            <td>${res.livre.titre}</td>
            <td>${res.dateReservation}</td>
            <td>${res.statut}</td>
            <td>
                <c:if test="${res.statut == 'en attente'}">
                    <form action="${pageContext.request.contextPath}/reservation/accepter" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${res.idReservation}" />
                        <button type="submit">Accepter</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/reservation/refuser" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${res.idReservation}" />
                        <button type="submit">Refuser</button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
