/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.managingStaff;
import Model.managingStaffFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@Entity

@WebServlet(name = "Load_staff_info", urlPatterns = {"/Load_staff_info"})
public class Load_staff_info extends HttpServlet {

    @EJB
    private managingStaffFacade managingStaffFacade;
    

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
           try
            {
            HttpSession s = request.getSession(false);
            List<managingStaff> managingStaffList =  managingStaffFacade.findAll();
            request.getRequestDispatcher("Staff/manage_staff_information.jsp").include(request, response);
            if(managingStaffList != null)
           {
               out.println("<table style=\"width:100%\">");
               out.println("<tr>"); 
               out.println("<th >Username</th>");
               out.println("<th >Password</th>");
               out.println("<th >Email</th>");
               out.println("<th >Gender</th>");
               out.println("<th >Choice</th>");
               out.println("</tr>");
               for (managingStaff staff: managingStaffList)
               {
                    
                    out.println("<tr>");
                    out.println("<td>" + staff.getUsername() + "</td>");
                    out.println("<td>" + staff.getPassword() + "</td>");
                    out.println("<td>" + staff.getEmail() + "</td>");
                    out.println("<td>" + staff.getGender() + "</td>");
                    out.println("<td></td>");
                    out.println("</tr>");                   
               }
               out.println("</table>");
           }
           else
           {
               out.println("No vet found");
           }
            s.setAttribute("staffList",managingStaffList);
    
           }
           catch(Exception e)
           {
               request.getRequestDispatcher("Staff/staffHomePage.jsp").include(request, response);
               out.println("<br><br><br><p> Error reading from database</p>");
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
