<%-- 
    Document   : ListaCursos
    Created on : Jul 21, 2020, 7:08:49 PM
    Author     : littman
--%>

<%@page import="idat.edu.pe.models.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="idat.edu.pe.models.Curso"%>
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
                <h5 class="card-title">Listado de cursos:</h5>
               
                <%
                    List<Curso> lstcursos = (List<Curso>) request.getAttribute("lstcursos");
            
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
                            for (Curso cur : lstcursos) {
                        %>
                        <tr>
                            <th><%=cur.idCurso %></th>
                            <td><%=cur.nombre %></td>
                            
                            <td><a href="ListarEvaluacionesController?idCurso=<%=cur.idCurso%>"
                                   class="btn btn-info">Ver evaluaciones</a></td>
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
