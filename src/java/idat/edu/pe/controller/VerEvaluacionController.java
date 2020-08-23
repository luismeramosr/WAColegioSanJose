/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import idat.edu.pe.db.DBManager;
import idat.edu.pe.models.Evaluacion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author littman
 */
@WebServlet(name = "VerEvaluacionController", urlPatterns = {"/VerEvaluacionController"})
public class VerEvaluacionController extends HttpServlet {



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //DBManager db = new DBManager("192.168.1.100", "root", "123", "apolloma_Colegio");
    DBManager db = new DBManager("localhost", "root", "123", "apolloma_Colegio");
    Gson gson = new Gson();
    JsonObject jo;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idEvaluacion = request.getParameter("idEvaluacion");
        Evaluacion ev = db.readRow(Evaluacion.class, idEvaluacion, 0);
        response.setHeader("Content-Type", "application/json");        
        PrintWriter pw = response.getWriter();
        pw.println(gson.toJson(ev));
        pw.close();   
        
        
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
