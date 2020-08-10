<%-- 
    Document   : ListaEvaluaciones
    Created on : Jul 22, 2020, 11:09:16 AM
    Author     : littman
--%>

<%@page import="idat.edu.pe.models.Evaluacion"%>
<%@page import="java.util.List"%>
<%@page import="idat.edu.pe.models.Curso"%>
<%@page import="idat.edu.pe.models.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="scriptstyle.jsp" %>>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Listado de evaluaciones:</h5>
               
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
                            <th><%=ev.idEvaluacion %></th>                            
                            <td><a class="btn btn-info">Ingresar</a></td>
                        </tr>
                        <%  }
                        %>
                    </tbody>
                </table>
            </div>            
        </div>
    </body>
</html>
