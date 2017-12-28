package com.libreria.models;


public class Libro {

    private Long id;
    private String titulo;
    private int paginas;
    private int ejemplares;
    private Long genero;
    private Long autor;

    public Libro(Long id, String titulo, int paginas, int ejemplares, Long genero, Long autor) {
        this.id = id;
        this.titulo = titulo;
        this.paginas = paginas;
        this.ejemplares = ejemplares;
        this.genero = genero;
        this.autor = autor;
    }

    public Libro(String titulo, int paginas, int ejemplares, Long genero, Long autor) {
        this.titulo = titulo;
        this.paginas = paginas;
        this.ejemplares = ejemplares;
        this.genero = genero;
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public void setGenero(Long genero) {
        this.genero = genero;
    }

    public void setAutor(Long autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPaginas() {
        return paginas;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public Long getGenero() {
        return genero;
    }

    public Long getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", paginas=" + paginas + ", ejemplares=" + ejemplares + ", genero=" + genero + ", autor=" + autor + '}';
    }
}
