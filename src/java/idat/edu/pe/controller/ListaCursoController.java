/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import idat.edu.pe.db.DBManager;
import idat.edu.pe.models.Alumno;
import idat.edu.pe.models.Curso;
import idat.edu.pe.models.Docente;
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
@WebServlet(name = "ListaCursoController", urlPatterns = {"/ListaCursoController"})
public class ListaCursoController extends HttpServlet {

    DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");
    HttpSession session;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("userData");
        
        if(user.isAlumno()) {
            Alumno alu = (Alumno) session.getAttribute("user");
            List<Curso> lstcursos = db.readTable(Curso.class, alu.Seccion, 1);
            request.setAttribute("lstcursos", lstcursos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaCursos.jsp");
            dispatcher.forward(request, response);            
        }else if(user.isDocente()) {
            Docente doc = (Docente) session.getAttribute("user");
            List<Curso> lstcursos = db.readTable(Curso.class, doc.idDocente, 2);
            request.setAttribute("lstcursos", lstcursos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaCursos.jsp");
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
