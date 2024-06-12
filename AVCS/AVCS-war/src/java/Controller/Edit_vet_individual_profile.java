/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
@WebServlet(name = "Edit_vet_individual_profile", urlPatterns = {"/Edit_vet_individual_profile"})
public class Edit_vet_individual_profile extends HttpServlet {

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
        HttpSession s = request.getSession(false);
        Vet vet = (Vet)s.getAttribute("userInfo");
        request.getRequestDispatcher("Vet/edit_vet_individual_profile.jsp").include(request, response);
        try (PrintWriter out = response.getWriter()) {
        out.println("<br><br><br><table border=\"1\" width=\"80%\"><tr>\n" +
                     "    <th>User Type</th>\n" +
                     "    <th>Username</th>\n" +
                     "    <th>Age</th>\n" +
                     "    <th>Contact Number</th>\n" +
                     "    <th>Email</th>\n" +
                     "    <th>Gender</th>\n" +
                     "    <th>Password</th>\n" +
                     "    <th>Status</th>\n" +
                     "    <th>Expertise 1</th>\n" +
                     "    <th>Expertise 2</th>\n" +
                     "  </tr>");
                     out.println("<form action=\"Save_vet_individual_info\" method=\"post\">");
                     out.println("<tr>");
                     out.println("<td>Vet</td>");
                     out.println("<td>" + vet.getUsername() + "</td>");
                     out.println("<td><input type = \"text\" name = \"age\" size = \"20\"></td>");
                     out.println("<td><input type = \"text\" name = \"contactnumber\" size = \"20\"></td>");
                     out.println("<td><input type = \"text\" name = \"email\" size = \"20\"></td>");
                     out.println("<td><select id = \"gender\" name =\"gender\">\n" +
"                                <option value = \"male\">Male</option>\n" +
"                                <option value = \"female\">Female</option>\n" +
"                                <option value = \"confidential\">Confidential</option>\n" +
"                            </select></td>");
                     
                     out.println("<td><input type = \"text\" name = \"password\" size = \"20\"></td>");
                     out.println("<td>" + vet.getStatus() + "</td>");
                     out.println("<td><select id = \"expertise1\" name =\"expertise1\">\n" +
"                                <option value = \"-\">-</option>\n" +
"                                <option value = \"Dogs\">Dogs</option>\n" +
"                                <option value = \"Cat\">Cat</option>\n" +
"                                 <option value = \"Fish\">Fish</option>\n" +
"                                 <option value = \"Birds\">Birds</option>\n" +
"                                 <option value = \"Reptiles\">Reptiles</option>\n" +
"                                 <option value = \"Horse\">Horse</option>\n" +
"                                 <option value = \"Tortoise\">Tortoise</option>\n" +                             
"                            </select></td>");
                     out.println("<td><select id = \"expertise2\" name =\"expertise2\">\n" +
"                                <option value = \"-\">-</option>\n" +
"                                <option value = \"Dogs\">Dogs</option>\n" +
"                                <option value = \"Cat\">Cat</option>\n" +
"                                 <option value = \"Fish\">Fish</option>\n" +
"                                 <option value = \"Birds\">Birds</option>\n" +
"                                 <option value = \"Reptiles\">Reptiles</option>\n" +
"                                 <option value = \"Horse\">Horse</option>\n"     +
"                                 <option value = \"Tortoise\">Tortoise</option>\n" +                                     
"                            </select></td>");
                     out.println("<td><input type=\"submit\" value=\"Save\" name=\"" + "vet \"></form> ");
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
