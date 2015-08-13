<!DOCTYPE html>
<html lang="en">

<head>

    
    <title>RECICLA TU ENTRADA</title>
    
    
    <link rel="shortcut icon" href="img/im.png" type="image/png" />

    

</head>

<body>
<?php
session_start();

if(isset($_SESSION["txtNombre"])){ 
    
    echo "Bienvenido: ".$_SESSION["txtNombre"];
}




?>
    
    
     <script src="js/bootstrap.min.js"></script>
    
    
    <script

</body>

</html>