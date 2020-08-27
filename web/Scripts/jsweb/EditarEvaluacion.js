import {Evaluacion_Editable} from './Models.js';

let evaluacion = new Evaluacion_Editable();
let idEvaluacion = sessionStorage.getItem("idEvaluacion");

$(document).ready(() => {
    //Agregando selector de duracion
    $('#duracionPicker').datetimepicker({
        timepicker: true,
        datepicker: false,
        format: 'H:i',
        hours12: false,
        value: '00:00',
        step: 15,
        scroll: false
    });
    //Agregando selector de hora y fecha
    $('#limitePicker').datetimepicker({
        timepicker: true,
        datepicker: true,
        format: 'Y/m/d H:i',
        hours12: false,
        value: 'now',
        step: 15        
    });
    
    $.ajax({
       type: 'GET',
       url: "/WAColegioSanJose/EditarEvaluacionController?idEvaluacion="+idEvaluacion,
       dataType: 'json',
       success: (ev, textStatus, jqXHR) => {
            let data = JSON.parse(ev.data);   
            $("#title").val(data.title);
            let duracionHoras =  parseInt(ev.duracion/3600);
            let duracionMinutos = parseInt((ev.duracion%3600)/60);
            $("#duracionPicker").val(duracionHoras+":"+duracionMinutos);
            evaluacion.id = ev.idEvaluacion;
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

$(document).on("click", "#pushPregunta", () => {    
    evaluacion.pushNuevaPregunta();
});

$(document).on("click", "#popPregunta", () => {
    evaluacion.popPregunta();
});

$(document).on("click",".pushAlt", (evt) => {    
    let idPregunta = evt.target.id.substring(7);
    let pregunta = evaluacion.preguntas.find(pregunta => pregunta.id == idPregunta);    
    pregunta.pushAlternativa();
});

$(document).on("click",".popAlt", (evt) => {
    let idPregunta = evt.target.id.substring(6);
    let pregunta = evaluacion.preguntas.find(pregunta => pregunta.id == idPregunta);
    pregunta.popAlternativa();
});

$(document).on("click", "#save", () => {
    evaluacion.title = document.getElementById("title").value;
    let nombreCurso = document.getElementById("cursoSelector").value;
    evaluacion.curso = document.getElementById(nombreCurso).value;
    evaluacion.duracion = document.getElementById("duracionPicker").value;
    evaluacion.limiteEntrega = document.getElementById("limitePicker").value;
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
    post(`/WAColegioSanJose/EditarEvaluacionController`, formatEvaluacion(evaluacion));
});

function formatEvaluacion(evaluacion) {
    
    let tempDuracion = evaluacion.duracion.split(":");    
    let duracion = tempDuracion[0]*3600 + tempDuracion[1]*60;
    
    let limite = getTimestampFromString(evaluacion.limiteEntrega);

    let formattedEv = {
        "id": evaluacion.id,
        "seccion": sessionStorage.getItem("seccion"),
        "curso": evaluacion.curso,
        "duracion": duracion,
        "limiteEntrega": limite
    };    
    
    let data = {};
    
    data["title"] = evaluacion.title;
    data["curso"] = evaluacion.curso;
    data["preguntas"] = evaluacion.preguntas.map(({
        id,
        numero,
        contenido
    }) => {
        return {
            "id": id, 
            "numero": numero, 
            "contenido": contenido
        };
    }); 
        
    data["preguntas"].forEach((pregunta, index) => {
        pregunta["alternativas"] = evaluacion.preguntas[index]["alternativas"].map(({
            id, 
            contenido,
            pregunta,
            isSelected
        }) => {
            return {
                "id": id,
                "contenido": contenido,
                "pregunta": pregunta,
                "isSelected": isSelected
            };
        });
    });
    
    formattedEv["data"] = data;

    return formattedEv;
}

function getTimestampFromString(datetime) {
    return Date.parse(datetime)/1000;
}

function getDatetimeFromTimestamp(timestamp){
  let date = new Date(timestamp * 1000);
  let year = date.getFullYear();
  let months = ['01','02','03','04','05','06','07','08','09','10','11','12'];  
  let month = months[date.getMonth()];
  let day = date.getDay();
  let hour = date.getHours();
  let min = date.getMinutes();
  let time = day + '/' + month + '/' + year + ' ' + hour + ':' + min;
  return time;
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