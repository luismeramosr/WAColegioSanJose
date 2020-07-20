$(document).on("click", "#btnagregarcurso", function (){
   $("#txtnomcurso").val("");
   $("#txtcredcurso").val("");
   $("#hddidcurso").val("0");
   $("#modalcurso").modal('show');    
});
$(document).on("click", ".btnactualizarcurso", function (){
   $("#txtnomcurso").val($(this).attr("data-nomcurso"));
   $("#txtcredcurso").val($(this).attr("data-credcurso"));
   $("#hddidcurso").val($(this).attr("data-codcurso"));
   $("#modalcurso").modal('show');    
});

$(document).on("click", "#btnregistrarcurso", function (){
    if($("#hddidcurso").val() === "0"){
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '/WAEjercicioMariaBD/RegistrarCursoAjaxController',
            data: JSON.stringify({
                nomcurso: $("#txtnomcurso").val(),
                credito: $("#txtcredcurso").val()
            }),
            success: function(data, textStatus, jqXHR){
                if(data){
                    alert("Se registró el curso correctamente.");
                }else{
                    alert("Ocurrió un error en la base de datos.");
                }
            }            
        });        
    }else{
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '/WAEjercicioMariaBD/RegistrarCursoAjaxController',
            data: JSON.stringify({
                idcurso: $("#hddidcurso").val(),
                nomcurso: $("#txtnomcurso").val(),
                credito: $("#txtcredcurso").val()
            }),
            success: function(data, textStatus, jqXHR){
                if(data){
                    alert("Se registró el curso correctamente.");
                }else{
                    alert("Ocurrió un error en la base de datos.");
                }
            }            
        });                 
    }
    $("#modalcurso").modal("hide");
});


