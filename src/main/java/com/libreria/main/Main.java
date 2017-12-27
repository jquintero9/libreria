package com.libreria.main;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import com.libreria.db.ConnectionDB;
import com.libreria.dao.PaisDAO;
import com.libreria.models.Pais;
import com.libreria.db.DBException;
import java.util.List;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        try {
            
            PaisDAO paisDAO = new PaisDAO();
            
            /*Pais p = new Pais("Ur", "Uruguay");
            
            
            paisDAO.create(p);*/
            
            List<Pais> paises = paisDAO.all();
            
            paises.forEach((pais) -> System.out.println(pais.toString()));
            
            /*Pais pais = paisDAO.get(9L);
            System.out.println(pais.toString() + "\n Antes de actualizar");
            pais.setCod("co");
            pais.setNombre("Colombia");
            
            paisDAO.update(pais);
            
            System.out.println("Despúes de actualizar \n " + pais.toString());*/
            
            /*paisDAO.delete(1L);
            System.out.println("El registro ha sido eliminados");
            */
        } catch(DBException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
