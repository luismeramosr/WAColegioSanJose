<%-- 
    Document   : ListaEvaluaciones
    Created on : Jul 22, 2020, 11:09:16 AM
    Author     : littman
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.time.Instant"%>
<%@page import="java.util.Date"%>
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
        <script src="Scripts/jsweb/ListarEvaluaciones.js" type="text/javascript"></script>
    </head>
    <body>
        <% 
            if(session.getAttribute("user")!=null){
            Usuario user = (Usuario) session.getAttribute("userData");
        %>
        <%@include file="navbar.jsp" %>
        <div class="card">
            <div class="card-body">                
                <h5 class="card-title">Evaluaciones:</h5>       
                <%
                    List<Evaluacion> evaluaciones = (List<Evaluacion>) request.getAttribute("evaluaciones");
                    List<String> limitesEntrega = (List<String>) request.getAttribute("limitesEntrega");
                    if (user.isDocente()){                                  
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
                            <%}else if (user.isAlumno()) {%>
                            <th scope="col"> </th>
                            <%}%>
                        </tr>
                    </thead>
                    <tbody>
                        <%  
                            int i=0;
                            for (Evaluacion ev : evaluaciones) {
                        %>
                        <tr>                              
                            <% if (user.isDocente()) {%>
                                <td><%=ev.idEvaluacion %></td>
                                <td><%=ev.Curso %></td>    
                                <td><%=limitesEntrega.get(i) %></td> 
                                <input  type="hidden" value="<%=ev.Seccion %>" id="<%=ev.idEvaluacion %>"/>
                                <td><button class="btn btn-success mr-2 btnGenerarReporteNotas"
                                        idEvaluacion="<%=ev.idEvaluacion%>" Seccion="<%=ev.Seccion%>"
                                        Curso="<%=ev.Curso %>">Reporte de Notas</button>
                                <button class="btn btn-warning mr-2 btnEditarEvaluacion"
                                        idEvaluacion="<%=ev.idEvaluacion%>">Editar</button>
                                <button class="btn btn-danger btnEliminarEvaluacion"
                                        idEvaluacion="<%=ev.idEvaluacion%>">Eliminar</button>
                                </td>                            
                            <%}else if (user.isAlumno() && ev.habilitada()) {%>
                                <td><%=ev.idEvaluacion %></td>
                                <td><%=ev.Curso %></td>    
                                <td><%=limitesEntrega.get(i) %></td>
                                <td><button class="btn btn-warning mr-2 btnResolverEvaluacion"
                                    idEvaluacion="<%=ev.idEvaluacion%>" Seccion="<%=ev.Seccion %>">Resolver</button>
                                </td>
                            <%}%>
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
        }else{
            response.sendRedirect("index.jsp");
        }
        %>
    </body>
</html>
