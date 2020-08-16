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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "EvaluacionController", urlPatterns = {"/EvaluacionController"})
public class CrearEvaluacionController extends HttpServlet {

    //DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");
    DBManager db = new DBManager("192.168.1.100", "root", "123", "apolloma_Colegio");
    Gson gson = new Gson();
    JsonObject jo;
    HttpSession session;
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Evaluacion> ev = db.readTable(Evaluacion.class);
        response.setHeader("Content-Type", "application/json");        
        PrintWriter pw = response.getWriter();
        pw.println(gson.toJson(ev));
        pw.close();      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        jo = gson.fromJson(request.getReader(), JsonObject.class);
        Docente doc = (Docente) session.getAttribute("user");
        Curso curso = db.readRow(Curso.class, doc.idDocente, 2);
        String data = jo.toString();
        Evaluacion newev = new Evaluacion(getNewEvaluacionID(),curso.Seccion, curso.idCurso, data);
        db.insertRow(newev);
    }
    
    private int getNewEvaluacionID() {
        List<Evaluacion> evaluaciones = db.readTable(Evaluacion.class);
        return ++evaluaciones.get(evaluaciones.size()-1).idEvaluacion;
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
