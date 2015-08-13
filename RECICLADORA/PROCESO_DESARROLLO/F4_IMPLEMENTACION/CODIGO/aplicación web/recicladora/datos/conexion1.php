<?php
define('HOST','localhost');
define('DB','proyec34_recicladora');
define('USER','proyec34');
define('PASSWORD','G95l9xv7dS');
class conexion{
	private $conex;
	private $result;
	public function conectar()
	{
	if(!isset($this->conex)){
	  $this->conex=(mysql_connect(HOST,USER,PASSWORD))
		or die (mysql_error());
	 mysql_select_db(DB,$this->conex)
		or die (mysql_error());
	}
	}
	public function desconectar()
	{
		mysql_close($this->conex);
	}
	public function ejecutar($sentenciaSQL)
	{
		$this->conectar();
		
		$this->result=mysql_query($sentenciaSQL,
				$this->conex);
		if(!$this->result){
			echo 'MySQL Error:'+mysql_error();
		}
	}
	public function obtenerRegistroUnico($consulta)
	{
		$this->ejecutar($consulta);
		$dato=$this->result;
		$dato=mysql_fetch_array($dato,MYSQL_NUM);
		return $dato[0];
	}
	public function obtenerRegistro($consulta)
	{
		$this->ejecutar($consulta);
		$dato=$this->result;
		$datos=mysql_fetch_array($dato,MYSQL_NUM);
		echo count($datos);
		return $datos;
	}
	
	public function obtenerRegistros($consulta)
	{
		$this->ejecutar($consulta);
		$dato=$this->result;
		$registros=array();
		$i=0;
		while($datos=mysql_fetch_array($dato,MYSQL_NUM))
		{
		for($j=0;$j<count($datos);$j++)
			{
				$registros[$i][$j]=$datos[$j];
			}
		$i++;	
		}
		return $registros;
	}
	
}

