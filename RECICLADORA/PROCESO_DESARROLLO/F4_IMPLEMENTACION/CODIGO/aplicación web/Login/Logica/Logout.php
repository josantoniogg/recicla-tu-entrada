<?php 
 //Crear sesi�n
 session_start();
 //Vaciar sesi�n
 $_SESSION = array();
 //Destruir Sesi�n
 session_destroy();
 exit(0);
 //Redireccionar a login.php
 header("location: ../index4.html");
?>