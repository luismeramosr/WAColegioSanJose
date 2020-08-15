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
public class Evaluacion {
    public String Seccion;
    public String Curso;
    public String data;

    public Evaluacion() {
    }

    public Evaluacion(String Seccion, String Curso, String data) {
        this.Seccion = Seccion;
        this.Curso = Curso;
        this.data = data;
    }   
        
}
