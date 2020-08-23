/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).on("click", ".btnEditarEvaluacion", (evt) => {
    let idEvaluacion = evt.target.getAttribute("idEvaluacion")
    sessionStorage.setItem("idEvaluacion", idEvaluacion);
    let Seccion = document.getElementById(idEvaluacion).value;
    sessionStorage.setItem("seccion", Seccion);
    window.location.href = "EditarEvaluacion.jsp";
});

$(document).on("click", ".btnEliminarEvaluacion", (evt) => {
    let idEvaluacion = evt.target.getAttribute("idEvaluacion");
    fetch("/WAColegioSanJose/EditarEvaluacionController", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({"id": idEvaluacion})
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = "/WAColegioSanJose/ListarEvaluacionesController";
        } else
            alert("Ocurrio un error inesperado, intente en unos instantes");
    });
});

$(document).on("click", ".btnResolverEvaluacion", (evt) => {
    let idEvaluacion = evt.target.getAttribute("idEvaluacion");
    let Seccion = evt.target.getAttribute("Seccion");
    sessionStorage.setItem("idEvaluacion", idEvaluacion);
    sessionStorage.setItem("Seccion", Seccion);
    window.location.href = "ResolverEvaluacion.jsp";
});
