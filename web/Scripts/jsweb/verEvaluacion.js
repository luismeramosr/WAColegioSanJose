/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import {Evaluacion} from './Models.js';

let evaluacion = new Evaluacion();

fetch("/WAColegioSanJose/VerEvaluacionController").then((response)=>{
    response.json((evaluacion)=>{
        console.log(evaluacion);
    })
})




//let obj = {
//    "id": "id1",
//    "nombre": "lala",
//    "atributos": [
//        {"nombreAtributo": "nom1", "tipoAtributo": "t1"},
//        {"nombreAtributo": "nom2", "tipoAtributo": "t2"},
//        {"nombreAtributo": "nom3", "tipoAtributo": "t3"}
//    ]
//}
//
//function deconstruct({id,nombre}) {
//    return params[0]
//}
//
//let obj2 = params[0];
//console.log(obj2);
