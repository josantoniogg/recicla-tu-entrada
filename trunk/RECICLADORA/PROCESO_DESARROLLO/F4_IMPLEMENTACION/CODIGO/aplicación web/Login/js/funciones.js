
$(document).ready(function(){
    verlistado()
   verlistado1()
    //CARGAMOS EL ARCHIVO QUE NOS LISTA LOS REGISTROS, CUANDO EL DOCUMENTO ESTA LISTO


})
function verlistado(){ //FUNCION PARA MOSTRAR EL LISTADO EN EL INDEX POR JQUERY
              var randomnumber=Math.random()*11;
            $.post("../Vistas/ListaContenedor.php", {
                randomnumber:randomnumber
            }, function(data){
              $("#contenido").html(data);
              
            });
}


function verlistado1(){ //FUNCION PARA MOSTRAR EL LISTADO EN EL INDEX POR JQUERY
              var randomnumber=Math.random()*11;
            $.post("../Vistas/ListaNombre.php", {
                randomnumber:randomnumber
            }, function(data){
              $("#contenido1").html(data);
              
            });
}