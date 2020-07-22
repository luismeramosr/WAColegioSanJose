/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.models;

/**
 *
 * @author luisr
 */
public class Alumno {
    public String idAlumno;
    public String Seccion;
    public String Usuario;
    
    public Alumno() {
        
    }

    public Alumno(String idAlumno, String Seccion, String Usuario) {
        this.idAlumno = idAlumno;
        this.Seccion = Seccion;
        this.Usuario = Usuario;
    }  
        
}
