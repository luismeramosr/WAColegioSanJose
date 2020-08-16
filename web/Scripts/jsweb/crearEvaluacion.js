import {EvaluacionNueva} from './Models.js';

let evaluacion = new EvaluacionNueva();

$(document).on("click", "#pushPregunta", () => {    
    evaluacion.pushPregunta();
});

$(document).on("click", "#popPregunta", () => {
    evaluacion.popPregunta();
});

$(document).on("click",".pushAlt", (evt) => {    
    let idPregunta = evt.target.id.substring(7);
    let pregunta = evaluacion.preguntas.find(pregunta => pregunta.id == idPregunta);    
    pregunta.pushAlternativa();
//    console.log(pregunta.alternativas);
});

$(document).on("click",".popAlt", (evt) => {
    let idPregunta = evt.target.id.substring(6);
    let pregunta = evaluacion.preguntas.find(pregunta => pregunta.id == idPregunta);
    pregunta.popAlternativa();
//    console.log(pregunta.alternativas);
});

$(document).on("click", "#save", () => {
    evaluacion.title = document.getElementById("title").value;
    evaluacion.preguntas.forEach(((pregunta) => {
        pregunta.contenido = pregunta.textElement.value;
        pregunta.alternativas.forEach((alternativa) => {
            alternativa.contenido = alternativa.textElement.value;
            if(alternativa.boolElement.checked) 
                alternativa.isSelected = true;
            else 
                alternativa.isSelected = false;
        });
    }));
    post("/WAColegioSanJose/EvaluacionController",evaluacion);
});

function post(url, evaluacion) {
    fetch(url,{
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(evaluacion)
    }).then((response) => {
        if(response.status === 200) {
            alert("Se guardo la evaluación");
            redirect("/WAColegioSanJose/ListarEvaluacionesController");
        }            
        else
            alert("Ocurrio un error inesperado, intente en unos instantes");
    });
}

function redirect(url) {
    window.location.href = url;
}






