package com.libreria.models;


public class Libro {

    private Long id;
    private String titulo;
    private int paginas;
    private int ejemplares;
    private Genero genero;
    private Autor autor;

    public Libro(Long id, String titulo, int paginas, int ejemplares, Genero genero, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.paginas = paginas;
        this.ejemplares = ejemplares;
        this.genero = genero;
        this.autor = autor;
    }

    public Libro(String titulo, int paginas, int ejemplares, Genero genero, Autor autor) {
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

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setAutor(Autor autor) {
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

    public Genero getGenero() {
        return genero;
    }

    public Autor getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", paginas=" + paginas + ", ejemplares=" + ejemplares + ", genero=" + genero + ", autor=" + autor + '}';
    }
}
