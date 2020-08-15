<%-- 
    Document   : evaluacion
    Created on : Jul 21, 2020, 3:33:13 PM
    Author     : luisr
--%>

<%@page import="idat.edu.pe.models.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="scriptstyle.jsp" %>
        <script type="module" src="Scripts/jsweb/crearEvaluacion.js" type="text/javascript"></script>
        <link href="Styles/crearEvaluacion.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <% 
            if(session.getAttribute("user")!=null){
            Usuario user = (Usuario) session.getAttribute("userData");
        %>
        <%@include file="navbar.jsp" %>           
        <div id="Evaluacion">    
            <div class="form-group">
                <h3 for="title">Título de la evaluación</h3>
                <input type="email" class="form-control" id="title" placeholder="Título...">
            </div>    
            <!-- Las preguntas se agregarán mediante Javascript-->
        </div>
        <button id="pushPregunta" class="btn btn-warning">Agregar pregunta</button>
        <button id="popPregunta" class="btn btn-warning">Quitar pregunta</button>
        <button id="save" class="btn btn-warning">Guardar evaluación</button>
        <% 
        }else{
            response.sendRedirect("index.jsp");
        }
        %>
    </body>
</html>
