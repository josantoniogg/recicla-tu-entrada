<?php
   if(!isset($SERVER)){
    include_once("config.php");
   }
   class Conexion{
   
   
    public function getConexion(){
        $cnn = new mysqli(SERVER, USER, PASSWORD, DB);
        if($cnn -> connect_error){
           // header('Location: incidencia.php');
            exit;
        }else{
            return $cnn;
        }
	}
		
    public function getClose(){
            $cnn->close;
    }
   }
   
?>