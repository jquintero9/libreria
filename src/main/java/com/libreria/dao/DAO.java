package com.libreria.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.libreria.db.ConnectionDB;
import com.libreria.db.DBException;

public abstract class DAO<Model, K> {
    
    protected HashMap<String, String> sentences;
    
    public DAO() {
        sentences = new HashMap();
    }
    
    public void create(Model object) throws DBException {
        PreparedStatement stm = null;
        ResultSet keys = null;
        
        try {
            ConnectionDB.connect();
            stm = ConnectionDB.getConnection().prepareStatement(sentences.get("create"), Statement.RETURN_GENERATED_KEYS);
            setCreateStatement(stm, object);
            
            if (stm.executeUpdate() == 0) {
                throw new DBException("Ocurrio un error al insertar el registro.");
            }
            
            setPrimaryKeys(stm.getGeneratedKeys(), object);
        } catch (SQLException ex) {
            throw new DBException(DBException.FAIL_TRANSACTION);
        } finally {
            ConnectionDB.close();
            
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    throw new DBException(DBException.FAIL_CLOSE_STM + " | " + ex.getMessage());
                }
            }
        }
    }
    
    public List<Model> all() throws DBException {
        List<Model> objects = new ArrayList();
        Statement stm = null;
        
        try {
            ConnectionDB.connect();
            stm = ConnectionDB.getConnection().createStatement();
            
            ResultSet result = stm.executeQuery(sentences.get("all"));
            
            while (result.next()) {
                objects.add(createObject(result));
            }
        } catch (SQLException ex) {
            throw new DBException(DBException.FAIL_TRANSACTION + " | " + ex.getMessage());
        } finally {
            ConnectionDB.close();
            
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    throw new DBException(DBException.FAIL_CLOSE_STM);
                }
            }
        }
        
        return objects;
    }
    
    public Model get(K pk) throws DBException {
        Model object = null;
        PreparedStatement stm = null;
        
        try {
            ConnectionDB.connect();
            stm = ConnectionDB.getConnection().prepareStatement(sentences.get("get"));
            setGetStatement(stm, pk);
            
            ResultSet result = stm.executeQuery();
            
            if (result.next()) {
                object = createObject(result);
            }
        } catch (SQLException ex) {
            throw new DBException(DBException.FAIL_TRANSACTION + " | " + ex.getMessage());
        } finally {
            ConnectionDB.close();
            
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    throw new DBException(DBException.FAIL_CLOSE_STM);
                }
            }
        }
        
        return object;
    }
    
    public void update(Model object) throws DBException {
        PreparedStatement stm = null;
        
        try {
            ConnectionDB.connect();
            stm = ConnectionDB.getConnection().prepareStatement(sentences.get("update"));
            
            setUpdateStatement(stm, object);
            
            if (stm.executeUpdate() == 0) {
                throw new DBException("Ocurrio un error al actualizar el registro.");
            }
            
        } catch (SQLException ex) {
            throw new DBException(DBException.FAIL_TRANSACTION + " | " + ex.getMessage());
        } finally {
            ConnectionDB.close();
            
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    throw new DBException(DBException.FAIL_CLOSE_STM + " | " + ex.getMessage());
                }
            }
        }
    }
    
    public void delete(K pk) throws DBException {
        PreparedStatement stm = null;
        
        try {
            ConnectionDB.connect();
            stm = ConnectionDB.getConnection().prepareStatement(sentences.get("delete"));
            setDeleteStatement(stm, pk);
            
            if (stm.executeUpdate() == 0) {
                throw new DBException("Ocurrio un error al eliminar el registro de la base de datos.");
            }
        } catch (SQLException ex) {
            throw new DBException(DBException.FAIL_TRANSACTION + " | " + ex.getMessage());
        } finally {
            ConnectionDB.close();
            
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    throw new DBException(DBException.FAIL_CLOSE_STM + " | " + ex.getMessage());
                }
            }
        }
    }
    
    public abstract void setCreateStatement(PreparedStatement stm, Model object) throws DBException;
    public abstract void setGetStatement(PreparedStatement stm, K pk) throws DBException;
    public abstract void setUpdateStatement(PreparedStatement stm, Model object) throws DBException;
    public abstract void setDeleteStatement(PreparedStatement stm, K pk) throws DBException;
    public abstract Model createObject(ResultSet result) throws DBException;
    public abstract void setPrimaryKeys(ResultSet keys, Model object) throws DBException;
}
