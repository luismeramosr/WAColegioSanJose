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
 * Clase para la comunicación con la base de datos (intento de orm xd)
 * usando funciones y objetos genéricos para manipular la base de datos con poo
 * aun esta en prueba y es lo que estoy creando para mi proyecto del curso.
 * @author luisr
 */
public class DBManager {

    // Default port is 3306
    private String port = "3306";
    private String user;
    private String password;
    private String url = "jdbc:mysql://";
    public Connection conn = null;

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
    
    private void MapValueToType(Field attribute, ResultSet reader,Object newObject){
        
        try{
//            if (attribute.getType().equals(int.class))
//                attribute.set(newObject, reader.getInt(attribute.getName()));
//            else if (attribute.getType().equals(String.class))
//                attribute.set(newObject, reader.getString(attribute.getName()));   
//            else if (attribute.getType().equals(String.class))
                attribute.set(newObject, reader.getObject(attribute.getName(), attribute.getType()));
        }catch (SQLException | IllegalAccessException | IllegalArgumentException err) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, err);
        }        
    }
    
    private <T> String CreateSQLFromType(String actionType,Field attribute, T obj) {
        try{
            if(actionType.equals("update")) {
                if (!attribute.getType().equals(String.class))
                    return attribute.getName()+"="+attribute.get(obj)+",";
                else if(attribute.getType().equals(String.class))
                    return attribute.getName()+"='"+attribute.get(obj)+"',";
                else return "";
            }else if(actionType.equals("insert")){
                if (!attribute.getType().equals(String.class))
                    return attribute.get(obj)+",";                                   
                else if(attribute.getType().equals(String.class))
                    return "'"+attribute.get(obj)+"',"; 
                else return "";
            }
        }catch (IllegalArgumentException | IllegalAccessException err){
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, err);
        }
        return "";
    }

    /**
     * Este es un método genérico el cual retorna una lista de objetos que
     * representan las filas de una tabla en la BD, requiere que los objetos
     * tengan por atributos los nombres de las columnas de la BD (en el mismo
     * orden y con los mismos nombres).
     *
     * @param <T> Es la clase que utilizaremos como "Molde", sirve como guía
     * para crear una lista de objetos de dicha clase y una instancia vacía de
     * dicha clase. (Este parámetro es obtenido implícitamente del siguiente).
     * @param object Es el parámetro del cual extraeremos la clase y sus
     * propiedades, de aquí se infiere el tipo de dato (clase) con el que vamos
     * a trabajar.
     * @return objectList El resultado de esta función es una lista de objetos
     * que contienen la información obtenida de la tabla.
     */
    public <T> List<T> readTable(Class TClass) {

        List<T> tempList = new ArrayList<>();
        Field[] attributes = TClass.getFields();
        String querySQL = "select * from " + TClass.getSimpleName() + ";";

        open();

        try {

            Statement smt = this.conn.createStatement();
            ResultSet result = smt.executeQuery(querySQL);

            while (result.next()) {
                T newObject = (T) TClass.newInstance();
                for (Field attribute : attributes) {
                    MapValueToType(attribute, result, newObject);
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

    public <T> List<T> readTable(Class TClass, String objectID, int column) {

        List<T> tempList = new ArrayList<>();
        Field[] attributes = TClass.getFields();
        String querySQL = "select * from " + TClass.getSimpleName() + 
                          " where "+attributes[column].getName()+" ='"+objectID+"';";

        open();
        try {

            Statement smt = this.conn.createStatement();
            ResultSet result = smt.executeQuery(querySQL);

            while (result.next()) {
                T newObject = (T) TClass.newInstance();
                for (Field attribute : attributes) {
                    MapValueToType(attribute, result, newObject);
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
     * @param <T> Es la clase que utilizaremos como "Molde", sirve como guía
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
    public <T> T readRow(Class genClass, String objectID, int columnIndex) {

        T newObject = null;

        try {
            Field[] attributes = genClass.getFields();
            String querySQL = "select * from " + genClass.getSimpleName()
                    + " where " + attributes[columnIndex].getName() + " = '"
                    + objectID + "';";

            open();
            Statement smt = this.conn.createStatement();
            ResultSet result = smt.executeQuery(querySQL);

            while (result.next()) {
                newObject = (T) genClass.newInstance();
                for (Field attribute : attributes) {
                    MapValueToType(attribute, result, newObject);
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
     * @param <T> Es la clase que utilizaremos como "Molde", sirve como guía
     * para crear una lista de objetos de dicha clase y una instancia vacía de
     * dicha clase. (Este parámetro es obtenido implícitamente del siguiente).
     * @param object Es el parámetro del cual extraeremos la clase y sus
     * propiedades, de aquí se infiere el tipo de dato (clase) con el que vamos
     * a trabajar.
     */
    public <T> void deleteRow(T obj) {
        try {
            Field[] attributes = obj.getClass().getFields();
            String query = "delete from " + obj.getClass().getSimpleName() + " where "
                    + attributes[0].getName() + "='" + attributes[0].get(obj) + "';";
            open();
            Statement smt = this.conn.createStatement();
            smt.executeUpdate(query);
            close();
        } catch (IllegalArgumentException | IllegalAccessException |
                SQLException err) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, err);
        }
    }

    /**
     *
     * @param <T>
     * @param object
     */
    public <T> boolean insertRow(T obj) {
        Field[] attributes = obj.getClass().getFields();
        String tb = obj.getClass().getSimpleName();
        String query = "insert into " + tb+ " (";
       
        for (Field field : attributes) {
            query += field.getName()+",";
        }
        query = query.substring(0, query.length() - 1);
        query+=") values(";
        for (Field field : attributes) {
            query +=CreateSQLFromType("insert",field, obj);
        }
        query = query.substring(0, query.length() - 1);
        query += ");";
        try{
            open();
            Statement smt = this.conn.createStatement();
            smt.executeUpdate(query);
            close();
            return true;
        }catch(SQLException err){
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, err);
            return false;
        }        
    }

    /**
     *
     * @param <T>
     * @param object
     */
    public <T> boolean updateRow(T obj){
        Field[] attributes= obj.getClass().getFields();
        String tb = obj.getClass().getSimpleName();
        String query ="update "+tb+" set " ;
        for(Field field: attributes){
            query += CreateSQLFromType("update", field, obj);
        }
        query=query.substring(0,query.length()-1);
        try {
            query += " where "+attributes[0].getName() + "='" + 
                    attributes[0].get(obj).toString()+"';";
            open();
            Statement objsql = this.conn.createStatement();
            objsql.executeUpdate(query);
            close();
            return true;
        } catch (IllegalArgumentException | IllegalAccessException | SQLException err) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, err);
            return false;
        }       
    }

}
