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
import idat.edu.pe.models.Evaluacion;
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

/**
 *
 * @author luisr
 */
@WebServlet(name = "EditarEvaluacionController", urlPatterns = {"/EditarEvaluacionController"})
public class EditarEvaluacionController extends HttpServlet {

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
        JsonObject evaluacion = gson.fromJson(request.getReader(), JsonObject.class);
        if(evaluacion.get("data")!=null) {
            actualizarEvaluacion(evaluacion);
        }else{
            String idEv = evaluacion.get("id").getAsString();
            eliminarEvaluacion(idEv);
        }
        
    }
    
    private void actualizarEvaluacion(JsonObject json) {
        Evaluacion ev = new Evaluacion(json.get("id").getAsInt(), json.get("seccion").getAsString(),
                            json.get("curso").getAsString(), 1,
                            json.get("duracion").getAsInt(), json.get("limiteEntrega").getAsInt(),
                            json.get("data").getAsJsonObject().toString());
        db.updateRow(ev);
        int currentTime = Math.round(new Date().getTime()/1000);
        int limiteEntrega = json.get("limiteEntrega").getAsInt();
        System.out.println(limiteEntrega-currentTime);
        ts.scheduleTask(new Task() {
            @Override
            public void run() {
                ev.habilitado = 0;
                db.updateRow(ev);
            }
        }, limiteEntrega-currentTime);
    }
    
    private void eliminarEvaluacion(String idEvaluacion) {
        Evaluacion ev = db.readRow(Evaluacion.class, idEvaluacion, 0);
        db.deleteRow(ev);
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
