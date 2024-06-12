/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Receptionist;
import Model.ReceptionistFacade;
import Model.Vet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "Load_receptionist_info", urlPatterns = {"/Load_receptionist_info"})
public class Load_receptionist_info extends HttpServlet {

    @EJB
    private ReceptionistFacade receptionistFacade;

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
        List<Receptionist> receptionistList = receptionistFacade.findAll();
        request.getRequestDispatcher("Staff/manage_receptionist_information.jsp").include(request, response);
        try (PrintWriter out = response.getWriter()) {
           if(receptionistList != null)
           {
               out.println("<table style=\"width:100%\">");
               out.println("<tr>"); 
               out.println("<th >Username</th>");
               out.println("<th >Password</th>");
               out.println("<th >Email</th>");
               out.println("<th >Gender</th>");
               out.println("<th >Contact Number</th>");
               out.println("<th >Age</th>");
               out.println("<th >Status</th>");
               out.println("<th >Choice</th>");
               out.println("</tr>");
               for (Receptionist receptionist: receptionistList)
               {
                    out.println("<tr>");
                    out.println("<td>" + receptionist.getUsername() + "</td>");
                    out.println("<td>" + receptionist.getPassword() + "</td>");
                    out.println("<td>" + receptionist.getEmail() + "</td>");
                    out.println("<td>" + receptionist.getGender() + "</td>");
                    out.println("<td>" + receptionist.getContactnumber() + "</td>");
                    out.println("<td>" + receptionist.getAge() + "</td>");
                    out.println("<td>" + receptionist.getStatus() + "</td>");
                    out.println("<td></td>");
                    out.println("</tr>");
                    
               }
               out.println("</table>");
           }
           
           else
           {
               out.println("No record found");
           }
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
        processRequest(request, response);
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
