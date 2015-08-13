<?php
class usuario{
	private $nombreUsuario;
	private $contrasenia;
	private $estado;
	public function __construct($args)
	{
	//echo "constructur";
	$this->nombreUsuario=$args[0];
	$this->contrasenia=$args[1];
	$this->estado=$args[2];
	}
	public function getNombreUsuario()
	{
			return $this->nombreUsuario;
	}
	public function setNombreUsuario($value)
	{
		$this->nombreUsuario=$value;
	}
	
		public function getContrasenia()
	{
			return $this->contrasenia;
	}
	public function setContrasenia($value)
	{
		$this->contrasenia=$value;
	}
	
	public function getEstado()
	{
			return $this->estado;
	}
	public function setEstado($value)
	{
		$this->estado=$value;
	}
	
}