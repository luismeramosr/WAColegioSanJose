/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import idat.edu.pe.models.Evaluacion;
import idat.edu.pe.models.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson jo = new Gson();
        Usuario user1 = new Usuario("Usuario150","asdad","asdad","123456789","asd321asd231","000000");
        response.setHeader("Content-Type", "application/json");
        System.out.println(request.getQueryString());
        PrintWriter pw = response.getWriter();
        pw.println(jo.toJson(user1));
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        jo = gson.fromJson(request.getReader(), JsonObject.class);
        System.out.println(jo.toString());
    }
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
