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
@WebServlet(name = "Load_profile", urlPatterns = {"/Load_profile"})
public class Load_profile extends HttpServlet {

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
        HttpSession s = request.getSession(false);
        String username = (String)s.getAttribute("username");
        String usertype = (String)s.getAttribute("usertype");
        try (PrintWriter out = response.getWriter()) {
           if(usertype.equals("vet"))
           {
               Vet vet = vetFacade.searchByUsername(username);
               request.getRequestDispatcher("Vet/edit_vet_individual_profile.jsp").include(request, response);
               if(vet != null)
               {
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
               
                    out.println("<tr>");
                    out.println("<td>Vet</td>");
                    out.println("<td>" + vet.getUsername() + "</td>");
                    out.println("<td>" + vet.getAge() + "</td>");
                    out.println("<td>" + vet.getContactnumber() + "</td>");
                    out.println("<td>" + vet.getEmail() + "</td>");
                    out.println("<td>" + vet.getGender() + "</td>");
                    out.println("<td>" + vet.getPassword() + "</td>");
                    out.println("<td>" + vet.getStatus() + "</td>");
                    out.println("<td>" + vet.getExpertise1() + "</td>");
                    out.println("<td>" + vet.getExpertise2() + "</td>");
                    out.println("<td><form action=\"Edit_vet_individual_profile\" method=\"post\"><input type=\"submit\" value=\"EDIT\" name=\"" + "vet \"></form> ");
                    out.println("</tr>");
                    out.println("</table>");
               }
           }
           else if(usertype.equals("receptionist"))
           {
               Receptionist receptionist = receptionistFacade.searchByUsername(username);
               request.getRequestDispatcher("Receptionist/edit_receptionist_individual_profile.jsp").include(request, response);
               if(receptionist != null)
               {
                    out.println("<br><br><br><table border=\"1\" width=\"80%\"><tr>\n" +
                    "    <th>User Type</th>\n" +
                    "    <th>Username</th>\n" +
                    "    <th>Age</th>\n" +
                    "    <th>Contact Number</th>\n" +
                    "    <th>Email</th>\n" +
                    "    <th>Gender</th>\n" +
                    "    <th>Password</th>\n" +
                    "    <th>Status</th>\n" +
                    "  </tr>");
               
                    out.println("<tr>");
                    out.println("<td>Receptionist</td>");
                    out.println("<td>" + receptionist.getUsername() + "</td>");
                    out.println("<td>" + receptionist.getAge() + "</td>");
                    out.println("<td>" + receptionist.getContactnumber() + "</td>");
                    out.println("<td>" + receptionist.getEmail() + "</td>");
                    out.println("<td>" + receptionist.getGender() + "</td>");
                    out.println("<td>" + receptionist.getPassword() + "</td>");
                    out.println("<td>" + receptionist.getStatus() + "</td>");
                    out.println("<td><form action=\"Edit_receptionist_inidividual_profile\" method=\"post\"><input type=\"submit\" value=\"EDIT\" name=\"" + "receptionist \"></form> ");
                    out.println("</tr>");
                    out.println("</table>");
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
