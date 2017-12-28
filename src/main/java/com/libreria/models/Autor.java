package com.libreria.models;

public class Autor {
    
    private Long id;
    private String nombres;
    private String apellidos;
    private Long pais;
    
    public Autor(Long id, String nombres, String apellidos, Long pais) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.pais = pais;
    }
    
    public Autor(String nombres, String apellidos, Long pais) {
        id = null;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Long getPais() {
        return pais;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setPais(Long pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", pais=" + pais + '}';
    }
}
