/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.managingStaff;
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
@WebServlet(name = "Edit_staff_info", urlPatterns = {"/Edit_staff_info"})
public class Edit_staff_info extends HttpServlet {

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
        Long staffIdUpdate = 0l;
        HttpSession s = request.getSession();
        managingStaff staffUpdate = (managingStaff)s.getAttribute("staffobj");
        try (PrintWriter out = response.getWriter()) {
            if(Long.toString(staffUpdate.getId())!= null)
            {
                request.getRequestDispatcher("Staff/manage_staff_information.jsp").include(request, response);
                out.println(
                             
                            "<table style=\"border-collapse: collapse; border: 1px solid black;\">\n" +
                            "    <tr>\n" +
                            "       <th>password</th>\n" +
                            "       <th>email</th>\n" +
                            "       <th>gender</th>\n" +
                            "       <th>choice</th>\n" +
                            "    </tr>\n" +
                            "<form action= \"Save_staff_info\" method=\"post\">\n"+
                            "    <tr>\n" +
                            "    <td><input type=\"text\" name=\"password\" size=\"10\"></td>\n" +
                            "    <td><input type=\"text\" name=\"email\" size=\"10\"></td>\n" +
                            "<td>\n" +
                            "    <select id = \"gender\" name =\"gender\">\n" +
                            "        <option value = \"F\">Female</option>\n" +
                            "        <option value = \"M\">Male</option>\n" +
                            "    </select>\n" +
                            "</td>\n"+
                            "        <td><input type= \"submit\" value= \"Save\" name= \""+String.valueOf(staffUpdate.getId())+"\"></td>\n"+
                            "    </tr>\n" +
                            "</form>\n"+
                            "</table>");
                        
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
