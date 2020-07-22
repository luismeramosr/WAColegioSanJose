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
                <a class="nav-link" href="ListarCursosController">Cursos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="EvaluacionController">Evaluación</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">                    
            <a class="btn btn-outline-light my-2 my-sm-0" href="LoginController" >Cerrar sesión</a>
        </form>
    </div>
</nav>
