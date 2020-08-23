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
public class AlumnoXEvaluacion {
    
    public int Evaluacion;
    public String Alumno;
    public String Seccion;
    public String Curso;
    public Double nota;
    public int aciertos;
    public int errores;
    public int preguntas;
    public String tiempo;
    
    public AlumnoXEvaluacion() {
    }

    public AlumnoXEvaluacion(int Evaluacion, String Alumno, String Seccion, String Curso, Double nota, int aciertos, int errores, int preguntas, String tiempo) {
        this.Evaluacion = Evaluacion;
        this.Alumno = Alumno;
        this.Seccion = Seccion;
        this.Curso = Curso;
        this.nota = nota;
        this.aciertos = aciertos;
        this.errores = errores;
        this.preguntas = preguntas;
        this.tiempo = tiempo;
    }  
    
}
