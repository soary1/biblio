<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Rendre un Livre (Lecture sur place)</title>
</head>
<body>
    <h2>Rendre un Livre (Lecture sur place)</h2>
    <form:form method="post" modelAttribute="emprunt">
        <p>
            <form:label path="id">ID Emprunt :</form:label>
            <form:input path="id" readonly="true"/>
        </p>
        <p>
            <form:label path="dateFinEmprunt">Date et heure de retour :</form:label>
            <form:input path="dateFinEmprunt" type="datetime-local"/>
        </p>
        <p>
            <input type="submit" value="Valider le retour"/>
        </p>
    </form:form>
</body>
</html>
