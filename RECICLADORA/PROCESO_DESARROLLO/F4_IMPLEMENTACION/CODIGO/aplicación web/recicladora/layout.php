<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- incluyo la libreria jQuery -->
    <script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
    <!-- incluyo el archivo que tiene mis funciones javascript -->
    <script type="text/javascript" src="../logica/jsInquilinos.js"></script>
    <!-- incluyo el framework css , blueprint. -->
    <link rel="stylesheet" type="text/css" href="../css/screen.css" />
    <!-- incluyo mis estilos css -->
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
    
    
    
    
    
    
    
    
    
</head>
<body>
    <div id ="block"></div>
    <div class="container">
        <h1></h1>
        <div id="popupbox"></div>
        <div id="content">
            <?php include_once ($view->contentTemplate);?>
        </div>
    </div>
</body>
</html>