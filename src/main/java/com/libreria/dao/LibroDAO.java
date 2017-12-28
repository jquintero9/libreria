package com.libreria.dao;

import com.libreria.db.DBException;
import com.libreria.models.Libro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibroDAO extends DAO<Libro, Long> {
    
    private final String SQL_CREATE = "INSERT INTO libros(titulo, paginas, ejemplares, genero, autor) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_ALL = "SELECT * FROM libros";
    private final String SQL_GET = "SELECT * FROM libros WHERE id=?";
    private final String SQL_UPDATE = "UPDATE libros SET titulo=?, paginas=?, ejemplares=?, genero=?, autor=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM libros WHERE id=?";
    
    public LibroDAO() {
        super();
        sentences.put("create", SQL_CREATE);
        sentences.put("all", SQL_ALL);
        sentences.put("update", SQL_UPDATE);
        sentences.put("get", SQL_GET);
        sentences.put("delete", SQL_DELETE);
    }

    @Override
    public void setCreateStatement(PreparedStatement stm, Libro object) throws DBException {
        try {
            stm.setString(1, object.getTitulo());
            stm.setInt(2, object.getPaginas());
            stm.setInt(3, object.getEjemplares());
            stm.setLong(4, object.getGenero());
            stm.setLong(5, object.getAutor());
        } catch (SQLException ex) {
            throw new DBException(DBException.FAIL_STM + " | " + ex.getMessage());
        }
    }

    @Override
    public void setGetStatement(PreparedStatement stm, Long pk) throws DBException {
        try {
            stm.setLong(1, pk);
        } catch (SQLException ex) {
            throw new DBException(DBException.FAIL_STM + " | " + ex.getMessage());
        }
    }

    @Override
    public void setUpdateStatement(PreparedStatement stm, Libro object) throws DBException {
        try {
            stm.setString(1, object.getTitulo());
            stm.setInt(2, object.getPaginas());
            stm.setInt(3, object.getEjemplares());
            stm.setLong(4, object.getGenero());
            stm.setLong(5, object.getAutor());
            stm.setLong(6, object.getId());
        } catch (SQLException ex) {
            throw new DBException(DBException.FAIL_STM + " | " + ex.getMessage());
        }
    }

    @Override
    public void setDeleteStatement(PreparedStatement stm, Long pk) throws DBException {
        try {
            stm.setLong(1, pk);
        } catch (SQLException ex) {
            throw new DBException(DBException.FAIL_STM + " | " + ex.getMessage());
        }
    }

    @Override
    public Libro createObject(ResultSet result) throws DBException {
        Libro libro = null;
        
        try {
            Long id = result.getLong("id");
            String titulo = result.getString("titulo");
            int paginas = result.getInt("paginas");
            int ejemplares = result.getInt("ejemplares");
            Long genero = result.getLong("genero");
            Long autor = result.getLong("autor");
            
            libro = new Libro(id, titulo, paginas, ejemplares, genero, autor);
        } catch (SQLException ex) {
            throw new DBException("Ocurrio un error al crear la instancia del Libro." + " | " + ex.getMessage());
        }
        
        return libro;
    }
    
}
