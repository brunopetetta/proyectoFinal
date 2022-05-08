
package com.modelo;

public class Carrito {
    int item;
    int idApunte;
    String nombre;
    String descripcion;    
    int cantidadCopias;
    Double precioCompra;    
    String anillado;
    String tipoImpresion;
    Double subtotal;
    int paginaDesde;
    int paginaHasta;
    int cantPaginas;
    String observaciones;
    
    public Carrito(){
    }

    public Carrito(int item, int idApunte, String nombre, String descripcion, int cantidadCopias, Double precioCompra, String anillado, String tipoImpresion, Double subtotal, int paginaDesde, int paginaHasta, int cantPaginas, String observaciones) {
        this.item = item;
        this.idApunte = idApunte;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadCopias = cantidadCopias;
        this.precioCompra = precioCompra;
        this.anillado = anillado;
        this.tipoImpresion = tipoImpresion;
        this.subtotal = subtotal;
        this.paginaDesde = paginaDesde;
        this.paginaHasta = paginaHasta;
        this.cantPaginas = cantPaginas;
        this.observaciones = observaciones;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getIdApunte() {
        return idApunte;
    }

    public void setIdApunte(int idApunte) {
        this.idApunte = idApunte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadCopias() {
        return cantidadCopias;
    }

    public void setCantidadCopias(int cantidadCopias) {
        this.cantidadCopias = cantidadCopias;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getAnillado() {
        return anillado;
    }

    public void setAnillado(String anillado) {
        this.anillado = anillado;
    }

    public String getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(String tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public int getPaginaDesde() {
        return paginaDesde;
    }

    public void setPaginaDesde(int paginaDesde) {
        this.paginaDesde = paginaDesde;
    }

    public int getPaginaHasta() {
        return paginaHasta;
    }

    public void setPaginaHasta(int paginaHasta) {
        this.paginaHasta = paginaHasta;
    }

    public int getCantPaginas() {
        return cantPaginas;
    }

    public void setCantPaginas(int cantPaginas) {
        this.cantPaginas = cantPaginas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
