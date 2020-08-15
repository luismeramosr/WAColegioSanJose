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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luisr
 */
@WebServlet(name = "EvaluacionController", urlPatterns = {"/EvaluacionController"})
public class EvaluacionController extends HttpServlet {

    //DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");
    DBManager db = new DBManager("localhost", "3306", "root", "123", "apolloma_Colegio");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Evaluacion> ev = db.readTable(Evaluacion.class);
        response.setHeader("Content-Type", "application/json");        
        PrintWriter pw = response.getWriter();
        pw.println(gson.toJson(ev));
        pw.close();
      
    }
    Gson gson = new Gson();
    JsonObject jo;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        jo = gson.fromJson(request.getReader(), JsonObject.class);
        String seccion = jo.get("Seccion").getAsString();
        String curso = jo.get("Curso").getAsString();
        String data = jo.get("data").getAsJsonObject().toString();
        System.out.println(data);
        Evaluacion newev = new Evaluacion(seccion, curso, data);
        db.insertRow(newev);

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
