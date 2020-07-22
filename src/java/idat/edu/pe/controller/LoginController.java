/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import idat.edu.pe.db.DBManager;
import idat.edu.pe.models.Alumno;
import idat.edu.pe.models.Docente;
import idat.edu.pe.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luisr
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
        String user = request.getParameter("txtUser");
        String password = request.getParameter("txtPassword");  

        System.out.println(login(user,password));
                
    }
    
    DBManager db = new DBManager("localhost", "root", "123", "apolloma_Colegio");
    private boolean login(String user, String password) {                
        Usuario newUser = db.readRow(new Usuario(), user);
        
        if (newUser!=null) {
            if(isAlumno(newUser) && newUser.password.equals(password)) {
                Alumno alu = db.readRow(new Alumno(), user);
                // Agregar alumno a la sesion.
                return true;
            }else if(isDocente(newUser) && newUser.password.equals(password)) {
                Docente doc = db.readRow(new Docente(), user);
                // Agregar docente a la sesion.
                return true;
            }else return false;
        }else return false;       
                
    }
    
    private boolean isAlumno(Usuario user) {
        if(user.tipo.equals("A"))
            return true;
        else return false;
    }
    
    private boolean isDocente(Usuario user) {
        if(user.tipo.equals("D"))
            return true;
        else return false;
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
