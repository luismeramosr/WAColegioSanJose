/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import idat.edu.pe.db.DBManager;
import idat.edu.pe.models.Alumno;
import idat.edu.pe.models.Curso;
import idat.edu.pe.models.Evaluacion;
import idat.edu.pe.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
 * @author littman
 */
@WebServlet(name = "ListarEvaluacionesController", urlPatterns = {"/ListarEvaluacionesController"})
public class ListarEvaluacionesController extends HttpServlet {
    
    DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");
//    DBManager db = new DBManager("sql10.freemysqlhosting.net", "sql10361956", "RzbDSJcgJp", "sql10361956");
    HttpSession session;
    RequestDispatcher dispatcher;
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("userData");
        String idCurso = request.getParameter("idCurso");
        if(user!=null){
            if (user.isDocente()) {
                String seccionDocente = ((Curso) db.readRow(Curso.class, user.idUsuario, 2)).Seccion;
                List<Evaluacion> evaluaciones = idCurso == null ? 
                db.readTable(Evaluacion.class, seccionDocente, 1): evaluacionesDeCursoX(idCurso, seccionDocente);
                request.setAttribute("evaluaciones", evaluaciones);
                request.setAttribute("limitesEntrega", getDatesFromTimestamps(evaluaciones));
                dispatcher = request.getRequestDispatcher("/ListarEvaluaciones.jsp");
                dispatcher.forward(request, response);
            } else if (user.isAlumno()) {
                Alumno alu = db.readRow(Alumno.class, user.idUsuario, 0);
                List<Evaluacion> evaluaciones = idCurso == null ? 
                db.readTable(Evaluacion.class, alu.Seccion, 1): evaluacionesDeCursoX(idCurso, alu.Seccion);
                request.setAttribute("evaluaciones", evaluaciones);
                request.setAttribute("limitesEntrega", getDatesFromTimestamps(evaluaciones));
                dispatcher = request.getRequestDispatcher("/ListarEvaluaciones.jsp");
                dispatcher.forward(request, response);
            }
        }else
            response.sendRedirect("index.jsp");            
    }
    
    private List<Evaluacion> evaluacionesDeCursoX(String idCurso, String Seccion) {
        List<Evaluacion> evaluacionesDeCursoX = new ArrayList();
        List<Evaluacion> evaluacionesDeSeccionX = db.readTable(Evaluacion.class, Seccion, 1);
        for (Evaluacion ev: evaluacionesDeSeccionX) {
            if(ev.Curso.equals(idCurso))
                evaluacionesDeCursoX.add(ev);
        }
        return evaluacionesDeCursoX;
    }
    
    private List<String> getDatesFromTimestamps(List<Evaluacion> evaluaciones) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        List<String> dates = new ArrayList();
        for (Evaluacion ev : evaluaciones) {
            Date date = new Date();
            date.setTime((long) ev.limiteEntrega*1000);
            dates.add(formatter.format(date));
        }
        return dates;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        
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
