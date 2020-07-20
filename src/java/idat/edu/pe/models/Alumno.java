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
    public String apellido;
    public String nombre;
    public String idEsp;
    public String proce;
    
    public Alumno(String IdAlumno, String ApeAlumno, String NomAlumno, String Idesp, String PROCE) {
        this.idAlumno = IdAlumno;
        this.apellido = ApeAlumno;
        this.nombre = NomAlumno;
        this.idEsp = Idesp;
        this.proce = PROCE;
    }

//    public Alumno(String idAlumno, String nombre, String apellido, String edad, String procedimiento) {
//        this.IdAlumno = idAlumno;
//        this.ApeAlumno = nombre;
//        this.NomAlumno = apellido;
//        this.Idesp = edad;
//        this.PROCE = procedimiento;
//    }
    
    public Alumno() {
        
    }
    
    
}
