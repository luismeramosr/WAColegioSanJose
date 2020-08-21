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
    </head>
    <body>
        <% 
            if(session.getAttribute("user")!=null){
        %>
        <%@include file="navbar.jsp" %>
        <div class="card">
            <div class="card-body">                
                <h5 class="card-title">Listado de evaluaciones:</h5>       
                <%
                    List<Evaluacion> evaluaciones = (List<Evaluacion>) request.getAttribute("evaluaciones");
                    List<String> limitesEntrega = (List<String>) request.getAttribute("limitesEntrega");
                %>
                <a href="CrearEvaluacionController" class="btn btn-primary">Crear evaluación</a>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Evaluación</th>
                            <th scope="col">Curso</th>
                            <th scope="col">Fecha límite</th>
                            <th scope="col">Mantenimiento</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  
                            int i=0;
                            for (Evaluacion ev : evaluaciones) {
                        %>
                        <tr>
                            <td><%=ev.idEvaluacion %></td>
                            <td><%=ev.Curso %></td>    
                            <td><%=limitesEntrega.get(i) %></td>    
                            <td><button class="btn btn-warning mr-2 btnEditarEvaluacion"
                                        idEvaluacion="<%=ev.idEvaluacion%>">Editar</button>
                                <button class="btn btn-danger btnEliminarEvaluacion"
                                        idEvaluacion="<%=ev.idEvaluacion%>">Eliminar</button>
                            </td>
                        </tr>
                        <%
                            i++;
                            }
                        %>
                    <script>
                            $(document).on("click", ".btnEditarEvaluacion", (evt) => {
                                sessionStorage.setItem("idEvaluacion", evt.target.getAttribute("idEvaluacion"));
                                window.location.href = "EditarEvaluacion.jsp";
                            });
                            
                            $(document).on("click", ".btnEliminarEvaluacion", (evt) => {
                                let idEvaluacion = evt.target.getAttribute("idEvaluacion");
                                fetch("/WAColegioSanJose/EditarEvaluacionController",{
                                    method: "POST",
                                    headers: {
                                        "Content-type": "application/json"
                                    },
                                    body: JSON.stringify(idEvaluacion)
                                }).then((response) => {
                                    if(response.status === 200) {
                                        window.location.href = "/WAColegioSanJose/ListarEvaluacionesController";
                                    }            
                                    else
                                        alert("Ocurrio un error inesperado, intente en unos instantes");
                                });
                            });
                    </script>
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
