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
    public String username;
    public String password;
    public String dni;
    public String telefono;
    public String tipo;

    public Usuario() {
        //Empty constructor, works as a template
    }

    public Usuario(String idUsuario, String username, String password, String dni, String telefono, String tipo) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.dni = dni;
        this.telefono = telefono;
        this.tipo = tipo;
    }        
  
}
