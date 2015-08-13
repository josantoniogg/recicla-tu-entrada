<?php    

 session_start();

if(isset($_POST['respuesta'])){ //check if form was submitted
//$input = $_POST['respuesta']; //get input text

    $enlace = mysql_connect('localhost', 'proyec34', 'G95l9xv7dS');
    mysql_select_db('proyec34_recicladora');


    
     
        $nick = $_SESSION["txtNombre"];   
        
        $input = $_POST['respuesta'];
        $query = mysql_query("SELECT valor FROM premios WHERE nombre = '$input'"); 
              
               if (mysql_num_rows($query)) //Si se encontraron datos en la búsqueda 
                    { 
                   $row = mysql_fetch_array($query); 
                   $pff= $row["valor"];
    
   
mysql_query("UPDATE usuarios SET Puntos = Puntos - '$pff'  WHERE nick = '$nick' && Puntos >= '$pff'");
//printf ("Registros actualizados: %d\n", mysql_affected_rows());
//mysql_query("COMMIT");
        
        
        
        
    }  
    header ("Location: usuarioENG.php"); 
  
}else{

header ("Location: usuarioENG.php"); 
}






?>