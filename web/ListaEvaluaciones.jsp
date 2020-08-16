<%-- 
    Document   : ListaEvaluaciones
    Created on : Jul 22, 2020, 11:09:16 AM
    Author     : littman
--%>

<%@page import="idat.edu.pe.models.Usuario"%>
<%@page import="idat.edu.pe.models.Evaluacion"%>
<%@page import="java.util.List"%>
<%@page import="idat.edu.pe.models.Curso"%>
<%@page import="idat.edu.pe.models.Alumno"%>
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
        <div class="card">
            <div class="card-body">                
                <h5 class="card-title">Listado de evaluaciones:</h5>        
                <a href="CrearEvaluacion.jsp" class="btn btn-primary">Crear evaluación</a>
                <%
                    List<Evaluacion> evaluaciones = (List<Evaluacion>) request.getAttribute("evaluaciones");            
                %>
                
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">ID Evaluacion</th>
                            <th scope="col">Fecha de creacion</th>
                            <th scope="col">Fecha límite</th>
                            <th scope="col">Curso</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Evaluacion ev : evaluaciones) {
                        %>
                        <tr>
                            <td><%=ev.idEvaluacion %></td>
                            <td><%=ev.Seccion %></td>    
                            <td><%=ev.Curso %></td>    
                            <td><a class="btn btn-info" href="VerEvaluacionController?idEvaluacion=<%=ev.idEvaluacion%>">Ingresar</a></td>
                        </tr>
                        <%  }
                        %>
                    </tbody>
                </table>                
            </div>            
        </div>
        <% 
        }else{
            response.sendRedirect("index.jsp");
        }
        %>
    </body>
</html>
