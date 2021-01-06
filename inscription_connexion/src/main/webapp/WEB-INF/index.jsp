<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    // JSTL Java Standard Tag Library
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Bienvenue !</h1>
<h2>Liste des séries</h2>
<ul>
<c:forEach items="${series}" var="serie">
	<li>${serie.nom} (${serie.prixEnEuros} &euro;) <a href="exportWord?ID_SERIE=${serie.id}">Export au format Word</a></li>
		<c:forEach items="${serie.saisons}" var="saison">
			${saison.nom}<br>
		</c:forEach>
</c:forEach>
</ul>
<c:if test="${sessionScope.utilisateur eq null}">
<a href="connexion">Connexion</a><br>
<a href="inscription">Inscription</a><br>
</c:if>
<c:if test="${sessionScope.utilisateur ne null}">
<a href="deconnexion">Déconnexion</a>
</c:if>
</body>
</html>