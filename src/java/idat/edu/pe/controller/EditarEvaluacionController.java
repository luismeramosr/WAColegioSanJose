/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import com.google.gson.Gson;
import idat.edu.pe.db.DBManager;
import idat.edu.pe.models.Curso;
import idat.edu.pe.models.Evaluacion;
import idat.edu.pe.scheduler.Task;
import idat.edu.pe.scheduler.TaskScheduler;
import java.io.IOException;
import java.io.PrintWriter;
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

    //DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");
    DBManager db = new DBManager("192.168.1.100", "root", "123", "apolloma_Colegio");
//    TaskScheduler ts = new TaskScheduler();
    Gson gson = new Gson();
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idEvaluacion = request.getParameter("idEvaluacion");
        Evaluacion ev = db.readRow(Evaluacion.class, idEvaluacion, 0);
        List<Curso> cursos = db.readTable(Curso.class, ev.Curso, 0);
        request.setAttribute("cursos", cursos);
        response.setHeader("Content-Type", "application/json");        
        PrintWriter pw = response.getWriter();
        pw.println(gson.toJson(ev));
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditarEvaluacion.jsp");
        dispatcher.forward(request, response);
        pw.close();           

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idEv = gson.fromJson(request.getReader(), String.class);
        
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
