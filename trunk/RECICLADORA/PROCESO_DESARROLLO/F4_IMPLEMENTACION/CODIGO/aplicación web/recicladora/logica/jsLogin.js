function validar(){
	var params={};
	params.miUsuario=$('#txtUsuario').val();
	params.miClave=$('#txtContrasenia').val();
	$.ajax({
				type: "POST",
				url: "../login/index.php",
				data: params,
				async: false,
				success:function(data){
					
					
				}
			});
}