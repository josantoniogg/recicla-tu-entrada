<div class="bar">
  <!--  <a id="new" class="button" href="javascript:void(0);">Nuevo</a>
    <a id="new" class="button" href="../PInquilinos/index.php">Papelera</a> -->
    <br><br>
</div>
<table id="example" class="display" cellspacing="0" width="50%">
    <thead>
        <tr>
            <th bgcolor="#6b96cb">Nombre</th>
            <th bgcolor="#6b96cb">Valor en Puntos</th>
	    
        </tr>
    </thead>
    <tbody>
        <?php foreach ($view->misInquilinos as $columna):?>
            <tr>
                <td><?php echo $columna['nombre'];?></td>
                <td><?php echo $columna['valor'];?></td>
		
               <!-- <td><a class="edit" href="javascript:void(0);" data-id="<?php echo $columna['idInquilino'];?>">Editar</a></td>
                <td><a class="delete" href="javascript:void(0);" data-id="<?php echo $columna['idInquilino'];?>">Borrar</a></td> -->
            </tr>
        <?php endforeach; ?>
    </tbody>
</table> 