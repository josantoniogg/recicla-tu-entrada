<h2><?php echo $view->label ?></h2>
<form name ="frmNuevo" id="frmNuevo" method="POST" action="index.php">
    <input type="hidden" name="id" id="id" value="<?php print $view->inquilino->getId(); ?>">
    <div>
        <label>Nombre</label>
        <input type="text" name="txtNombre" id="txtNombre" value = "<?php print $view->inquilino->getNombre(); ?>">
	<label>APaterno</label>
        <input type="text" name="txtAPaterno" id="txtAPaterno" value = "<?php print $view->inquilino->getAPaterno(); ?>">
	<label>AMaterno</label>
        <input type="text" name="txtAMaterno" id="txtAMaterno" value = "<?php print $view->inquilino->getAMaterno(); ?>">
	<label>Edad</label>
        <input type="text" name="txtEdad" id="txtEdad" value = "<?php print $view->inquilino->getEdad(); ?>">
	<label>Telefono</label>
        <input type="text" name="txtTelefono" id="txtTelefono" value = "<?php print $view->inquilino->getTelefono(); ?>">
	<label>Correo</label>
        <input type="text" name="txtCorreo" id="txtCorreo" value = "<?php print $view->inquilino->getCorreo(); ?>">
    </div>
       <div class="buttonsBar">
        <input id="cancel" type="button" value ="Cancelar" />
        <input id="submit" type="submit" name="submit" value ="Guardar" />
    </div>
</form>
