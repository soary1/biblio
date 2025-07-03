<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un Abonnement</title>
</head>
<body>
    <h2>Ajouter un Abonnement</h2>
    <form:form method="post" modelAttribute="abonnement">
        <p>
            <form:label path="nomPersonne">Nom de la personne :</form:label>
            <form:input path="nomPersonne"/>
        </p>
        <p>
            <form:label path="nbMoisAbonnement">Nombre de mois :</form:label>
            <form:input path="nbMoisAbonnement" type="number"/>
        </p>
        <p>
            <form:label path="dateDebutAbonnement">Date début abonnement :</form:label>
            <form:input path="dateDebutAbonnement" type="date"/>
        </p>
        <p>
            <input type="submit" value="Ajouter"/>
        </p>
    </form:form>
</body>
</html>