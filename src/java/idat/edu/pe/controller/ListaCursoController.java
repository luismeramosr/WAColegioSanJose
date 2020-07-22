/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import idat.edu.pe.db.DBManager;
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
 * @author littman
 */
@WebServlet(name = "ListaCursoController", urlPatterns = {"/ListaCursoController"})
public class ListaCursoController extends HttpServlet {

    DBManager db = new DBManager("localhost","3306","root","123","apolloma_Colegio");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        Usuario user =(Usuario)new Usuario();
        //if(EsAlu(user)){
            List<Curso> lstcursos = db.readTable(new Curso());
            request.setAttribute("lstcursos", lstcursos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaCursosAlu.jsp");
            dispatcher.forward(request, response);
            
        //}
    }
    private boolean EsAlu(Usuario user){
        if(user.tipo.equals("A"))
            return true;
        else
            return false;
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
