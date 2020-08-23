import {Evaluacion_Calificable} from './Models.js';

let evaluacion = new Evaluacion_Calificable();
let tiempoTranscurrido = 0;
evaluacion.id = sessionStorage.getItem("idEvaluacion");

$(document).ready(() => {
    $.ajax({
       type: 'GET',
       url: "/WAColegioSanJose/EditarEvaluacionController?idEvaluacion="+evaluacion.id,
       dataType: 'json',
       success: (ev, textStatus, jqXHR) => {
            if(ev.habilitado) {
            let data = JSON.parse(ev.data);   
            $("#title").html(data.title);  
            evaluacion.curso = ev.Curso;
            let clock = setInterval(() => {                
                let tiempoRestante = ev.duracion--;
                tiempoTranscurrido++;                               
                let resultado = formatTime(tiempoRestante);
                $("#tiempoRestante").html(resultado);
                if (tiempoRestante<0) {
                    clearInterval(clock);
                    $("#tiempoRestante").html("TERMINÓ EL TIEMPO");
                    setTimeout(() => {
                        guardarRespuestas();
                        post("/WAColegioSanJose/ResolverEvaluacionController", formatEvaluacion(evaluacion));
                        redirect("ListarEvaluacionesResueltasController");
                    }, 2000);
                }
            },1000);
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
    }
    });
});

$(document).on("click","#enviarEvaluacion", () => {
    guardarRespuestas();
    post("/WAColegioSanJose/ResolverEvaluacionController", formatEvaluacion(evaluacion));
    redirect("ListarEvaluacionesResueltasController");
});

function guardarRespuestas() {
    evaluacion.preguntas.forEach(((pregunta) => {
        pregunta.alternativas.forEach((alternativa) => {
            if(alternativa.boolElement.checked) 
                alternativa.isSelected = true;
            else 
                alternativa.isSelected = false;
        });
    }));
}

function formatEvaluacion(evaluacion) {   

    let formattedEv = {
        "idEvaluacion": evaluacion.id,
        "seccion": sessionStorage.getItem("Seccion"),
        "curso": evaluacion.curso,
        "tiempo": formatTime(tiempoTranscurrido),
        "alumno": document.getElementById("idAlumno").value,
        "respuestas": []
    };
        
    evaluacion.preguntas.forEach((pregunta)=> {
        pregunta.alternativas.forEach((alternativa) => {
            formattedEv.respuestas.push(alternativa.isSelected);
        });
    });   

    return formattedEv;
}

function post(url, evaluacion) {
    fetch(url,{
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(evaluacion)
    }).then((response) => {
        if(response.status === 200) {
//            redirect("/WAColegioSanJose/ListarEvaluacionesResueltasController");            
        }            
        else
            alert("Ocurrio un error inesperado, intente en unos instantes");
    });
}

function formatTime(tiempo) {
    let h =  Math.floor((tiempo % (3600 * 24)) / 3600);
    let m = Math.floor((tiempo % 3600) / 60);
    let s = Math.floor(tiempo % 60);
    let horas = h<10? `0${h}` : h;
    let minutos = m<10? `0${m}` : m;
    let segundos = s<10? `0${s}` : s;
    return horas+":"+minutos+":"+segundos;
}

function redirect(url) {
    window.location.href = url;
}