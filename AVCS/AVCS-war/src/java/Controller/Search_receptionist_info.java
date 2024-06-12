/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Receptionist;
import Model.ReceptionistFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
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
@WebServlet(name = "Search_receptionist_info", urlPatterns = {"/Search_receptionist_info"})
public class Search_receptionist_info extends HttpServlet {

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
        String receptionist_username = request.getParameter("receptionist_username");
        Receptionist receptionist = receptionistFacade.searchByUsername(receptionist_username);
        request.getRequestDispatcher("Staff/manage_receptionist_information.jsp").include(request, response);
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            {
                if(receptionist != null)
                {
                    s.setAttribute("receptionistfound", receptionist);
                    out.println("<table style=\"width:100%\">");
                    out.println("<tr>");
                    out.println("<td style=\"width:14%\">" + receptionist.getUsername() + "</td>");
                    out.println("<td style=\"width:14%\">" + receptionist.getPassword() + "</td>");
                    out.println("<td style=\"width:14%\">" + receptionist.getEmail() + "</td>");
                    out.println("<td style=\"width:14%\">" + receptionist.getGender() + "</td>");
                    out.println("<td style=\"width:14%\">" + receptionist.getContactnumber() + "</td>");
                    out.println("<td style=\"width:14%\">" + receptionist.getAge() + "</td>");
                    out.println("<td style=\"width:14%\">" + receptionist.getStatus() + "</td>");
                    out.println("<td><form action=\"Edit_receptionist_info\" method=\"post\"><input type=\"submit\" value=\"Edit\" name=\""+receptionist.getId()+"\"></form>");
                    out.println("<form action=\"Delete_receptionist_info\" method=\"post\"><input type=\"submit\" value=\"Delete\" name=\""+receptionist.getId()+"\"></form></td>");
                    out.println("</tr>");
                    out.println("</table>");
                }
                else
                {
                    out.println("No such username found");
                }
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
