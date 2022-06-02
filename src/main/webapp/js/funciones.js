
$(document).ready(function () {
    $("tr #deleteItem").click(function (e) {
        e.preventDefault();
        var ida = $(this).parent().find('#item').val();
        swal({
            title: "Desea eliminar el apunte?",
            text: "Una vez eliminado, deberá agregar nuevamente",
            icon: "warning",
            buttons: true,
            dangerMode: true
        }).then((willDelete) => {
            if (willDelete) {
                eliminar(ida);
                swal("Registro borrado con Éxito", {
                    icon: "success",
                }).then((willDelete) => {
                    if (willDelete) {
                        parent.location.href = "./Controlador?accion=Carrito";
                    }
                });
            }
        });
    });
    function eliminar(ida) {
        var url = "./Controlador?accion=BorrarApunteCarrito&id=" + ida;
        $.ajax({
            type: 'POST',
            url: url,
            async: true,
            success: function (r) {
            }
        });
    }
    
    $("tr #PagDesde").click(function (e) {
        var ida = $(this).parent().find('#itemPagDesde').val();
        var cantidad = $(this).parent().find('#PagDesde').val();
        var url = "./Controlador?accion=ActualizarPaginaDesde";
        console.log(ida, cantidad);
        $.ajax({
            type: 'POST',
            url: url,
            data: "id=" + ida + "&cantidad=" + cantidad,
            success: function (data, textStatus, jqXHR) {
                parent.location.href = "./Controlador?accion=Carrito";
            }
        });
    });
    
    $("tr #PagHasta").click(function (e) {
        var ida = $(this).parent().find('#itemPagHasta').val();
        var cantidad = $(this).parent().find('#PagHasta').val();
        var url = "./Controlador?accion=ActualizarPaginaHasta";
        console.log(ida, cantidad);
        $.ajax({
            type: 'POST',
            url: url,
            data: "id=" + ida + "&cantidad=" + cantidad,
            success: function (data, textStatus, jqXHR) {
                parent.location.href = "./Controlador?accion=Carrito";
            }
        });
    });
    
    
    $("tr #CantCopias").click(function (e) {
        var ida = $(this).parent().find('#itemCantCo').val();
        var cantidad = $(this).parent().find('#CantCopias').val();
        var url = "./Controlador?accion=ActualizarCantidadCopias";
        console.log(ida, cantidad);
        $.ajax({
            type: 'POST',
            url: url,
            data: "id=" + ida + "&cantidad=" + cantidad,
            success: function (data, textStatus, jqXHR) {
                parent.location.href = "./Controlador?accion=Carrito";
            }
        });
    });
    
    $("tr #anillado").click(function (e) {
        var ida = $(this).parent().find('#itemAnillado').val();
        var valorchk = $(this).parent().find('#anillado').prop('checked');
        var va = 0;
        if(valorchk == true){
            va = 1;
        }
        var url = "./Controlador?accion=ActualizarAnillado";
        $.ajax({
            type: 'POST',
            url: url,
            data: "id=" + ida + "&anillado=" + va,
            success: function (data, textStatus, jqXHR) {
                parent.location.href = "./Controlador?accion=Carrito";
            }
        });
    });
    
     $("tr #tipoImpresion").click(function (e) {
        var ida = $(this).parent().find('#itemTipoImpresion').val();
        var valorTI = $(this).parent().find('#tipoImpresion').prop('checked');
        var va = 0;
        if(valorTI == true){
            va = 1;
        }
        var url = "./Controlador?accion=ActualizarTipoImpresion";
        $.ajax({
            type: 'POST',
            url: url,
            data: "id=" + ida + "&ti=" + va,
            success: function (data, textStatus, jqXHR) {
                parent.location.href = "./Controlador?accion=Carrito";
            }
        });
    });
    
    $("tr #observaciones").change(function(e) {
        var ida = $(this).parent().find('#itemObs').val();
        var obs = $(this).parent().find('#observaciones').val();
        var url = "./Controlador?accion=ActualizarObservaciones";
        $.ajax({
            type: 'POST',
            url: url,
            data: "id=" + ida + "&obs=" + obs,
            success: function (data, textStatus, jqXHR) {
                parent.location.href = "./Controlador?accion=Carrito";
            }
        });
    });
    
    $("tr #deleteApunte").click(function (e) {
        e.preventDefault();
        var ida2 = $(this).parent().find('#apu').val();
        swal({
            title: "Desea eliminar?",
            text: "Una vez eliminado, perderá todos los datos del registro",
            icon: "warning",
            buttons: true,
            dangerMode: true
        }).then((willDelete) => {
            if (willDelete) {
                eliminarApunte(ida2);
                swal("Registro borrado con éxito", {
                    icon: "success",
                }).then((willDelete) => {
                    if (willDelete) {
                        parent.location.href = "./Controlador?accion=ApuntesAdmin";
                    }
                });
            }
        });
    });
    function eliminarApunte(ida2) {
        var url = "./Controlador?accion=eliminarApunteBD&id=" + ida2;
        $.ajax({
            type: 'POST',
            url: url,
            async: true,
            success: function (r) {
            }
        });
    }
    
});

