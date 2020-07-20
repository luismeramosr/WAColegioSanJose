$(document).ready(() => {
        
});

let id= 1;

$(document).on("click", "#btnTest", function() {    
          
    let user = document.getElementById('txtUser').value;
    let password = document.getElementById('txtPassword').value;
    
    let formData = {'user': user, 'password': password};    
    
    addComponent(id);
    id++;
});

function addComponent(id) {
    
    var newDiv = document.createElement('div');
    newDiv.id = `toast${id}`;
    newDiv.innerHTML = `<toast${id}></toast${id}>`;
    document.body.appendChild(newDiv);
    
    var component = Vue.component(`toast${id}`, {
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
        el: `#toast${id}`,
        data: {
          msg: 'ABCDEASDFA'
        }
    })
}

function isOK(someData) {
    
    let wrongDataCounter = 0;
    Object.values(someData).forEach((value) => {
        value !== "" ? wrongDataCounter : wrongDataCounter++;
    });
    
    return wrongDataCounter === 0 ? true: false;
}

function post(url, jsonData) {
    if(isOK(jsonData)){
        fetch(url,{
            method: 'POST',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(jsonData)
        });
    } 
}

function runTest(test) {
    var promFetch=0, promJquery=0, sumFetch=0, sumJquery=0;
    var requests=500;
    
    for (var i=1; i<=requests; i++) {
        var result = test;
        sumFetch += result.fetchTime;
        sumJquery += result.jqueryTime;        
    }    
    promFetch = sumFetch/requests;
    promJquery = sumJquery/requests;
    console.log(`Averages for ${requests} requests`);
    console.log(`Fetch took: ${promFetch} seconds average.`);
    console.log(`Jquery took: ${promJquery} seconds average.`);  
}

function speedTest() {
    var start, end;
    
    // Jquery
    start = Date.now();
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: '/WAPACSJ/api',
        success: function(data, textStatus, jqXHR) {
//            console.log(data);
        }
    });
    end = Date.now();    
    var jqueryTime = (end-start)/1000;
    
    // Fetch api
    start = Date.now();
    fetch('/WAPACSJ/api').then((response) => {
        response.json().then((data) => {
//           console.log(data); 
        });
    });
    end = Date.now();    
    var fetchTime = (end-start)/1000;
    
    return {"jqueryTime": jqueryTime, "fetchTime": fetchTime};
}