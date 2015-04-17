<?php 
 //Crear sesión
 session_start();
 //Vaciar sesión
 $_SESSION = array();
 //Destruir Sesión
 session_destroy();
 exit(0);
 //Redireccionar a login.php
 header("location: ../index4.html");
?>