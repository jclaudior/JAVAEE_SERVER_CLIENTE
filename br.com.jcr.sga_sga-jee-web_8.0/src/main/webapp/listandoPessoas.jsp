<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Pessoas</title>
    </head>
    <body>
        <h1>Lista de Pessoas</h1>
        <ul>
            <c:forEach items="${pessoas}" var="pessoa">
                <li>${pessoa.nome} ${pessoa.apelido}</li>
            </c:forEach>
        </ul>
    </body>
</html>
