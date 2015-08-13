<?php
class Inquilinos
{
	//se declaran los atributos de la clase, que son los atributos del cliente
	private $id;
        private $nombre;
        private $valor;

    public static function getAll() 
		{
			$obj_inquilino=new Query();
			$obj_inquilino->executeQuery("select nombre, valor from premios"); // ejecuta la consulta para traer al cliente
			return $obj_inquilino->fetchAll(); // retorna todos los muebles
		}

	function Inquilinos($nro=0) // declara el constructor, si trae el numero de cliente lo busca , si no, trae todos los clientes
	{
		if ($nro!=0)
		{
			$obj_inquilino=new Query();
			$result=$obj_inquilino->executeQuery("select nombre, valor from premios"); // ejecuta la consulta para traer al cliente 
			$row=MySQLi_fetch_array($result);
			$this->id=$row['nombre'];
			$this->nombre=$row['valor'];
			
                        
			
		}
	}
		
		// metodos que devuelven valores
	
   
	
	
		
	
}
?>