/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import idat.edu.pe.db.DBManager;
import idat.edu.pe.models.Alumno;
import idat.edu.pe.models.Curso;
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
 * @author littman
 */
@WebServlet(name = "ListaEvaluacionesController", urlPatterns = {"/ListaEvaluacionesController"})
public class ListaEvaluacionesController extends HttpServlet {
    DBManager db = new DBManager("localhost","3306","root","123","bdnotas");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idcurso = request.getParameter("idcurso");
        if(idcurso != null){
            List<Alumno> lstalu = db.readTable(new Alumno());
            request.setAttribute("lstalu", lstalu);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEvaluaciones.jsp");
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
