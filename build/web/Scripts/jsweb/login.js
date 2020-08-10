$(document).on("click", "#btnTest", function() {   
    let user = document.getElementById("txtUser");
    let password = document.getElementById("txtPassword");

    if(isOK(user, password)){
        let formData = {"user": user.value, "password": password.value};
        post('/WAPACSJ/LoginController', formData);      
        $("#errPassword").text("Validando...");
    }
});

function isOK() {    
    
    var isOk = true;
    $("#errUser").text("");
    $("#errPassword").text("");
    $("#errLogin").text("");
    
    if(arguments[0].value === "") {
        isOk = false;
        $("#errUser").text("Debe ingresar su código");
    }
    
    if(arguments[1].value === ""){
        isOk = false;
        $("#errPassword").text("Debe ingresar su contraseña");       
    }

    return isOk;
}

function post(url, jsonData) {
    fetch(url,{
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(jsonData)
    }).then((response) => {
        if(response.status === 200)
            redirect('home.jsp');
        else if(response.status === 500){
            $("#errPassword").text("");
            $("#errLogin").show();
            $("#errLogin").text(`El usuario ${jsonData['user']}, ` +
                                `con contraseña ${jsonData['password']} ` +
                                'no existe o los datos son incorrectos');
        }            
        else
            alert("Ocurrio un error inesperado, intente en unos instantes");
    });
}

function redirect(url) {
    window.location.href = url;
}


