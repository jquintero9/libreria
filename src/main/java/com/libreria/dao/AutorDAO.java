package com.libreria.dao;

import com.libreria.db.DBException;
import com.libreria.models.Autor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutorDAO extends DAO<Autor, Long> {
    
    private final String SQL_CREATE = "INSERT INTO autores(nombres, apellidos, pais) VALUES (?, ?, ?)";
    private final String SQL_ALL = "SELECT * FROM autores";
    private final String SQL_GET = "SELECT * FROM autores WHERE id=?";
    private final String SQL_UPDATE = "UPDATE autores SET nombres=?, apellidos=?, pais=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM autores WHERE id=?";
    
    public AutorDAO() {
        super();
        sentences.put("create", SQL_CREATE);
        sentences.put("all", SQL_ALL);
        sentences.put("update", SQL_UPDATE);
        sentences.put("get", SQL_GET);
        sentences.put("delete", SQL_DELETE);
    }

    @Override
    public void setCreateStatement(PreparedStatement stm, Autor object) throws DBException {
        try {
            stm.setString(1, object.getNombres());
            stm.setString(2, object.getApellidos());
            stm.setLong(3, object.getPais());
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
    public void setUpdateStatement(PreparedStatement stm, Autor object) throws DBException {
        try {
            stm.setString(1, object.getNombres());
            stm.setString(2, object.getApellidos());
            stm.setLong(3, object.getPais());
            stm.setLong(4, object.getId());
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
    public Autor createObject(ResultSet result) throws DBException {
        Autor autor = null;
        
        try {
            Long id = result.getLong("id");
            String nombres = result.getString("nombres");
            String apellidos = result.getString("apellidos");
            Long pais = result.getLong("pais");
            
            autor = new Autor(id, nombres, apellidos, pais);
        } catch (SQLException ex) {
            throw new DBException("Ocurrio un error al crear la instancia del Autor." + " | " + ex.getMessage());
        }
        
        return autor;
    }
}