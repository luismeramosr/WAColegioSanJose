<%@page import="idat.edu.pe.models.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-lg navbar-dark bg-danger">
    <a class="navbar-brand" href="home.jsp">San Jose</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="ListarCursos.jsp">Cursos</a>
            </li>            
                <% 
                    Usuario u = (Usuario) session.getAttribute("userData");
                    if (u.isAlumno()) {
                %>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Evaluaciones
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="ListarEvaluacionesController">Pendientes</a>
                        <a class="dropdown-item" href="ListarEvaluacionesResueltasController">Resueltas</a>
                    </div>
                </li>
                <%} else if (u.isDocente()) {%>
                <li>
                    <a class="nav-link" href="ListarEvaluacionesController">Evaluaciones</a>
                </li>
                <%}%>            
        </ul>
        <form class="form-inline my-2 my-lg-0">                    
            <a class="btn btn-outline-light my-2 my-sm-0" href="LoginController">Cerrar sesión</a>
        </form>
    </div>
</nav>
