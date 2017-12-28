package com.libreria.dao;

import com.libreria.db.DBException;
import com.libreria.models.Genero;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneroDAO extends DAO<Genero, Long> {
    
    private final String SQL_CREATE = "INSERT INTO generos(nombre, descripcion) VALUES (?, ?)";
    private final String SQL_ALL = "SELECT * FROM generos";
    private final String SQL_GET = "SELECT * FROM generos WHERE id=?";
    private final String SQL_UPDATE = "UPDATE generos SET nombre=?, descripcion=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM generos WHERE id=?";
    
    public GeneroDAO() {
        super();
        sentences.put("create", SQL_CREATE);
        sentences.put("all", SQL_ALL);
        sentences.put("update", SQL_UPDATE);
        sentences.put("get", SQL_GET);
        sentences.put("delete", SQL_DELETE);
    }

    @Override
    public void setCreateStatement(PreparedStatement stm, Genero object) throws DBException {
        try {
            stm.setString(1, object.getNombre());
            stm.setString(2, object.getDescripcion());
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
    public void setUpdateStatement(PreparedStatement stm, Genero object) throws DBException {
        try {
            stm.setString(1, object.getNombre());
            stm.setString(2, object.getDescripcion());
            stm.setLong(3, object.getId());
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
    public Genero createObject(ResultSet result) throws DBException {
        Genero genero = null;
        
        try {
            Long id = result.getLong("id");
            String nombre = result.getString("nombre");
            String descripcion = result.getString("descripcion");
            genero = new Genero(id, nombre, descripcion);
        } catch (SQLException ex) {
            throw new DBException("Ocurrio un erro al crear la instancia del Género." + " | " + ex.getMessage());
        }
        
        return genero;
    }
}
