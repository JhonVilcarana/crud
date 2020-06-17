$(document).ready(function(){
listaral();
listares(0)
limpiar();

});
$("#boton").click(function(){
	var es= $("#es").val();	
	var apelnombres= $("#apelnombres").val();
	var correo= $("#correo").val();
	var telefono = $("#telefono").val();
	var id = $("#id").val();
	if(id==0){
		$.post( "hc", {es:es,apelnombres:apelnombres,correo:correo,telefono:telefono, opc:3}).done(function(data){
			limpiar();
			listares(0);
			listaral();
			});
	}else{
		bootbox.confirm("Desea Modificar?", function(result) {
		if(result){
		 	bootbox.alert("Registro Modificado Correctamente...!", function() {		
			$.post( "hc", {ides :es, apelnombres:apelnombres, correo:correo, telefono:telefono, idal:id, opc:6}).done(function(data){
				$("#id").val(0);
				limpiar();
				listaral(0)
				listarapel();			
			});
		 	});
		}else{
	    	bootbox.alert("El registro no se Modifico...!");
	    	limpiar();
			listaral(0)
			listarapel();
	    }});		
	}
});
function listares(x){
	var i, c =1;
	$("#es").empty().append('<option selected="selected" value="test">Seleccionar Escuela</option>')
		$.get("hc", {opc : "1"}, 
		function(data) {
	    var d = JSON.parse(data);
		for (i = 0; i < d.length; i++) {
			if (x == d[i].idescuela) {
				$("#es").append(
						"<option selected='selected' value='" + d[i].idescuela + "'>"
								+ d[i].nombrecat + "</option>");
			} else {
				$("#es").append(
						"<option value='" + d[i].idescuela + "'>"
								+ d[i].nombrecat + "</option>");
			}
		}
	});	
}

function listaral(){
	var i, c =1;
	$.get("hc",{opc:"2"},function(data){	
		var d = JSON.parse(data);
		$('#tablita tbody').empty();
		for(i=0;i<d.length;i++){
			$("#tablita tbody").append("<tr><td style='color:blue'>"+c+"</td><td>"+d[i].nombrecat+"</td><td>"+d[i].apelnombres+"</td><td>"+d[i].correo+"</td><td>"+d[i].telefono+
					
			"</td><td><a href='#' style='color:green' onclick='modificar("+d[i].idalumno+")'><i class='far fa-edit'></i></a></td><td><a href='#' style='color:red' onclick='eliminar("+d[i].idalumno+")'><i class='far fa-trash-alt'></i></a></td></tr>")
			c++;
		}
	});
}

function eliminar(id){	
	bootbox.confirm("Desea Eliminar?", function(result) {
    if(result){
    	bootbox.alert("Registro Eliminado Correctamente...!", function() {
    		$.get("hc",{id:id,opc:5},function(data){
     			limpiar();
    			listares(0);
    			listaral();
		});
    	});
		 
    }else{
    	bootbox.alert("El registro no se Elimino...!")
    }});
}
function modificar(id){	
	$.post("hc",{id:id,opc:4},function(data){
		var x = JSON.parse(data);
		$("#apelnombres").val(x[0].apelnombres);
		$("#correo").val(x[0].correo);
		$("#telefono").val(x[0].telefono);
		$("#id").val(x[0].idalumno);		
		listares(x[0].idescuela);
	});
}
function limpiar(){
	$("#apelnombres").val("");
	$("#correo").val("");
	$("#telefono").val("");
	$("#apelnombres").focus();
}
