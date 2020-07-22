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

    public Usuario() {
        //Empty constructor, works as a template
    }

    public Usuario(String idUsuario, String password, String tipo) {
        this.idUsuario = idUsuario;
        this.password = password;
        this.tipo = tipo;
    }        
  
}
