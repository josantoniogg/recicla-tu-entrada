

<?php  
function Conectarse()  
{ if (!($link=mysql_connect("localhost","proyec34","G95l9xv7dS")))  
   { echo "Error conectando a la base de datos.";  
      exit(); }  
   if (!mysql_select_db("proyec34_recicladora",$link))  
   {  echo "Error seleccionando la base de datos.";  
      exit(); }  
   return $link;  
}  
    $txtNombre = $_POST["txtNombre"]; 
    $txtApa = $_POST["txtApa"];
    $txtAma = $_POST["txtAma"];
    $txtSex = $_POST["txtSex"];
    $txtNac = $_POST["txtNac"];
    $txtNick = $_POST["txtNick"];
    $txtPass = $_POST["txtPass"];

   $link = Conectarse();

mysql_query("INSERT INTO usuarios (nombre, apellidoPaterno, apellidoMaterno, Sexo, fechaNacimiento, nick, password, tipo, Puntos) VALUES ('$txtNombre', '$txtApa', '$txtAma', '$txtSex','$txtNac','$txtNick','$txtPass','usuario','0')",$link);  

 echo "<script language=Javascript> location.href='../index.html'; </script>"; 
die(); 
  
   ?>
   
