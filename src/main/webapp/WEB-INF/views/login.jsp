<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
    <h2>Formulaire de Connexion</h2>
    <form method="post" action="${pageContext.request.contextPath}/login">
        Nom d'utilisateur : <input type="text" name="userName" /><br><br>
        Mot de passe : <input type="password" name="motDePasse" /><br><br>
        <button type="submit">Se connecter</button>
    </form>
    <a href="inscription">inscription</a>
</body>
</html>
