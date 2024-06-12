/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Receptionist;
import Model.Vet;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Edit_receptionist_info", urlPatterns = {"/Edit_receptionist_info"})
public class Edit_receptionist_info extends HttpServlet {

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
        HttpSession s = request.getSession();
        Receptionist receptionistedit = (Receptionist)s.getAttribute("receptionistfound");
        try (PrintWriter out = response.getWriter()) {
            request.getRequestDispatcher("Staff/manage_receptionist_information.jsp").include(request, response);
            out.println("<table style=\"width:80%\">");
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
            out.println("<tr>");
            out.println("<form action= \"Save_receptionist_info\" method=\"post\">");
            out.println("<td style=\"width:14%\">"+receptionistedit.getUsername()+"</td>");
            out.println("<td style=\"width:14%\"><input type = \"text\" name = \"password\" size = \"20\"></td>");
            out.println("<td style=\"width:14%\"><input type = \"text\" name = \"email\" size = \"20\"></td>");
                        out.println("<td>\n" +
"                            <select id = \"gender\" name =\"gender\">\n" +
"                                <option value = \"male\">Male</option>\n" +
"                                <option value = \"female\">Female</option>\n" +
"                                <option value = \"confidential\">Confidential</option>\n" +
"                            </select>\n" +
"                        </td>    ");
            out.println("<td style=\"width:14%\"><input type = \"text\" name = \"contactnumber\" size = \"20\"></td>");
            out.println("<td style=\"width:14%\"><input type = \"text\" name = \"age\" size = \"20\"></td>");
            out.println("<td style=\"width:14%\">"+receptionistedit.getStatus()+"</td>");
            out.println("<td><input type=\"submit\" value=\"Save\" name=\"save\"></form>");
            out.println("</tr>");
            out.println("</form>");
            out.println("</table>");
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
