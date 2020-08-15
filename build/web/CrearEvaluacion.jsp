<%-- 
    Document   : CrearEvaluacion
    Created on : Aug 10, 2020, 6:21:03 PM
    Author     : littman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="scriptstyle.jsp" %>
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
    </body>
</html>
