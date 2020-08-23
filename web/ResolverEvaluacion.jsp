<%-- 
    Document   : ResolverEvaluacion
    Created on : Aug 22, 2020, 8:35:42 AM
    Author     : luisr
--%>

<%@page import="idat.edu.pe.models.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="scriptstyle.jsp" %>        
        <script type="module" src="Scripts/jsweb/ResolverEvaluacion.js" type="text/javascript"></script>
        <link href="Styles/Evaluacion.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <% if (session.getAttribute("userData")!=null) {
            Usuario alumno = (Usuario) session.getAttribute("userData");
        %>
        <%@include file="navbar.jsp" %>        
        <div id="Evaluacion">
            <div class="form-group">
                <h4 id="title"></h4>             
                <div class="settings mt-2">                    
                    <div class="mr-5 flex">
                        <span><strong>Tiempo restante: </strong></span>
                        <span id="tiempoRestante"></span>
                    </div>                       
                </div>                      
            </div>
        </div>
        <button id="enviarEvaluacion" class="btn btn-warning">Enviar evaluacion</button>
        <input type="hidden" value="<%=alumno.idUsuario %>" id="idAlumno">        
        <%}%>
    </body>
</html>
