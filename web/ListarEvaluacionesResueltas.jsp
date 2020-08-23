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
        <%@include file="scriptstyle.jsp" %>
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
                    List<AlumnoXEvaluacion> resultados = (List<AlumnoXEvaluacion>) request.getAttribute("resultados");
                %>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Evaluación</th>
                            <th scope="col">Curso</th>
                            <th scope="col">Nota</th>
                                <% if (user.isDocente()) {%>
                            <th scope="col">Mantenimiento</th>
                                <%} else if (user.isAlumno()) {%>
                            <th scope="col">Resultados</th>
                                <%}%>
                        </tr>
                    </thead>
                    <tbody>
                        <%                            
                            for (AlumnoXEvaluacion resultado : resultados) {
                        %>
                        <tr>
                            <td><%=resultado.Evaluacion %></td>
                            <td><%=resultado.Curso %></td>    
                            <td><%=resultado.nota %></td>
                            <td><button class="btn btn-warning mr-2 btnGenerarReporteEvaluacion"
                                        Evaluacion="<%=resultado.Evaluacion%>" Alumno="<%=resultado.Alumno%>"
                                        Seccion="<%=resultado.Seccion%>" Curso="<%=resultado.Curso%>"
                                        >Ver detalles</button>
                            </td>
                        </tr>
                        <%                            
                            }
                        %>
                    <script>
                        $(document).on("click", ".btnGenerarReporteEvaluacion", (evt) => {
                            let Evaluacion = evt.target.getAttribute("Evaluacion");
                            let Alumno = evt.target.getAttribute("Alumno");
                            let Seccion = evt.target.getAttribute("Seccion");
                            let Curso = evt.target.getAttribute("Curso");
                            
                            window.open("/WAColegioSanJose/ReporteEvaluacionController?"+
                                        "Evaluacion="+Evaluacion+"&Alumno="+Alumno+
                                        "&Seccion="+Seccion+"&Curso="+Curso);                         
                            
                        });
                    </script>
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
