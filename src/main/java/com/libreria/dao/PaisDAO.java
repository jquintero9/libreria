package com.libreria.dao;

import com.libreria.db.DBException;
import com.libreria.models.Pais;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaisDAO extends DAO<Pais, Long> {
    
    private final String SQL_CREATE = "INSERT INTO paises(cod, nombre) VALUES (?, ?)";
    private final String SQL_ALL = "SELECT * FROM paises";
    private final String SQL_GET = "SELECT * FROM paises WHERE id=?";
    private final String SQL_UPDATE = "UPDATE paises SET nombre=?, cod=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM paises WHERE id=?";
    
    public PaisDAO() {
        super();
        sentences.put("create", SQL_CREATE);
        sentences.put("all", SQL_ALL);
        sentences.put("get", SQL_GET);
        sentences.put("update", SQL_UPDATE);
        sentences.put("delete", SQL_DELETE);
    }
    
    @Override
    public Pais createObject(ResultSet result) throws DBException {
        Pais pais = null;
        
        try {
            Long id = result.getLong("id");
            String nombre = result.getString("nombre");
            String cod = result.getString("cod");
        
            pais = new Pais(id, cod, nombre);
        } catch (SQLException ex) {
            throw new DBException("Ocurrio un error al crear la instacia del país | " + ex.getMessage());
        }
        
        return pais;
    }

    @Override
    public void setCreateStatement(PreparedStatement stm, Pais pais) throws DBException {
        try {
            stm.setString(1, pais.getCod());
            stm.setString(2, pais.getNombre());
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
    public void setUpdateStatement(PreparedStatement stm, Pais pais) throws DBException {
        try {
            stm.setString(1, pais.getNombre());
            stm.setString(2, pais.getCod());
            stm.setLong(3, pais.getId());
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
    public void setPrimaryKeys(ResultSet keys, Pais pais) throws DBException {
        try {
            if (keys.next()) {
                pais.setId(keys.getLong("id"));
            } else throw new DBException("No se pudo extraer la llave primaria.");
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
    }
}
