/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

function addComponent(id) {
    
    var div = document.getElementById(id);
    div.innerHTML = `<evaluacion></evaluacion>`;
    
    var component = Vue.component(`evaluacion`, {
       template: `
            <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
    <a class="navbar-brand" href="#">AppIDAT</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="home.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Mantenimiento
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="ListarCursosController">Curso</a>
                    <a class="dropdown-item" href="#">Alumno</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="ListaAjaxCursoController">Curso con Ajax</a>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">                    
            <a class="btn btn-outline-light my-2 my-sm-0" href="#" >Cerrar sesión</a>
        </form>
    </div>
</nav>
        `
    });
    
    var app = new Vue({
        el: `#${id}`,
        data: {
          msg: 'ABCDEASDFA'
        }
    })
}
