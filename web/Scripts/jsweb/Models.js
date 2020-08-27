export class Evaluacion_Calificable {
    constructor() {
        this.id;
        this.preguntas = [];
        this.element = document.getElementById("Evaluacion");
        this.title;
        this.duracion;
        this.limiteEntrega;
    }
    
    pushPregunta({id, numero, contenido}) {
        this.preguntas.push(Object.assign(new Pregunta_Calificable(), {id, numero, contenido}));
    }    
}

class Pregunta_Calificable {
    constructor() {
        this.id;
        this.numero;
        this.contenido;
        this.evaluacion = document.getElementById("Evaluacion");
        this.alternativas = [];
        this.textElement;                      
    }   
    
    pushToDOM() {
        let newPregunta = document.createElement('div');
        newPregunta.id = this.id;    
        newPregunta.innerHTML = `    
        <div class="card">
            <div class="card-body">
                <span class="card-title"><strong>Pregunta Nro ${this.numero}:</strong> ${this.contenido}</span>
                <div class="mt-3" id="alternativas${this.id}">                    
                </div>
            </div>            
        </div>`;
        this.evaluacion.appendChild(newPregunta);
        this.textElement = document.getElementById(`${this.id}data`);
    }
    
    pushAlternativa({id, numero, contenido, pregunta}) {
        this.alternativas.push(Object.assign(new Alternativa_Calificable(), {id, numero, contenido, pregunta}));
    }   
}

class Alternativa_Calificable {
    constructor() {
        this.id;
        this.numero;
        this.contenido;
        this.pregunta;
        this.isSelected;
        this.textElement;
        this.boolElement;
    }
    
    pushToDOM() {        
        let newAlternativa = document.createElement('div');        
        newAlternativa.id = this.id;     
        newAlternativa.innerHTML = `
        <div class="input-group">
            <div class="input-group-prepend">
                <div class="input-group-text">
                    <input type="radio" aria-label="Radio button" id="${this.id}bool" 
                    name="group${this.pregunta}" class="alt">
                </div>
            </div>
            <input type="text" class="form-control" aria-label="Text input"
            id="${this.id}text" value="${this.contenido}" disabled>
        </div></br>`;
        let container = document.getElementById(`alternativas${this.pregunta}`);
        container.appendChild(newAlternativa);
        this.boolElement = document.getElementById(`${this.id}bool`);
        this.textElement = document.getElementById(`${this.id}text`); 
    }    
}

export class Evaluacion_Editable {
    constructor() {
        this.id;
        this.preguntas = [];
        this.element = document.getElementById("Evaluacion");
        this.title;
        this.duracion;
        this.limiteEntrega;
    }
    
    pushPregunta({id, numero, contenido}) {
        this.preguntas.push(Object.assign(new Pregunta_Editable(), {id, numero, contenido}));
    }
    
    popPregunta() {
        if(this.preguntas.length>=1){
            let deleted = this.preguntas.pop();
            this.element.removeChild(document.getElementById(deleted.id));
        }else {
            alert("No hay preguntas que eliminar.");
        }
    }
    
    pushNuevaPregunta() {
        this.preguntas.push(new Pregunta_Nueva(this.preguntas.length+1));
    }  
    
}

class Pregunta_Editable {
    constructor() {
        this.id;
        this.numero;
        this.contenido;
        this.evaluacion = document.getElementById("Evaluacion");
        this.alternativas = [];
        this.textElement;                      
    }   
    
    pushToDOM() {
        let newPregunta = document.createElement('div');
        newPregunta.id = this.id;    
        newPregunta.innerHTML = `    
        <div class="card">
            <div class="card-body">
                <span class="card-title">Pregunta Nro ${this.numero}</span>
                <div class="input-group">
                    <textarea class="form-control" placeholder="Inserte Pregunta" 
                    aria-label="With textarea" id="${this.id}data">${this.contenido}</textarea>
                </div><br/>
                <div id="alternativas${this.id}">
                    
                </div><br/>
                <button id="pushAlt${this.id}" class="btn btn-warning pushAlt">Agregar alternativa</button>
                <button id="popAlt${this.id}" class="btn btn-warning popAlt">Quitar alternativa</button>
            </div>            
        </div>`;
        this.evaluacion.appendChild(newPregunta);
        this.textElement = document.getElementById(`${this.id}data`);
    }
    
