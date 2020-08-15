/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import idat.edu.pe.db.DBManager;
import idat.edu.pe.models.Alumno;
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
 * @author luisr
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    
    //DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");
    DBManager db = new DBManager("192.168.1.100", "root", "123", "apolloma_Colegio");
    HttpSession session;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        session = request.getSession();
        session.invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    Gson gson = new Gson();
    JsonObject jo;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        jo = gson.fromJson(request.getReader(), JsonObject.class);
        String user = jo.get("user").getAsString();
        String password = jo.get("password").getAsString();
        session = request.getSession();
        
        if(login(user, password))
            // User exists and has logged in
            response.setStatus(200);
        else
            // User was not found or doesn't
            response.setStatus(500);            
        
    }       
    
    private boolean login(String user, String password) {                
        Usuario newUser = db.readRow(Usuario.class, user, 0);
        
        if (newUser!=null) {
            if(newUser.isAlumno() && newUser.password.equals(password)) {
                Alumno alu = db.readRow(Alumno.class, user, 0);              
                session.setAttribute("userData", newUser);
                session.setAttribute("user", alu);
                return true;
            }else if(newUser.isDocente() && newUser.password.equals(password)) {
                Docente doc = db.readRow(Docente.class, user, 0);
                session.setAttribute("userData", newUser);
                session.setAttribute("user", doc);
                return true;
            }else return false;
        }else return false;       
                
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
