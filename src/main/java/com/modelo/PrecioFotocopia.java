package com.modelo;

public class PrecioFotocopia {
    int idPrecio;
    String fechaDesdePrecio;
    double valorFotocopia;

   
    public PrecioFotocopia(){
    }

    public PrecioFotocopia(int idPrecio, String fechaDesdePrecio, double valorFotocopia) {
        this.idPrecio = idPrecio;
        this.fechaDesdePrecio = fechaDesdePrecio;
        this.valorFotocopia = valorFotocopia;
    }

    
     public int getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }
    
    
    public String getFechaDesdePrecio() {
        return fechaDesdePrecio;
    }

    public void setFechaDesdePrecio(String fechaDesdePrecio) {
        this.fechaDesdePrecio = fechaDesdePrecio;
    }

    public double getValorFotocopia() {
        return valorFotocopia;
    }

    public void setValorFotocopia(double valorFotocopia) {
        this.valorFotocopia = valorFotocopia;
    }
    
    
}
