<%-- 
    Document   : ListaEvaluaciones
    Created on : Jul 22, 2020, 11:09:16 AM
    Author     : littman
--%>

<%@page import="java.util.List"%>
<%@page import="idat.edu.pe.models.Curso"%>
<%@page import="idat.edu.pe.models.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
     
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Listado de cursos:</h5>
               
                <%
                    List<Alumno> lstalu = (List<Alumno>) request.getAttribute("lstalu");
            
                %>
                
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">ID Curso</th>
                            <th scope="col">Nombre Curso</th>
                            <th scope="col"> </th>
                           
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Alumno alu : lstalu) {
                        %>
                        <tr>
                            <th><%=alu.idAlumno%></th>
                            <td><%=alu.nombre%></td>
                            
                            <td><a 
                                   class="btn btn-info">Ingresar</a></td>
                        </tr>
                        <%  }
                        %>
                    </tbody>
                </table>
            </div>            
        </div>
    </body>
</html>
