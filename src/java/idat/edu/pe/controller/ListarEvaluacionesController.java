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
@WebServlet(name = "ListaEvaluacionesController", urlPatterns = {"/ListaEvaluacionesController"})
public class ListaEvaluacionesController extends HttpServlet {
    
    //DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");
    DBManager db= new DBManager("localhost","3306","root","123","apolloma_Colegio");
    HttpSession session;
    RequestDispatcher dispatcher;
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("userData");
        
        
        if (user.isDocente()) {
            List<Evaluacion> evaluaciones = db.readTable(Evaluacion.class, user.idUsuario, 0);
            request.setAttribute("evaluaciones", evaluaciones);
            dispatcher = request.getRequestDispatcher("/ListaEvaluaciones.jsp");
            dispatcher.forward(request, response);
        }        
                
        
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
