<%-- 
    Document   : ListarEvaluacionesResueltas
    Created on : Aug 22, 2020, 1:50:56 PM
    Author     : luisr
--%>

<%@page import="idat.edu.pe.models.AlumnoXEvaluacion"%>
<%@page import="java.util.List"%>
<%@page import="idat.edu.pe.models.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("user") != null) {
                Usuario user = (Usuario) session.getAttribute("userData");
        %>
        <%@include file="navbar.jsp" %>
        <div class="card">
            <div class="card-body">                
                <h5 class="card-title">Evaluaciones:</h5>       
                <%
                    List<AlumnoXEvaluacion> evaluaciones = (List<AlumnoXEvaluacion>) request.getAttribute("evaluaciones");
                    if (user.isDocente()) {
                %>
                <a href="CrearEvaluacion.jsp" class="btn btn-primary">Crear evaluación</a>
                <%}%>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Evaluación</th>
                            <th scope="col">Curso</th>
                            <th scope="col">Fecha límite</th>
                                <% if (user.isDocente()) {%>
                            <th scope="col">Mantenimiento</th>
                                <%} else if (user.isAlumno()) {%>
                            <th scope="col"> </th>
                                <%}%>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int i = 0;
                            for (AlumnoXEvaluacion ev : evaluaciones) {
                        %>
                        <tr>
                            <td><%=ev.idEvaluacion%></td>
                            <td><%=ev.Curso%></td>    
                            <td><%=limitesEntrega.get(i)%></td>
                            <td><button class="btn btn-warning mr-2 btnResolverEvaluacion"
                                        idEvaluacion="<%=ev.idEvaluacion%>" Seccion="<%=ev.Seccion%>">Resolver</button>
                            </td>

                        </tr>
                        <%
                            i++;
                            }
                        %>
                    </tbody>
                </table>                
            </div>            
        </div>
        <%
            } else {
                response.sendRedirect("index.jsp");
            }
        %>
    </body>
</html>
