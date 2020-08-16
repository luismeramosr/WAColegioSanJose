/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import idat.edu.pe.db.DBManager;
import idat.edu.pe.models.Alumno;
import idat.edu.pe.models.Evaluacion;
import idat.edu.pe.models.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.parser.JSONParser;

/**
 *
 * @author luisr
 */
@WebServlet(name = "Test", urlPatterns = {"/api"})
public class Test extends HttpServlet {

    //DBManager db = new DBManager("gator4125.hostgator.com", "apolloma_root", "!Rg[5b1mzuOV", "apolloma_Colegio");
    DBManager db = new DBManager("192.168.1.100", "root", "123", "apolloma_Colegio");    
    //DBManager db= new DBManager("localhost","3306","root","123","apolloma_Colegio");
    Gson gson = new Gson();  
    JsonObject jo;  
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        String seccion = request.getParameter("idSeccion");
        System.out.println(seccion);
        List<Alumno> alumnos = db.readTable(Alumno.class, seccion, 1);
        response.setHeader("Content-Type", "application/json");        
        PrintWriter pw = response.getWriter();
        pw.println(gson.toJson(alumnos));
        pw.close();
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
        jo = gson.fromJson(request.getReader(), JsonObject.class);
        System.out.println(jo.get("name"));
    }
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
