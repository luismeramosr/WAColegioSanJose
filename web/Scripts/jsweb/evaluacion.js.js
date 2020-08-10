/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function addComponent(id) {
    
    var div=document.getElementById(id);
    div.innerhtml=`<pregunta></pregunta>`;
    
    
    var component = Vue.component(`pregunta`, {
       template: `
            <p>dad121as2d1a2ds1</p>
        `
    });
    
    var app = new Vue({
        el: `#${id}`,
        data: {
          msg: 'ABCDEASDFA'
        }
    })
}

$(document).on("click", "#btnpregunta", function() {    
    addComponent("pregunta1");
    console.log("asdsdsddad");
});