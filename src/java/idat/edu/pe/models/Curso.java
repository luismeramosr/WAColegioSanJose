/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.models;

/**
 *
 * @author littman
 */
public class Curso {
    public String idCurso;
    public String Seccion;    
    public String Docente;
    public String nombre;
    
    

    public Curso(String idCurso, String nombre, String Docente, String Seccion) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.Docente = Docente;
        this.Seccion = Seccion;
    }
    
    public Curso(){
        
    }
            
    
    
}
