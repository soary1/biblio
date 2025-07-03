<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Rendre un Livre (Lecture sur place)</title>
</head>
<body>
    <h2>Rendre un Livre (Lecture sur place)</h2>
    <form action="${pageContext.request.contextPath}/rendre-emprunt-surplace" method="post">
        <label>Nom de l'emprunteur :</label>
        <select name="empruntId" required>
            <option value="">-- Sélectionnez --</option>
            <c:forEach var="emprunt" items="${empruntsEnCours}">
                <option value="${emprunt.id}">
                    ${nomsEmprunteurs[emprunt.id]}
                </option>
            </c:forEach>
        </select>
        <br>
        <label>Date et heure de retour :</label>
        <input type="datetime-local" name="dateFinEmprunt" required />
        <br>
        <input type="submit" value="Valider le retour"/>
    </form>
    <p>
        <a href="${pageContext.request.contextPath}/ajout-emprunt">&#8592; Retour à l'ajout d'emprunt</a>
    </p>
</body>
</html>