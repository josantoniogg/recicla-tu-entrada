<?php
 
class funciones_BD {
 
    private $db;
 
    // constructor

    function __construct() {
        require_once 'connectbd.php';
        // connecting to database

        $this->db = new DB_Connect();
        $this->db->connect();

    }
 
    // destructor
    function __destruct() {
 
    }
 
    /**
     * agregar nuevo usuario
     */
    public function adduser($username, $password) {

    $result = mysql_query("INSERT INTO usuarios(nick,password) VALUES('$username', '$password')");
        // check for successful store

        if ($result) {

            return true;

        } else {

            return false;
        }

    }
    
    
   
 
 
     /**
     * Verificar si el usuario ya existe por el username
     */

    public function datis($username,$password) {

        $result = mysql_query("SELECT Sexo from usuarios WHERE nick = '$username' and password='$password'" );

       //$num_rows = mysql_num_rows($result); //numero de filas retornadas

        if ($result=="masculino") {

            
            return 0;
            
        } else {
            
            return 1;
        }
    }
 
   
	public function login($user,$passw){
	$result=mysql_query("SELECT COUNT(*) FROM usuarios WHERE nick='$user' AND password='$passw' "); 
	$count = mysql_fetch_row($result);

	

		if ($count[0]==0){

		return true;

		}else{

		return false;

		}
	}
  
}
 
?>