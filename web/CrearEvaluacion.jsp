<%-- 
    Document   : evaluacion
    Created on : Jul 21, 2020, 3:33:13 PM
    Author     : luisr
    Author     : littman
--%>

<%@page import="java.util.List"%>
<%@page import="idat.edu.pe.models.Curso"%>
<%@page import="idat.edu.pe.models.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="scriptstyle.jsp" %>
        <%@include file="datetimepicker.jsp" %>
        <link href="Styles/Evaluacion.css" rel="stylesheet" type="text/css"/>      
        <script type="module" src="Scripts/jsweb/CrearEvaluacion.js" type="text/javascript"></script>
    </head>
    <body>
        <% 
            if(session.getAttribute("user")!=null){
            List<Curso> cursos = (List<Curso>) session.getAttribute("cursos");
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
                        <% for (Curso c: cursos) { %>
                            <option><%=c.nombre %></option>                            
                        <% }%>
                        </select>
                        <% for(Curso c: cursos) { %>
                            <input type="hidden" id="<%=c.nombre %>" value="<%=c.idCurso %>"/>
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
