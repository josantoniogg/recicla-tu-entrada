<?PHP
$hostname_localhost ="localhost";  //nuestro servidor
$database_localhost ="proyec34_recicladora";//Nombre de nuestra base de datos
$username_localhost ="proyec34";//Nombre de usuario de nuestra base de datos (yo utilizo el valor por defecto)
$password_localhost ="G95l9xv7dS";//Contraseña de nuestra base de datos (yo utilizo por defecto)
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)//Conexión a nuestro servidor mysql
or
trigger_error(mysql_error(),E_USER_ERROR); //mensaaje de error si no se puede conectar
mysql_select_db($database_localhost, $localhost);//seleccion de la base de datos con la qu se desea trabajar
 $_olis;
//variables que almacenan los valores que enviamos por nuestra app, (observar que se llaman igual en nuestra app y aqui)
$usuario = $_POST['usuario'];
$idmaterial=$_POST['idmaterial'];
$Cantidad=$_POST['Cantidad'];


$_olis ="select idusuario from usuarios WHERE  nick='$usuario'";
 $query = mysql_query($_olis);

$query_search = "insert into materiales_usuarios(idmaterial,idusuario,Cantidad,Fechahora) values 
('".$idmaterial."','".$query."','".$Cantidad."',now())";

$query_exec = mysql_query($query_search) or die(mysql_error());//Ejecuta la sentencia sql.
	
?>