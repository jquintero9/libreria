package com.libreria.models;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Autor {
    
    private LongProperty id;
    private StringProperty nombres;
    private StringProperty apellidos;
    private LongProperty pais;
    private StringProperty nombrePais;
    
    public Autor(Long id, String nombres, String apellidos, Long pais) {
        this.id = new SimpleLongProperty(id);
        this.nombres = new SimpleStringProperty(nombres);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.pais = new SimpleLongProperty(pais);
        this.nombrePais = new SimpleStringProperty();
    }
    
    public Autor(String nombres, String apellidos, Long pais) {
        this.id = new SimpleLongProperty();
        this.nombres = new SimpleStringProperty(nombres);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.pais = new SimpleLongProperty(pais);
        this.nombrePais = new SimpleStringProperty();
    }

    public Long getId() {
        return id.get();
    }

    public String getNombres() {
        return nombres.get();
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public Long getPais() {
        return pais.get();
    }
    
    public String getNombrePais() {
        return nombrePais.get();
    }

    public void setId(Long id) {
        this.id = new SimpleLongProperty(id);
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres);
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public void setPais(Long pais) {
        this.pais.set(pais);
    }
    
    public void setNombrePais(String nombrePais) {
        this.nombrePais.set(nombrePais);
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + getId() + ", nombres=" + getNombres() + ", apellidos=" + getApellidos() + ", pais=" + getNombrePais() + '}';
    }
}
