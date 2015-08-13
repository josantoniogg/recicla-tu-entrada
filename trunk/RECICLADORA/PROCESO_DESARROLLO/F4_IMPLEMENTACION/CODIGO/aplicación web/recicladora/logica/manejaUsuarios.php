<?php

include_once("../datos/usuario.php");
include_once("../datos/conexion1.php");
class manejadorUsuario{
	private $usuario;
	private $conex;
	public function __construct(){
		$this->conex=new conexion();
	}
	public function recibeDatos(){
		$this->usuario = new usuario(array(
		$_POST["txtNombre"],
		$_POST["txtContrasenia"],
		0
		));   	
	 // echo "recibeDAtos";	
	}
	private function armarInstruccionConsultarAcceso()
	{/*
	$instruccion="call sp_validarUsuario('";
	$instruccion.=$this->usuario->getNombreUsuario();
	$instruccion.="','";
	$instruccion.=$this->usuario->getContrasenia();
	$instruccion.="','Contrasenia');";*/
	$instruccion="select count(nick) as total, tipo from usuarios where  ";
	$instruccion.=" nick= '";
	$instruccion.=$this->usuario->getNombreUsuario();
	$instruccion.="' and password='";
	$instruccion.=$this->usuario->getContrasenia();
	$instruccion.="';";
	  return $instruccion;
	}
	public function consultarAcceso()
	{
	$sql=$this->armarInstruccionConsultarAcceso();
	//echo "<br>".$sql;
	$coincidenciasUsuario=$this->conex->obtenerRegistro($sql);
	$this->conex->desconectar();
	 
	return $coincidenciasUsuario;
	}
}
?>
