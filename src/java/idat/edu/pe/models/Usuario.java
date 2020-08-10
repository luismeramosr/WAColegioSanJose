package idat.edu.pe.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luisr
 */
public class Usuario {
    
    public String idUsuario;
    public String password;
    public String tipo;
    public String nombre;
    public String apellidos;
    public String edad;
    public String correo;
    public String image;
    
    
    public Usuario() {
        //Empty constructor, works as a template
    }

    public Usuario(String idUsuario, String password, String tipo, String nombre, String apellidos, String edad, String correo, String image) {
        this.idUsuario = idUsuario;
        this.password = password;
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.image = image;
    }    
    
    public boolean isAlumno() {
        if(this.tipo.equals("A"))
            return true;
        else return false;
    }
    
    public boolean isDocente() {
        if(this.tipo.equals("D"))
            return true;
        else return false;
    }
  
}
