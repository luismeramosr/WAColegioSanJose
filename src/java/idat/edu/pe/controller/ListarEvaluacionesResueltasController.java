/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import idat.edu.pe.db.DBManager;
import idat.edu.pe.models.AlumnoXEvaluacion;
import idat.edu.pe.models.Curso;
import idat.edu.pe.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ListarEvaluacionesResueltasController", urlPatterns = {"/ListarEvaluacionesResueltasController"})
public class ListarEvaluacionesResueltasController extends HttpServlet {

    DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");
    HttpSession session;    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        Usuario alumno = (Usuario) session.getAttribute("userData");
        List<AlumnoXEvaluacion> evaluacionesResueltas = db.readTable(AlumnoXEvaluacion.class, 
                                                                    alumno.idUsuario, 1);        
        request.setAttribute("resultados", evaluacionesResueltas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListarEvaluacionesResueltas.jsp");
        dispatcher.forward(request, response);
    }

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