    pushAlternativa(alternativaData) {
        if(alternativaData){
            this.alternativas.push(Object.assign(new Alternativa_Editable(), alternativaData));
        }else{
            this.alternativas.push(new Alternativa_Nueva(this.alternativas.length+1, this.id));
        }
    }
    
    popAlternativa() {
        if(this.alternativas.length>=1) {
            let deleted = this.alternativas.pop();
            let pregunta = document.getElementById(`alternativas${this.id}`);
            pregunta.removeChild(document.getElementById(deleted.id));
        }else 
            alert("No hay alternativas para eliminar.");
    }
    
}

class Alternativa_Editable {
    constructor() {
        this.id;
        this.numero;
        this.contenido;
        this.pregunta;
        this.isSelected;
        this.textElement;
        this.boolElement;
    }
    
    pushToDOM() {        
        let newAlternativa = document.createElement('div');        
        newAlternativa.id = this.id;     
        newAlternativa.innerHTML = `
        <div class="input-group">
            <div class="input-group-prepend">
                <div class="input-group-text">
                    <input type="radio" aria-label="Radio button" id="${this.id}bool" 
                    name="group${this.pregunta}" class="alt">
                </div>
            </div>
            <input type="text" class="form-control" aria-label="Text input"
            id="${this.id}text" value="${this.contenido}">
        </div></br>`;
        let container = document.getElementById(`alternativas${this.pregunta}`);
        container.appendChild(newAlternativa);
        this.boolElement = document.getElementById(`${this.id}bool`);
        this.boolElement.checked = this.isSelected;
        this.textElement = document.getElementById(`${this.id}text`); 
    }
    
}

export class Evaluacion_Nueva {
    constructor() {
        this.preguntas = [];
        this.preguntas.push(new Pregunta_Nueva(1));
        this.element = document.getElementById("Evaluacion");
        this.title;
        this.duracion;
        this.limiteEntrega;
    }

    pushPregunta() {
        this.preguntas.push(new Pregunta_Nueva(this.preguntas.length+1));
    }

    popPregunta() {
        if (this.preguntas.length >= 1) {
            let deleted = this.preguntas.pop();
            let pregunta = document.getElementById(deleted.id);
            this.element.removeChild(pregunta);
        } else
            alert("No hay preguntas para eliminar.");
    }
}

class Pregunta_Nueva {
    constructor(numero) {
        this.id = `p${numero}`;
        this.numero = numero;
        this.contenido = "";
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
            new Alternativa_Nueva(1, this.id),
            new Alternativa_Nueva(2, this.id),
            new Alternativa_Nueva(3, this.id)
        ];
    }     
    
    pushAlternativa() {
        this.alternativas.push(new Alternativa_Nueva(this.alternativas.length+1, this.id));
    }
    
    popAlternativa() {
        if(this.alternativas.length >=1){
            let deleted = this.alternativas.pop();
            let alternativa = document.getElementById(deleted.id);
            this.alternativasContainer.removeChild(alternativa);
        } else
            alert("No hay alternativas para eliminar.");        
    }
    
    pushToDOM() {
        let newPregunta = document.createElement('div');
        newPregunta.id = this.id;    
        newPregunta.innerHTML = this.template;
        this.evaluacion.appendChild(newPregunta);
    }
}

class Alternativa_Nueva { 
    constructor(numero, idPregunta) {
        this.id = `${idPregunta}alt${numero}`;
        this.numero = numero;
        this.contenido = "";
        this.pregunta = idPregunta;
        this.isSelected = false; 
        this.container = document.getElementById(`alternativas${idPregunta}`);        
        this.template = `
        <div class="input-group">
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
    
    pushToDOM() {
        let newAlternativa = document.createElement('div');        
        newAlternativa.id = this.id;     
        newAlternativa.innerHTML = this.template;
        this.container.appendChild(newAlternativa);
    }
}
