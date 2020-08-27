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
@WebServlet(name = "ReporteNotasController", urlPatterns = {"/ReporteNotasController"})
public class ReporteNotasController extends HttpServlet {

    DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File reporte = new File(request.getServletContext().getRealPath("ReporteNota.jasper"));
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("p_idevaluacion", Integer.parseInt(request.getParameter("Evaluacion")));
        parametros.put("p_curso", request.getParameter("Curso"));
        parametros.put("p_seccion", request.getParameter("Seccion"));
        try{
            byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(),
                    parametros,db.getConnection());
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            OutputStream output = response.getOutputStream();
            output.write(bytes, 0, bytes.length);
            output.flush();
            output.close();      
        } catch (JRException err) {
            System.out.println(err);
        }
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
