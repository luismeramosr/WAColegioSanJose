/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import idat.edu.pe.db.DBManager;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author littman
 */
@WebServlet(name = "ReporteEvaluacionController", urlPatterns = {"/ReporteEvaluacionController"})
public class ReporteEvaluacionController extends HttpServlet {



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DBManager db = new DBManager("localhost", "root", "123", "apolloma_Colegio");
    Gson gson = new Gson();
    JsonObject jo;
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
        jo = gson.fromJson(request.getReader(), JsonObject.class);
        File reporte = new File(request.getServletContext().getRealPath("ReporteEvaluacion.jasper"));
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("p_idevaluacion", jo.get("idEvaluacion").getAsInt());
        parametros.put("p_idalumno", jo.get("idAlumno").getAsString());
        parametros.put("p_curso", jo.get("curso").getAsString());
        parametros.put("p_seccion", jo.get("seccion").getAsString());
        try{
            byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(),
                    parametros,db.conn);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            OutputStream output = response.getOutputStream();
            output.write(bytes, 0, bytes.length);
            output.flush();
            output.close();
      
            
        } catch (JRException ex) {
           
        }
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
