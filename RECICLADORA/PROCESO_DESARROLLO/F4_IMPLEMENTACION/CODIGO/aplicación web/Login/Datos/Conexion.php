<?php
if(!isset($SERVER)){
		include("../Datos/config.php");	
	
}	
	class Conexion{
		public function getConexion(){
			
			$cnn = new mysqli(SERVER,USER,PASSWORD,DB);
			
			if($cnn -> connect_error){
			
				header('Location: ../Datos/incidencia.php');
				echo 'hola';
				exit;
			}else{
				return $cnn;
			echo 'hola';
			}
		}
		
		public function getClose(){
			$cnn ->close;
		}
	}
	?>