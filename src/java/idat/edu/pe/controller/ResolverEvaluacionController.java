/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import idat.edu.pe.db.DBManager;
import idat.edu.pe.models.AlumnoXEvaluacion;
import idat.edu.pe.models.Evaluacion;
import idat.edu.pe.scheduler.TaskScheduler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luisr
 */
@WebServlet(name = "ResolverEvaluacionController", urlPatterns = {"/ResolverEvaluacionController"})
public class ResolverEvaluacionController extends HttpServlet {
    
    DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");
    Gson gson = new Gson();
    JsonObject jo = new JsonObject();
    TaskScheduler ts = new TaskScheduler();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idEvaluacion = request.getParameter("idEvaluacion");
        Evaluacion ev = db.readRow(Evaluacion.class, idEvaluacion, 0);
        response.setHeader("Content-Type", "application/json");        
        PrintWriter pw = response.getWriter();
        pw.println(gson.toJson(ev));
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.nota = 0.0;
        this.aciertos = 0;
        this.errores = 0;
        Double notaMaxima = 20.0;
        JsonObject evResuelta = gson.fromJson(request.getReader(), JsonObject.class);
        Evaluacion evaluacionOriginal = db.readRow(Evaluacion.class, evResuelta.get("idEvaluacion").getAsString(), 0);
        JsonObject evOriginal = gson.fromJson(evaluacionOriginal.data, JsonObject.class);
        JsonArray preguntas = evOriginal.get("preguntas").getAsJsonArray();
        JsonArray respuestasAlumno = evResuelta.get("respuestas").getAsJsonArray();
        Double puntoPorPregunta = notaMaxima/preguntas.size();
        int i=0;
        for (JsonElement pregunta : preguntas) {
            JsonArray alternativas = pregunta.getAsJsonObject().get("alternativas").getAsJsonArray();
            for (JsonElement alternativa : alternativas) {
                calificar(puntoPorPregunta, respuestasAlumno.get(i).getAsBoolean(), 
                                    alternativa.getAsJsonObject().get("isSelected").getAsBoolean());
                i++;
            }
        }        
        errores = preguntas.size() - aciertos;
        AlumnoXEvaluacion resultado = new AlumnoXEvaluacion(evaluacionOriginal.idEvaluacion,
                                            evResuelta.get("alumno").getAsString(), 
                                            evResuelta.get("seccion").getAsString(), 
                                            evResuelta.get("curso").getAsString(), 
                                            this.nota, this.aciertos, this.errores, 
                                            preguntas.size(), evResuelta.get("tiempo").getAsString());
        
        if(db.insertRow(resultado)) {
            response.setStatus(200);
        }else{
            response.setStatus(500);
        }
    }
    
    private Double nota=0.0;
    private int aciertos=0;
    private int errores=0;    
    
    private void calificar(Double puntoPorPregunta, boolean respuestaAlumno, 
                                                    boolean respuestaDocente) {        
        if (respuestaDocente && respuestaAlumno) {
            this.nota += puntoPorPregunta;
            this.aciertos++;
        }       
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
