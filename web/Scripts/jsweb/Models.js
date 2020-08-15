export class Evaluacion {
    constructor() {
        this.preguntas = [];
        this.preguntasCount;
        this.element;
        this.title;
    }
}

class Pregunta {
    constructor() {
        this.id;
        this.numero;
        this.contenido;
        this.evaluacion;
        this.alternativas = [];
        this.element;
        this.template = ``; // agrega un template para mostrar preguntas        
    }
    
    pushToDOM() {
        let newPregunta = document.createElement('div');
        newPregunta.id = this.id;    
        newPregunta.innerHTML = this.template;
        this.evaluacion.appendChild(newPregunta);
    }
}

class Alternativa {
    constructor() {
        this.id;
        this.numero;
        this.contenido;
        this.pregunta;
        this.container;
        this.isSelected;
        this.template = ``; // agrega el template
        this.element;
    }
    
    pushToDOM() {
        let newAlternativa = document.createElement('div');        
        newAlternativa.id = this.id;     
        newAlternativa.innerHTML = this.template;
        this.container.appendChild(newAlternativa);
    }
    
}

export class EvaluacionNueva extends Evaluacion {
    constructor() {
        super();
        this.preguntasCount = 1;
        this.preguntas.push(new PreguntaNueva(1));
        this.element = document.getElementById("Evaluacion");
    }

    pushPregunta() {
        this.preguntasCount++;
        let newID = `p${this.preguntasCount}`;
        this.preguntas.push(new PreguntaNueva(this.preguntasCount));
    }

    popPregunta() {
        if (this.preguntasCount >= 1) {
            this.preguntasCount--;
            let deleted = this.preguntas.pop();
            let pregunta = document.getElementById(deleted.id);
            this.element.removeChild(pregunta);
        } else
            alert("No hay preguntas para eliminar.");
    }
}

class PreguntaNueva extends Pregunta {
    constructor(numero) {
        super();
        this.id = `p${numero}`;
        this.numero = numero;
        this.contenido = "";
        this.alternativasCount=3;
        this.evaluacion = document.getElementById("Evaluacion");
        this.template = `    
        <div class="card">
            <div class="card-body">
                <span class="card-title">Pregunta Nro ${this.numero}</span>
                <div class="input-group">
                    <textarea class="form-control" placeholder="Inserte Pregunta" 
                    aria-label="With textarea" id="${this.id}data"></textarea>
                </div><br/>
                <div id="alternativas${this.id}">
                    
                </div><br/>
                <button id="pushAlt${this.id}" class="btn btn-warning pushAlt">Agregar alternativa</button>
                <button id="popAlt${this.id}" class="btn btn-warning popAlt">Quitar alternativa</button>
            </div>            
        </div>`;
        this.pushToDOM();                
        this.textElement = document.getElementById(`${this.id}data`);
        this.alternativasContainer = document.getElementById(`alternativas${this.id}`);
        this.alternativas = [
            new AlternativaNueva(1, this.id),
            new AlternativaNueva(2, this.id),
            new AlternativaNueva(3, this.id)
        ];
    }     
    
    pushAlternativa() {
        this.alternativasCount++;
        this.alternativas.push(new AlternativaNueva(this.alternativasCount, this.id));
    }
    
    popAlternativa() {
        if(this.alternativasCount >=1){
            this.alternativasCount--;
            let deleted = this.alternativas.pop();
            let alternativa = document.getElementById(deleted.id);
            this.alternativasContainer.removeChild(alternativa);
        } else
            alert("No hay alternativas para eliminar.");        
    }
}

class AlternativaNueva extends Alternativa { 
    constructor(numero, idPregunta) {
        super();
        this.id = `${idPregunta}alt${numero}`;
        this.numero = numero;
        this.contenido = "";
        this.pregunta = idPregunta;
        this.isSelected = false; 
        this.container = document.getElementById(`alternativas${idPregunta}`);        
        this.template = `<div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text">
                                    <input type="radio" aria-label="Radio button" id="${this.id}bool" 
                                    name="group${this.pregunta}" class="alt">
                                </div>
                            </div>
                            <input type="text" class="form-control" aria-label="Text input"
                            id="${this.id}text">
                        </div></br>`;
        this.pushToDOM({"id": this.id,"container": this.container});                
        this.textElement = document.getElementById(`${this.id}text`); 
        this.boolElement = document.getElementById(`${this.id}bool`);
    }    
}
