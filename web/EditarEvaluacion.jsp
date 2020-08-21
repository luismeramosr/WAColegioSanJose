<%-- 
    Document   : VerEvaluacion
    Created on : Aug 15, 2020, 4:17:55 PM
    Author     : luisr
    Author     : littman
--%>

<%@page import="java.util.List"%>
<%@page import="idat.edu.pe.models.Curso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="scriptstyle.jsp" %>
        <script type="module" src="Scripts/jsweb/EditarEvaluacion.js" type="text/javascript"></script>        
    </head>
    <body>
        <% if (session.getAttribute("user") != null) {
            List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
        %>
        <%@include file="navbar.jsp" %>        
        <div id="Evaluacion">
            <div class="form-group">
                <h3 for="title">Título de la evaluación</h3>
                <input type="email" class="form-control" id="title" placeholder="Título...">                
                <div class="settings mt-2">
                    <div class="mr-5">
                        <h3 for="title">Curso</h3>
                        <select class="form-control" id="cursoSelector">
                            <% for (Curso c : cursos) {%>
                            <option><%=c.nombre%></option>                            
                            <% }%>
                        </select>
                        <% for (Curso c : cursos) {%>
                        <input type="hidden" id="<%=c.nombre%>" value="<%=c.idCurso%>"/>
                        <% }%>
                    </div>
                    <div class="mr-5">
                        <h3>Duración</h3>
                        <input type="text" class="form-control" id="duracionPicker"/>                       
                    </div>   
                    <div>
                        <h3>Límite de entrega</h3>
                        <input type="text" class="form-control" id="limitePicker"/>                       
                    </div>
                </div>                      
            </div> 
        </div>
        <%} else {
                response.sendRedirect("index.jsp");
            }%>
    </body>
</html>


