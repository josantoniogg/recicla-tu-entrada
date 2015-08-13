<?php  
function Conectarse()  
{  
   if (!($link=mysql_connect("localhost","proyec34","G95l9xv7dS")))  
   {  
      echo "Error conectando a la base de datos.";  
      exit();  
   }  
   if (!mysql_select_db("proyec34_recicladora",$link))  
   {  
      echo "Error seleccionando la base de datos.";  
      exit();  
   }  
   return $link;  
}  
?>  
