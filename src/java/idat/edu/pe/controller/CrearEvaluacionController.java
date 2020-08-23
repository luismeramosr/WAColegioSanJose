/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import idat.edu.pe.db.DBManager;
import idat.edu.pe.models.Curso;
import idat.edu.pe.models.Docente;
import idat.edu.pe.models.Evaluacion;
import idat.edu.pe.models.Usuario;
import idat.edu.pe.scheduler.Task;
import idat.edu.pe.scheduler.TaskScheduler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luisr
 */
@WebServlet(name = "CrearEvaluacionController", urlPatterns = {"/CrearEvaluacionController"})
public class CrearEvaluacionController extends HttpServlet {

    //DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");
    DBManager db = new DBManager("192.168.1.100", "root", "123", "apolloma_Colegio");
    Gson gson = new Gson();
    JsonObject jo;
    HttpSession session;
    TaskScheduler ts = new TaskScheduler();           

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        jo = gson.fromJson(request.getReader(), JsonObject.class);
        Curso curso = db.readRow(Curso.class, jo.get("curso").getAsString(), 0);    
        String newEvData = jo.get("data").getAsJsonObject().toString();
        int duracion = jo.get("duracion").getAsInt();
        int limiteEntrega = jo.get("limiteEntrega").getAsInt();
        int currentTime = Math.round(new Date().getTime()/1000);
        Evaluacion newev = new Evaluacion(getNewEvaluacionID(), curso.Seccion, 
                                curso.idCurso, 1, duracion, limiteEntrega, newEvData);        
        db.insertRow(newev);
        ts.scheduleTask(new Task() {
            @Override
            public void run() {
                newev.habilitado=0;
                db.updateRow(newev);           
            }
        }, limiteEntrega-currentTime);
    }
    
    private int getNewEvaluacionID() {
        try {
            List<Evaluacion> evaluaciones = db.readTable(Evaluacion.class);
            return ++evaluaciones.get(evaluaciones.size()-1).idEvaluacion;
        }catch (ArrayIndexOutOfBoundsException err) {
            System.out.println(err);
            return 1;
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
