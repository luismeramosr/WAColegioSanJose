/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para la comunicación con la base de datos.
 *
 * @author luisr
 */
public class DBManager {

    // Default port is 3306
    private String port = "3306";
    private String user;
    private String password;
    private String url = "jdbc:mysql://";
    private Connection conn = null;

    /**
     * Crea una nueva instancia de la base de datos, pero debes especificar el
     * puerto de conexión.
     *
     * @param hostIP Es la dirección IP del servidor donde está la base de
     * datos.
     * @param port Es el puerto en el que esta la BD.
     * @param user Usuario con el que nos conectaremos a la BD.
     * @param password Contraseña del usuario para acceder.
     * @param dbName Nombre de la BD a usar.
     */
    public DBManager(String hostIP, String port, String user, String password, String dbName) {
        this.url = this.url + hostIP + ":" + port + "/" + dbName;
        this.user = user;
        this.password = password;
    }

    /**
     * Crea una nueva instancia de la base de datos, el puerto de conexión por
     * defecto es 3306.
     *
     * @param hostIP Es la dirección IP del servidor donde está la base de
     * datos.
     * @param user Usuario con el que nos conectaremos a la BD.
     * @param password Contraseña del usuario para acceder.
     * @param dbName Nombre de la BD a usar.
     */
    public DBManager(String hostIP, String user, String password, String dbName) {
        this.url = this.url + hostIP + ":" + this.port + "/" + dbName;
        this.user = user;
        this.password = password;
    }

    private void open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
        } catch (ClassNotFoundException | SQLException err) {
            // Mensaje  de error
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, err);
        }
    }

    private void close() {
        try {
            conn.close();
        } catch (SQLException err) {
            // Mensaje  de error
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, err);
        }
    }

    /**
     * Este es un método genérico el cual retorna una lista de objetos que
     * representan las filas de una tabla en la BD, requiere que los objetos
     * tengan por atributos los nombres de las columnas de la BD (en el mismo
     * orden y con los mismos nombres).
     *
     * @param <Gen> Es la clase que utilizaremos como "Molde", sirve como guía
     * para crear una lista de objetos de dicha clase y una instancia vacía de
     * dicha clase. (Este parámetro es obtenido implícitamente del siguiente).
     * @param object Es el parámetro del cual extraeremos la clase y sus
     * propiedades, de aquí se infiere el tipo de dato (clase) con el que vamos
     * a trabajar.
     * @return objectList El resultado de esta función es una lista de objetos
     * que contienen la información obtenida de la tabla.
     */
    public <Gen> List<Gen> readTable(Gen object) {

        List<Gen> tempList = new ArrayList<>();
        Field[] attributes = object.getClass().getFields();
        String querySQL = "select * from " + object.getClass().getSimpleName() + ";";

        open();

        try {

            Statement smt = this.conn.createStatement();
            ResultSet result = smt.executeQuery(querySQL);

            while (result.next()) {
                Gen newObject = (Gen) object.getClass().newInstance();
                for (Field attribute : attributes) {
                    attribute.set(newObject, result.getString(attribute.getName()));
                }
                tempList.add(newObject);
            }

            close();
        } catch (SQLException | IllegalAccessException |
                InstantiationException | NullPointerException err) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, err);
        }

        return tempList;
    }

    /**
     * Esta función lee una fila de la base de datos y retorna un objeto con el
     * contenido de dicha fila, es necesario que el objeto que usamos como
     * parámetro tenga como atributos los nombres de las columnas de la BD (en
     * el mismo orden y con los mismos nombres).
     *
     * @param <Gen> Es la clase que utilizaremos como "Molde", sirve como guía
     * para crear una lista de objetos de dicha clase y una instancia vacía de
     * dicha clase. (Este parámetro es obtenido implícitamente del siguiente).
     * @param object Es el parámetro del cual extraeremos la clase y sus
     * propiedades, de aquí se infiere el tipo de dato (clase) con el que vamos
     * a trabajar.
     * @param objectID Es el ID que usaremos para filtrar en la base de datos y
     * solo obtener la fila que corresponde.
     * @return newObject Retorna un objeto que corresponde a la fila obtenida de
     * la BD.
     */
    public <Gen> Gen readRow(Gen object, String objectID) {

        Gen newObject = null;

        try {
            Field[] attributes = object.getClass().getFields();
            String querySQL = "select * from " + object.getClass().getSimpleName()
                    + " where " + attributes[0].getName() + "='"
                    + objectID + "';";

            open();

            Statement smt = this.conn.createStatement();
            ResultSet result = smt.executeQuery(querySQL);

            while (result.next()) {
                newObject = (Gen) object.getClass().newInstance();
                for (Field attribute : attributes) {
                    attribute.set(newObject, result.getString(attribute.getName()));
                }
            }

        } catch (IllegalArgumentException | IllegalAccessException |
                SQLException | InstantiationException | NullPointerException err) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, err);
        }

        return newObject;
    }

    /**
     *
     * @param <Gen> Es la clase que utilizaremos como "Molde", sirve como guía
     * para crear una lista de objetos de dicha clase y una instancia vacía de
     * dicha clase. (Este parámetro es obtenido implícitamente del siguiente).
     * @param object Es el parámetro del cual extraeremos la clase y sus
     * propiedades, de aquí se infiere el tipo de dato (clase) con el que vamos
     * a trabajar.
     */
    public <Gen> void deleteRow(Gen object) {
        try {
            Field[] attributes = object.getClass().getFields();
            String querySQL = "delete from " + object.getClass().getSimpleName() + " where "
                    + attributes[0].getName() + "='" + attributes[0].get(object) + "';";

            open();
            Statement smt = this.conn.createStatement();
            smt.executeUpdate(querySQL);
            close();
        } catch (IllegalArgumentException | IllegalAccessException |
                SQLException err) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, err);
        }
    }

    /**
     *
     * @param <Gen>
     * @param object
     */
    public <Gen> void insertRow(Gen object) {
        Field[] attributes = object.getClass().getFields();
        String tb = object.getClass().getSimpleName();
        String query = "insert into " + tb + "(";
        for (Field field : attributes) {
            try {
                query = query + field.getName() + ",";

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query = query.substring(0, query.length() - 1);
        query += ") values (";
        for (Field field : attributes) {
            try {
                query = query + "'" + field.get(object) + "',";
                //bool
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query = query.substring(0, query.length() - 1);
        query += ");";
        try{
                open();
                Statement objsql = this.conn.createStatement();
                objsql.executeUpdate(query);
                close();
            }catch(SQLException ex){
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }        
    }
    //----------

    /**
     *
     * @param <Gen>
     * @param object
     */
    public <Gen> void updateRow(Gen object){
        Field[] attributes= object.getClass().getFields();
        String tb = object.getClass().getSimpleName();
        String query ="update "+tb+" set " ;
        for(Field field: attributes){
            try {
                query = query + field.getName()+" = '"+field.get(object)+"',";
                
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query=query.substring(0,query.length()-1);
        query += " where ";
            try {
                query += attributes[0].getName() + "='" + attributes[0].get(object).toString()+"'";
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        query +=";";
            try{
                open();
                Statement objsql = this.conn.createStatement();
                objsql.executeUpdate(query);
                close();
            }catch(SQLException ex){
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }

}
