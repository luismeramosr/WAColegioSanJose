<%-- 
<<<<<<< HEAD
    Document   : CrearEvaluacion
    Created on : Aug 10, 2020, 6:21:03 PM
    Author     : littman
--%>

=======
    Document   : evaluacion
    Created on : Jul 21, 2020, 3:33:13 PM
    Author     : luisr
--%>

<%@page import="idat.edu.pe.models.Usuario"%>
>>>>>>> f43716e0f7a28ce28861cb0597fa1c760989b664
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="scriptstyle.jsp" %>
<<<<<<< HEAD
    </head>
    <body>
        <%@include file="navbar.jsp" %>

        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Pregunta Nro</h5>

                <div class="input-group">

                    <textarea class="form-control" placeholder="Inserte Pregunta" aria-label="With textarea"></textarea>
                </div><br/>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <input type="radio" aria-label="Radio button">
                        </div>
                    </div>
                    <input type="text" class="form-control" aria-label="Text input">
                </div><br/>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <input type="radio" aria-label="Radio button">
                        </div>
                    </div>
                    <input type="text" class="form-control" aria-label="Text input">
                </div><br/>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <input type="radio" aria-label="Radio button">
                        </div>
                    </div>
                    <input type="text" class="form-control" aria-label="Text input">
                </div><br/>
                
            </div>  
        </div>
=======
        <script type="module" src="Scripts/jsweb/crearEvaluacion.js" type="text/javascript"></script>
        <link href="Styles/crearEvaluacion.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <% 
            if(session.getAttribute("user")!=null){
            Usuario user = (Usuario) session.getAttribute("userData");
        %>
        <%@include file="navbar.jsp" %>           
        <div id="Evaluacion">    
            <div class="form-group">
                <h3 for="title">Título de la evaluación</h3>
                <input type="email" class="form-control" id="title" placeholder="Título...">
            </div>    
            <!-- Las preguntas se agregarán mediante Javascript-->
        </div>
        <button id="pushPregunta" class="btn btn-warning">Agregar pregunta</button>
        <button id="popPregunta" class="btn btn-warning">Quitar pregunta</button>
        <button id="save" class="btn btn-warning">Guardar evaluación</button>
        <% 
        }else{
            response.sendRedirect("index.jsp");
        }
        %>
>>>>>>> f43716e0f7a28ce28861cb0597fa1c760989b664
    </body>
</html>
