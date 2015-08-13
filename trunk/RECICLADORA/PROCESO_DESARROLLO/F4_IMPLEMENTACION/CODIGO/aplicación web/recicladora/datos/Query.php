<?php
    class Query{
    private $data;
     function executeQuery($Transact_SQL)
    {
       $oCnn= new Conexion();
       $link= $oCnn->getConexion();
	   $this->data=$link->query($Transact_SQL);
	   $link->close(); 
     return $this->data;
    }
    function fetchAll()
    {
        $numRows= $this->data->num_rows;
        $rows= array();
        if($numRows>0){
            while($row =$this-> data-> fetch_array()){
                $rows[] = $row;
            }
        }
        return $rows;
    }
}
	?>
   <!-- function getConexion(){
     $ocnn=new Conexion();
     $link=$ocnn->getConexion();
     $this->cnn=$link;
     return $this->cnn;
    }
    } -->

