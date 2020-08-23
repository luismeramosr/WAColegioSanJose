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
    public int idEvaluacion;
    public String Seccion;
    public String Curso;
    public int habilitado;
    public int duracion;
    public int limiteEntrega;
    public String data;

    public Evaluacion() {
    }

    public Evaluacion(int idEvaluacion, String Seccion, String Curso, int habilitado, int duracion, int fecha_limite, String data) {
        this.idEvaluacion = idEvaluacion;
        this.Seccion = Seccion;
        this.Curso = Curso;
        this.habilitado = habilitado;
        this.duracion = duracion;
        this.limiteEntrega = fecha_limite;
        this.data = data;
    }
    
    public boolean habilitada() {
        return this.habilitado == 1 ? true:false;
    }
}
