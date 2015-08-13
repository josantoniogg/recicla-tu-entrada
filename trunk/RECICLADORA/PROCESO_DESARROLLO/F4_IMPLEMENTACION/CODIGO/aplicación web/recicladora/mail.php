<?php
if (isset($_POST['nombre']) && isset($_POST['email'])) {
	$fecha=date("d-m-Y");
	$hora=date("H:i:s");
	$destino="developmentsolutions@proyectositi.com";
	$asunto= "Comentario";
	$desde='From: '.$_POST['email'];
	$comentario="
	\n
	Nombre: $_POST[nombre]\n 
	Email: $_POST[email]\n
        Tel: $_POST[telefono]\n
	Comentario: $_POST[mensaje]\n
	Enviado: $fecha a las $hora \n
	\n
	";
	$boleano = mail($destino, $asunto, $comentario,$desde);
	var_dump($boleano);
}
else{
	echo "listisho";
}
	echo "<script language=Javascript> location.href=\"contact.html\"; </script>";
?>