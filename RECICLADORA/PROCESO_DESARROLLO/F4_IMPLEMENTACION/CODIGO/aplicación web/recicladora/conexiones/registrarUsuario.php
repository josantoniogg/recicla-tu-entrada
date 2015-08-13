<?php include ('functions.php');
$cc=$_GET['cc'];
$nombre=$_GET['nombre'];
$apellido=$_GET['apellido'];
$direccion=$_GET['direccion'];
$telefono=$_GET['telefono'];


ejecutarSQLCommand("INSERT INTO  `tablaprueba` (
`cc` ,
`nombre` ,
`apellido` ,
`direccion`,
`telefono`
)
VALUES (
'$cc' ,
'$nombre' ,
'$apellido' ,
'$direccion',
'$telefono')

 ON DUPLICATE KEY UPDATE `nombre`= '$nombre',
`apellido`='$apellido' ,
`direccion`='$direccion',
`telefono`='$telefono'
;");

 ?>
