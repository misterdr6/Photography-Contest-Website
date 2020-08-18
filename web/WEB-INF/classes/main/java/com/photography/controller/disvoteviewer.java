/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photography.controller;

import com.photography.beans.image;
import com.photography.beans.imagedao;
import com.photography.model.userdb;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manish
 */
public class disvoteviewer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet disvoteviewer</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet disvoteviewer at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
            String idnum= request.getParameter("id");
           // email="manishkumargunda@gmail.com";
            int id = 0;
              try {
         id=Integer.parseInt(idnum);
   }catch (NumberFormatException e){
       System.out.println("not a number"); 
   }
            HttpSession session = request.getSession();
            String name=(String)session.getAttribute("use");
                   // HttpSession session = request.getSession(true);

//                   ArrayList<image> totallist =new ArrayList<>();
//   
                      ArrayList<image> likelist =new ArrayList<image>(3);
                    likelist=(ArrayList<image>) session.getAttribute("likelist");
//            //HttpSession session = request.getSession(true);
//    totallist=(ArrayList<image>) session.getAttribute("totallist");
   // processRequest(request, response);
    //if(!likelist.isEmpty()){
                imagedao dao = new imagedao();
                image im=new image();
            ArrayList<image> imagelist =new ArrayList<image>();
        try {
            imagelist = dao.get();
           
        } catch (SQLException ex) {
            Logger.getLogger(vote.class.getName()).log(Level.SEVERE, null, ex);
        }
                userdb db=new userdb();
            //id=0;
            boolean register;
        try 
        {
            register=db.dislikeimageview(name,imagelist.get(id));
           // 
             
            if(register)
            {
                
                //likelist.remove(likelist.indexOf(imagelist.get(id)));
                session.setAttribute("likelist", likelist);
                 request.setAttribute("data", register); 
//             RequestDispatcher rd; 
//            // processRequest(request, response);
//              rd = request.getRequestDispatcher("displayphotoview");
//                  rd.forward(request, response);
                 response.sendRedirect(request.getContextPath()+"/displayphotoview");
            }
            else 
            {
                             request.setAttribute("data", register); 
             //processRequest(request, response);
//             RequestDispatcher rd; 
//              rd = request.getRequestDispatcher("displayphotoview");
//                  rd.forward(request, response);
                 response.sendRedirect(request.getContextPath()+"/displayphotoview");
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(vote.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
