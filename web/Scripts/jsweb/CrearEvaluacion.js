import {Evaluacion_Nueva} from './Models.js';

let evaluacion = new Evaluacion_Nueva();

$(document).ready(() => {
    //Agregando selector de duracion
    $('#duracionPicker').datetimepicker({
        timepicker: true,
        datepicker: false,
        format: 'H:i',
        hours12: false,
        value: '00:00',
        step: 15        
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
});

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
    post(`/WAColegioSanJose/CrearEvaluacionController`, formatEvaluacion(evaluacion));
});

function formatEvaluacion(evaluacion) {
    
    let tempDuracion = evaluacion.duracion.split(":");    
    let duracion = tempDuracion[0]*3600 + tempDuracion[1]*60;
    
    let limite = getTimeStampFromString(evaluacion.limiteEntrega);

    let formattedEv = {
        "curso": evaluacion.curso,
        "duracion": duracion,
        "limiteEntrega": limite
    };    
    
    let data = {};
    
    data["title"] = evaluacion.title;
    data["curso"] = evaluacion.curso;
    data["preguntasCount"] = evaluacion.preguntasCount;
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

function getTimeStampFromString(datetime) {
    return Date.parse(datetime)/1000;
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






