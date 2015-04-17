<?php
$hostname_localhost ="localhost";  //nuestro servidor
$database_localhost ="recicladora";//Nombre de nuestra base de datos
$username_localhost ="root";//Nombre de usuario de nuestra base de datos (yo utilizo el valor por defecto)
$password_localhost ="";//Contraseña de nuestra base de datos (yo utilizo por defecto)
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)//Conexión a nuestro servidor mysql
or
trigger_error(mysql_error(),E_USER_ERROR); //mensaaje de error si no se puede conectar
mysql_select_db($database_localhost, $localhost);//seleccion de la base de datos con la qu se desea trabajar
$_olis;
        $_olis="select * from usuarios";
		
			  $query = mysql_query($_olis);
    
?>

 <script type="text/javascript" language="javascript" src="../js/jslistadonombre.js"></script>


    <table cellpadding="0" cellspacing="0" border="0" class="display" id="tabla_lista_productos">
                <thead>
                    <tr>
                        <th >id</th><!--Estado-->
                        <th >Nombre</th>
                       
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th></th>
                        <th></th>
                       
                     
                    </tr>
                </tfoot>
                  <tbody>
                    <?php
                       
     
                   while($reg=mysql_fetch_array($query))
                   {
                               echo '<tr>';
                               echo '<td >'.mb_convert_encoding($reg['idusuario'], "UTF-8").'</td>';
                               echo '<td>'.mb_convert_encoding($reg['nombre'], "UTF-8").'</td>';
                             
                               echo '</tr>';
                     
                        }
                    ?>  
                <tbody>
            </table>