/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 *
 * @author PC
 */
public class PrecioFotocopia {
    private String fechaDesdePrecio;
    private Double valorFotocopia;
    
    public PrecioFotocopia(){
    }

    public PrecioFotocopia(String fechaDesdePrecio, Double valorFotocopia) {
        this.fechaDesdePrecio = fechaDesdePrecio;
        this.valorFotocopia = valorFotocopia;
    }

    public String getFechaDesdePrecio() {
        return fechaDesdePrecio;
    }

    public void setFechaDesdePrecio(String fechaDesdePrecio) {
        this.fechaDesdePrecio = fechaDesdePrecio;
    }

    public Double getValorFotocopia() {
        return valorFotocopia;
    }

    public void setValorFotocopia(Double valorFotocopia) {
        this.valorFotocopia = valorFotocopia;
    }
    
    
}
