<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Contacto"%>
<%
    ArrayList<Contacto> lista = (ArrayList<Contacto>) request.getAttribute("lista");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Lista de Contactos</h1>
        <p>Nuevo Contacto</p>
        <table border="1">
            <tr>
                <td>ID</td>
                <td>Nombre</td>
                <td>Correo</td>
                <td>Telefono</td>
            </tr>
            
            <!<!-- c:forech esde JSTL -->
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nombre}</td>
                    <td>${item.correo}</td>
                    <td>${item.telefono}</td>
                </tr>
            </c:forEach>
            
        </table>
    
    </body>
</html>
