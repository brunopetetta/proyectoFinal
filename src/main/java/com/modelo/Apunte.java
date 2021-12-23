/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 *
 * @author PC
 */
public class Apunte {
    private int id;
    private String nombre;
    private String descripcion;
    private String carrera;
    private String materia;
    private int cantPaginas;
    private Boolean upload;
    
    public Apunte(){
    }

    public Apunte(int id, String nombre, String descripcion, String carrera, String materia, int cantPaginas, Boolean upload) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.carrera = carrera;
        this.materia = materia;
        this.cantPaginas = cantPaginas;
        this.upload = upload;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getCantPaginas() {
        return cantPaginas;
    }

    public void setCantPaginas(int cantPaginas) {
        this.cantPaginas = cantPaginas;
    }

    public Boolean getUpload() {
        return upload;
    }

    public void setUpload(Boolean upload) {
        this.upload = upload;
    }
    
    
}
