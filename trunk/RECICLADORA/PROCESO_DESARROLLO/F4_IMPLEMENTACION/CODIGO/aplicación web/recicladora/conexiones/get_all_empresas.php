<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';

 
// connecting to db
$db = new DB_CONNECT();
$nick=$_POST['nick']; 




// get all products from products table
$result = mysql_query("SELECT u.idusuario as idusuario,u.nick as nick ,u.nombre as nombre,sum(mu.cantidad)as total ,m.nombre as nombre_del_material ,m.imagen as imagen FROM usuarios u 
inner join materiales_usuarios mu on mu.idusuario= u.idusuario inner join materiales m on m.idmaterial=mu.idmaterial 
where nick='pepe' group by nombre_del_material") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["usuarios"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["idusuario"] = $row["idusuario"];
        $product["nick"] = $row["nick"];
        $product["nombre"] = $row["nombre"];
        $product["total"] = $row["total"];
        $product["nombre_del_material"] = $row["nombre_del_material"];
        $product["imagen"] = $row["imagen"];
        
        array_push($response["usuarios"], $product);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>