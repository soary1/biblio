<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Prêter un livre</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />"/>
</head>
<body>
    <h2>Effectuer un prêt</h2>

    <form action="${pageContext.request.contextPath}/pret/creer" method="post">
        <label for="idExemplaire">Exemplaire :</label>
        <select name="idExemplaire" required>
            <c:forEach items="${exemplaires}" var="ex">
                <option value="${ex.idExemplaire}">
                    Livre: ${ex.livre.titre} - ID: ${ex.idExemplaire}
                </option>
            </c:forEach>
        </select><br><br>

        <label for="idAdherant">Adhérant :</label>
        <select name="idAdherant" required>
            <c:forEach items="${adherants}" var="ad">
                <option value="${ad.idAdherant}">
                    ${ad.nomAdherant} ${ad.prenomAdherant}
                </option>
            </c:forEach>
        </select><br><br>

        <label for="idAdmin">Administrateur :</label>
        <select name="idAdmin" required>
            <c:forEach items="${admins}" var="a">
                <option value="${a.idAdmin}">
                    ${a.nomAdmin} ${a.prenomAdmin}
                </option>
            </c:forEach>
        </select><br><br>

        <label for="idTypePret">Type de prêt :</label>
        <select name="idTypePret" required>
            <c:forEach items="${typesPret}" var="t">
                <option value="${t.idTypePret}">
                    ${t.type}
                </option>
            </c:forEach>
        </select><br><br>

        <button type="submit">Valider le prêt</button>
    </form>

    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>
</body>
</html>
