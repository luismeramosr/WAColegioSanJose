<%@page import="idat.edu.pe.models.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="scriptstyle.jsp" %>
    </head>
    <body>
        <% 
            if(session.getAttribute("user")!=null){
            Usuario user = (Usuario) session.getAttribute("userData");
        %>
        <%@include file="navbar.jsp" %>
        <h1>
            Hola <%=user.nombre+" "+user.apellidos%>
        </h1>
        <% 
        }else{
            response.sendRedirect("index.jsp");
        }
        %>
    </body>
</html>
