<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inscription</title>
</head>
<body>
<h2>Formulaire d'inscription</h2>

<form:form method="post" modelAttribute="sign_in">
    <p>
        <form:label path="userName">Nom d'utilisateur :</form:label>
        <form:input path="userName"/>
    </p>
    <p>
        <form:label path="motDePasse">Mot de passe :</form:label>
        <form:password path="motDePasse"/>
    </p>
    <p>
        <form:label path="email">Email :</form:label>
        <form:input path="email"/>
    </p>
    <p>
        <form:label path="numero">Numéro :</form:label>
        <form:input path="numero"/>
    </p>
    <p>
        <form:label path="profil">Profil :</form:label>
        <form:select path="profil">
            <form:option value="" label="-- Sélectionner --"/>
            <form:option value="etudiant" label="Étudiant"/>
            <form:option value="enseignant" label="Enseignant"/>
            <form:option value="autre" label="Autre"/>
        </form:select>
    </p>
    <p>
        <input type="submit" value="S'inscrire"/>
    </p>
</form:form>

</body>
</html>
