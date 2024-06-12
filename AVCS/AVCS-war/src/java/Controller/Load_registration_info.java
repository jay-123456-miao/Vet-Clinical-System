/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Receptionist;
import Model.ReceptionistFacade;
import Model.Vet;
import Model.VetFacade;
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
@WebServlet(name = "Load_registration_info", urlPatterns = {"/Load_registration_info"})
public class Load_registration_info extends HttpServlet {

    @EJB
    private ReceptionistFacade receptionistFacade;

    @EJB
    private VetFacade vetFacade;
    

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
        List<Vet>UnapprovedVetList = vetFacade.searchNotApproved();
        List<Receptionist>UnapprovedReceptionistList = receptionistFacade.searchNotApproved();
        request.getRequestDispatcher("Staff/approve_registration.jsp").include(request, response);
        try (PrintWriter out = response.getWriter()) {
            if (UnapprovedVetList != null) {
                out.println("<br><p>Vet Registration Table :</p><br><br>");
                out.println("<br><br><br><table border=\"1\" width=\"80%\"><tr>\n" +
                "    <th>User Type</th>\n" +
                "    <th>Username</th>\n" +
                "    <th>Action</th>\n" +
                "  </tr>");
                for (Vet vet: UnapprovedVetList) {
                    out.println("<tr>");
                    out.println("<td>Vet</td>");
                    out.println("<td>" + vet.getUsername() + "</td>");
                    out.println("<td><form action=\"Approve_registration\" method=\"post\"><input type=\"submit\" value=\"Approve\" name=\"" + "vet " + String.valueOf(vet.getId()) + "\"></form> ");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<br><br>");
            }
            else
            {
                out.println("<br><p>Vet Registration Table :</p><br><br>");
                out.println("<p>No vet to approve !!!</p><br><br>");
            }
            
            if (UnapprovedReceptionistList != null) {
                out.println("<br><p>Receptionist Registration Table :</p><br><br>");
                out.println("<br><br><br><table border=\"1\" width=\"80%\"><tr>\n" +
                "    <th>User Type</th>\n" +
                "    <th>Username</th>\n" +
                "    <th>Action</th>\n" +
                "  </tr>");
                for (Receptionist receptionist: UnapprovedReceptionistList) {
                    out.println("<tr>");
                    out.println("<td>Receptionist</td>");
                    out.println("<td>" + receptionist.getUsername() + "</td>");
                    out.println("<td><form action=\"Approve_registration\" method=\"post\"><input type=\"submit\" value=\"Approve\" name=\"" + "rec " +String.valueOf(receptionist.getId()) + "\"></form> ");
                    out.println("</tr>");
                }
                out.println("</table>");
            
            }
             else
            {
                out.println("<br><p>Receptionist Registration Table :</p><br><br>");
                out.println("<p>No receptionist to approve !!!</p>");
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
