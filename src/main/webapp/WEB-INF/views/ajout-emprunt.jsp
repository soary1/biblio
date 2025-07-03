<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Ajouter un Emprunt</title>
    <script>
        function toggleDateFin() {
            var typeLecture = document.getElementById('typeDeLecture');
            var dateFinDiv = document.getElementById('dateFinDiv');
            if (typeLecture.value === 'sur place' || typeLecture.value === 'a emporte') {
                dateFinDiv.style.display = 'none';
                document.getElementById('dateFinEmprunt').value = '';
            } else {
                dateFinDiv.style.display = 'block';
            }
        }
        window.onload = function() {
            toggleDateFin();
            document.getElementById('typeDeLecture').addEventListener('change', toggleDateFin);
        };
    </script>
</head>
<body>
    <h2>Ajouter un Emprunt</h2>
    <c:if test="${not empty message}">
    <div style="color: red; font-weight: bold;">${message}</div>
</c:if>

    <form:form method="post" modelAttribute="emprunt">
        <p>
            <form:label path="idLivre">ID Livre :</form:label>
            <form:select path="idLivre">
                <form:option value="" label="-- Sélectionnez un livre --" />
                <form:options items="${livres}" itemValue="id" itemLabel="numExemplaire"/>
            </form:select>
        </p>

        <p>
            <form:label path="idNomEmprunteur">Nom Emprunteur :</form:label>
            <form:select path="idNomEmprunteur">
                <form:option value="" label="-- Nom Emprunteur --" />
                <form:options items="${adherent}" itemValue="id" itemLabel="userName"/>
            </form:select>
        </p>
        <p>
            <form:label path="typeDeLecture">Type de lecture :</form:label>
            <form:select path="typeDeLecture" id="typeDeLecture">
                <form:option value="" label="-- Sélectionnez un type --" />
                <form:option value="sur place" label="Sur place" />
                <form:option value="a emporte" label="a emporte" />
            </form:select>
        </p>

        <p>
            <form:label path="dateDebutEmprunt">Date début :</form:label>
            <form:input path="dateDebutEmprunt" type="datetime-local"/>
        </p>
        <!-- <div id="dateFinDiv">
            <p>
                <form:label path="dateFinEmprunt">Date fin :</form:label>
                <form:input path="dateFinEmprunt" type="datetime-local" id="dateFinEmprunt"/>
            </p>
        </div> -->
        <p>
            <input type="submit" value="Ajouter"/>
        </p>
    </form:form>
    <p>
        <a href="${pageContext.request.contextPath}/rendre-emprunt-surplace">Rendre un livre (lecture sur place)</a>
        <a href="${pageContext.request.contextPath}/livres">&#8592; Retour liste livre</a>
    </p>
</body>
</html>