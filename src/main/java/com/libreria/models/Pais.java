package com.libreria.models;

public class Pais {
    
    private Long id;
    private String cod;
    private String nombre;
    
    public Pais(Long id, String cod, String nombre) {
        this.id = id;
        this.cod = cod;
        this.nombre = nombre;
    }
    
    public Pais(String cod, String nombre) {
        id = null;
        this.cod = cod;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getCod() {
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
