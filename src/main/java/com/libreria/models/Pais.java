package com.libreria.models;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pais {
    
    private LongProperty id;
    private StringProperty cod;
    private StringProperty nombre;
    
    public Pais(Long id, String cod, String nombre) {
        this.id = new SimpleLongProperty(id);
        this.cod = new SimpleStringProperty(cod);
        this.nombre = new SimpleStringProperty(nombre);
    }
    
    public Pais(String cod, String nombre) {
        this.id = new SimpleLongProperty();
        this.cod = new SimpleStringProperty(cod);
        this.nombre = new SimpleStringProperty(nombre);
    }

    public Long getId() {
        return id.get();
    }

    public String getCod() {
        return cod.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public void setCod(String cod) {
        this.cod.set(cod);
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
