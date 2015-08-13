<?php session_start();?>
<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Hector izazaga">

    <title>RECICLA TU ENTRADA</title>
    
    
    <link rel="shortcut icon" href="img/im.png" type="image/png" />

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="css/table.css" type="text/css"/>

    <!-- Custom CSS -->
    <link href="css/business-casual.css" rel="stylesheet">
    
    <link href="css/xxx.CSS" rel="stylesheet">

    <!-- Fonts -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">
    

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/business-casual.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
    
    
    
   

    <div class="brand"><img src="img/logo.png" width="60" class="logo">Recicla Tu Entrada</div>
    <div class="address-bar"></div>

    
    
    
    
    
    
    
    <!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                <a class="navbar-brand" href="index.html">Recicla Tu Entrada</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="indexENG.html">Home</a>
                    </li>
                    <li>
                        <a href="aboutENG.html">About us</a>
                    </li>
                   <!-- <li>
                        <a href="blog.html">Blog</a>
                    </li> -->
                    <li>
                        <a href="contactENG.html">Contact us</a>
                    </li>
                     <li>
                        <a href="logout.php">Logout</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <div class="container">

        <div class="row">
            <div class="box">
                
            
                
                
                
                
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">
                        <strong>TRADE AREA</strong>
                    </h2>
                    <hr>
                </div>
                <div class="col-md-6">
                    <div class="use">  <p class="us">  
<?php
if(isset($_SESSION["txtNombre"])){ 
    
     echo "<h7>Welcome: ".$_SESSION["txtNombre"] ."</h7>";
    
    
    echo "<br>"; 
    
    
     
//tomamos los datos del archivo conexion.php  
include_once ("datos/conx.php");
 
//tomamos los datos del archivo conexion.php  
  
$nick = $_SESSION["txtNombre"];    
    
$link = Conectarse();  
//se envia la consulta  
$result = mysql_query("SELECT Puntos FROM usuarios WHERE  
nick = '$nick'", $link);  
//se despliega el resultado  
echo " <table>";  
echo "<tr>";  
    echo "<th>User</th>";
echo "<th>Points</th>";  
 
echo "</tr>";  
while ($row = mysql_fetch_row($result)){   
    echo "<tr>"; 
    echo "<td>$nick</td>";
    echo "<td>$row[0]</td>";  
    
      
    echo "</tr>";  
}  
echo "</table>";  

    
    
    
    
    
    
    
    
    
    
}else{
    
 echo "<script language=Javascript> location.href=\"indexENG.html\"; </script>"; 
die();    
}



















?></p><div>
                        
   <br>                     
        <form method="POST" action="updateENG.php"> 
<input type="radio" value="Boleto de Transporte" name="respuesta"><img src="img/bus26.png" width="50px" height="50px"><b> Bus Ticket</b><br> 
<input type="radio" value="Producto del Comedor" name="respuesta"><img src="img/coffee10.png" width="50px" height="50px"><b> Product Diner</b><br> 
<input type="radio" value="Boleto para el cine" name="respuesta"><img src="img/ticket6.png" width="50px" height="50px"><b> Movie Ticket</b><br><br>
<input type="submit" value="Trade" name="bu" class="myButton">
            
</form>  



                
                        
                        
                        
                        </div></div>
                </div>
                <div >
                    <p>

<?php

 
include_once ("datos/Inquilinos.php");// incluyo las clases a ser usadas
include_once ("datos/Query.php");
include_once ("datos/Conexion.php");
include_once ("datos/funciones.php");
$action='index';


if(isset($_POST['action']))
{$action=$_POST['action'];}


$view= new stdClass(); // creo una clase standard para contener la vista
$view->disableLayout=false;// marca si usa o no el layout , si no lo usa imprime directamente el template




// para no utilizar un framework y simplificar las cosas uso este switch, la idea
// es que puedan apreciar facilmente cuales son las operaciones que se realizan
//echo "trae".$action;
//echo "demo".$_SESSION;
switch ($action)
{
    case 'index':
        $view->misInquilinos=Inquilinos::getAll(); // trae todos los muebles
        $view->contentTemplate="vwListarENG.php"; // seteo el template que se va a mostrar
        break;
    
    default :
}

// si esta deshabilitado el layout solo imprime el template
if ($view->disableLayout==true)
{include_once ($view->contentTemplate);}
else
{include_once ('layout.php');} // el layout incluye el template adentro}
?>



</p><br>
                    <p></p>
                    <p></p>
                    
                    
                    
                    
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

        <div class="row">
     <!--      <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Our
                        <strong>Team</strong>
                    </h2>
                    <hr>
                </div>
                <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="http://placehold.it/750x450" alt="">
                    <h3>John Smith
                        <small>Job Title</small>
                    </h3>
                </div>
                <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="http://placehold.it/750x450" alt="">
                    <h3>John Smith
                        <small>Job Title</small>
                    </h3>
                </div>
                <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="http://placehold.it/750x450" alt="">
                    <h3>John Smith
                        <small>Job Title</small>
                    </h3>
                </div>
                <div class="clearfix"></div>
            </div> -->
        </div>

    </div>
    <!-- /.container --> 

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="socialw fadeInDown">  
  
    
<h23>© DERECHOS RESERVADOS | D-SOLUTIONS</h23>
    <!-- Facebook !-->
    <a href="https://www.facebook.com/ReciclaTuEntrada" target="_blank">       
    <div class="social"><img src="http://i.imgur.com/PY2EPRW.png"/></div>
    </a>
    <!-- Facebook !-->

    <!-- Twitter !-->
    <a href="https://twitter.com/Recicla_tu_entr" target="_blank">        
    <div class="social"> <img src="http://i.imgur.com/ny0pyqt.png"/> </div>
    </a>
    <!-- Twitter !-->
                
                    
<div class="idioma">
                    
                   <a href="usuarioENG.php" target="_self">        
     <img src="img/America.png"/ class="america"> 
    </a>
    <a href="usuario.php" target="_self">        
     <img src="img/Mexico.png"/ class="america"> 
    </a>
                    </div>  
   
    </div>
<!-- Social Icons End !-->
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    
    <script>
    
    
    $(document).ready(function() {
    $('#example').DataTable();
} );
    
    
    
    
    </script>
    
    <script src="js/jquery.dataTables.min.js"></script>
    
    <script src="js/jquery-1.11.1.min.js"></script>

</body>

</html>