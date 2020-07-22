<%-- 
    Document   : Index
    Created on : Jul 16, 2020, 3:25:40 PM
    Author     : luisr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="scriptstyle.jsp" %>
        <link href="Styles/login.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
        <script src="Scripts/jsweb/login.js" type="text/javascript"></script>        
    </head>
    <body>
        <div class="card mx-auto">
            <div class="card-header">
                Iniciar sesión
            </div>
            <div class="card-body">
                <form>
                    <div class="form-group">
                        <label for="txtUser">Código</label>
                        <input type="text" class="form-control" id="txtUser" name="txtUser">
                    </div>
                    <div class="form-group">
                        <label for="txtPassword">Contraseña</label>
                        <input type="password" class="form-control" id="txtPassword" name="txtPassword">
                    </div>
                    <%
                        if (request.getAttribute("result") != null) {
                            String result = request.getAttribute("result").toString();
                    %>
                    <p id="emailHelp" class="form-text"><%=result%></p>
                    <%
                        }
                    %>
                    <button type="button" class="btn btn-primary" id="btnTest" >Ingresar</button>
                </form>                    
            </div>
        </div>        
    </body>
</html>
