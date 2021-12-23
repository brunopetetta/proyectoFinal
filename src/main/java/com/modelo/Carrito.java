/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 *
 * @author PC
 */
public class Carrito {
    int item;
    int idApunte;
    String nombre;
    String descripcion;    
    int cantidadCopias;
    Double precioCompra;    
    Boolean anillado;
    Boolean tipoImpresion;
    Double subtotal;
    int paginaDesde;
    int paginaHasta;
    String observaciones;
    
    public Carrito(){
    }

    public Carrito(int item, int idApunte, String nombre, String descripcion, int cantidadCopias, Double precioCompra, Boolean anillado, Boolean tipoImpresion, Double subtotal, int paginaDesde, int paginaHasta, String observaciones) {
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

    public Boolean getAnillado() {
        return anillado;
    }

    public void setAnillado(Boolean anillado) {
        this.anillado = anillado;
    }

    public Boolean getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(Boolean tipoImpresion) {
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
