/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "Search_vet_info", urlPatterns = {"/Search_vet_info"})
public class Search_vet_info extends HttpServlet {

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
        String vet_username = request.getParameter("vet_username");
        Vet vet = vetFacade.searchByUsername(vet_username);
        request.getRequestDispatcher("Staff/manage_vet_info.jsp").include(request, response);
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            {
                if(vet != null)
                {                   
                    s.setAttribute("vetfound", vet);
                    out.println("<table style=\"width:80%\">");
                    out.println("<tr>"); 
                    out.println("<th >Username</th>");
                    out.println("<th >Password</th>");
                    out.println("<th >Email</th>");
                    out.println("<th >Gender</th>");
                    out.println("<th >Contact Number</th>");
                    out.println("<th >Age</th>");
                    out.println("<th >Status</th>");
                    out.println("<th >Expertise 1</th>");
                    out.println("<th >Expertise 2</th>");
                    out.println("<th >Choice</th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td style=\"width:14%\">" + vet.getUsername() + "</td>");
                    out.println("<td style=\"width:14%\">" + vet.getPassword() + "</td>");
                    out.println("<td style=\"width:14%\">" + vet.getEmail() + "</td>");
                    out.println("<td style=\"width:14%\">" + vet.getGender() + "</td>");
                    out.println("<td style=\"width:14%\">" + vet.getContactnumber() + "</td>");
                    out.println("<td style=\"width:14%\">" + vet.getAge() + "</td>");
                    out.println("<td style=\"width:14%\">" + vet.getStatus() + "</td>");
                    out.println("<td style=\"width:14%\">" + vet.getExpertise1() + "</td>");
                    out.println("<td style=\"width:14%\">" + vet.getExpertise2() + "</td>");
                    out.println("<td><form action=\"Edit_vet_info\" method=\"post\"><input type=\"submit\" value=\"Edit\" name=\""+vet.getId()+"\"></form>");
                    out.println("<form action=\"Delete_vet_info\" method=\"post\"><input type=\"submit\" value=\"Delete\" name=\""+vet.getId()+"\"></form></td>");
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
