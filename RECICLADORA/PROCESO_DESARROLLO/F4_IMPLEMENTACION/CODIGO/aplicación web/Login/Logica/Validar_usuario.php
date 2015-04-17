<?php
session_start();
  $hostname_localhost ="localhost";  //nuestro servidor
$database_localhost ="recicladora";//Nombre de nuestra base de datos
$username_localhost ="root";//Nombre de usuario de nuestra base de datos (yo utilizo el valor por defecto)
$password_localhost ="";//Contraseña de nuestra base de datos (yo utilizo por defecto)
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)//Conexión a nuestro servidor mysql
or
trigger_error(mysql_error(),E_USER_ERROR); //mensaaje de error si no se puede conectar
mysql_select_db($database_localhost, $localhost);//seleccion de la base de datos con la qu se desea trabajar
 $_olis;

   require_once("LimpiarTexto.php");
    $usuario = $_POST["login"];   
    $password = $_POST["password"];
    $limpia = new LimpiarTexto();
    $usuario=$limpia->limpiar($usuario);
    $password=$limpia->limpiar($password);
    $Admin ="admin";
    $Empleado = "usuario";

//echo $usuario; comente
   
     $_olis=("select nick,password,tipo from usuarios where nick='$usuario'");
	  $query = mysql_query($_olis);
        if($row=mysql_fetch_array($query)){
           if($row["password"]== $password && $row["tipo"]==$Admin){
           $_SESSION['nick']==$usuario;
               header("location: ../Vistas/Panel_admin.html");
			   echo 'molina';
               exit;
  
           }else if($row["password"]==$password && $row["tipo"]==$Empleado){
                  header("location:../index4.html");
                  exit;
               }
               }else{
			   header("location: ../index4.html");
                echo "existen errores";
                }
               
       /* }else{
            printf("usuario incorrecto: %s\n", $mysqli->connect_error);
            ?>
            <script language="javascript">
                alert("Nombre de usuario es incorrecto");
              // location.href="../Login.php"
            </script>
            <?
            }*/ 
  //  mysqli_free_result($datos);  
   // mysqli_close(); comente
?>