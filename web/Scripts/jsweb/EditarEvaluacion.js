import {Evaluacion} from './Models.js';

let evaluacion = new Evaluacion();
let idEvaluacion = sessionStorage.getItem("idEvaluacion");

$(document).ready(() => {
    $.ajax({
       type: 'GET',
       url: "/WAColegioSanJose/EditarEvaluacionController?idEvaluacion="+idEvaluacion,
       dataType: 'json',
       success: (ev, textStatus, jqXHR) => {
            let data = JSON.parse(ev.data);            
            data.preguntas.forEach((pregunta, i) => {
                evaluacion.pushPregunta(pregunta);
                pregunta.alternativas.forEach((alternativa) => {
                    evaluacion.preguntas[i].pushAlternativa(alternativa);
                });
            });
            
            evaluacion.preguntas.forEach((pregunta, i) => {
                pregunta.pushToDOM();
                pregunta.alternativas.forEach((alternativa) => {
                    alternativa.pushToDOM();
                });
            });
       }
    });
});