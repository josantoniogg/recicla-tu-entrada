<?php
session_start();
include_once("../logica/manejaUsuarios.php");

class controladorAccesoUsuario{
	public function consultarAcceso(){
		$cu=new manejadorUsuario();
		//echo "despues new";
		$cu->recibeDatos();
		//echo "despues cu";
	//echo $_POST["txtNombre"];
	//echo $_POST["txtContrasenia"];
		$usuario=$cu->consultarAcceso();
        
       $_SESSION["txtNombre"]= $_POST["txtNombre"];
        
		if($usuario[1]== "admin"){
		$_SESSION["intentosfallidos"]=0;
			    echo "<script type='text/javascript'>";
				echo "document.location='../x.php'";
				echo "</script>";
        
        
        }else if($usuario[0]==1){
				$_SESSION["intentosfallidos"]=0;
			    echo "<script type='text/javascript'>";
				echo "document.location='../usuarioENG.php'";
				echo "</script>";
            
			//echo "IF";
		}else {
			$_SESSION["usuarioMascota"]=$usuario[0];
		        echo "<script type='text/javascript'>";
				echo "document.location='../indexENG.html'";
				echo "</script>";
			//echo "ELSE=>".$usuario[0];
		}
		 		
	}
}

$cUsuario= new controladorAccesoUsuario();
$cUsuario->consultarAcceso();
?>

