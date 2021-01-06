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
<title>Merci pour votre inscription</title>
</head>
<body>
Merci ${utilisateur.email} pour votre inscription !<br>
<a href="connexion">Connexion</a><br>
</body>
</html>