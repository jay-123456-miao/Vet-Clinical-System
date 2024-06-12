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
@WebServlet(name = "Manage_staff_info", urlPatterns = {"/Manage_staff_info"})
public class Manage_staff_info extends HttpServlet {

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
        String username = request.getParameter("username");
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            try{
                List<managingStaff> managingStaffList =  (List)s.getAttribute("staffList");
            managingStaff found = null;
            for(managingStaff staff: managingStaffList)
            {
                if(staff.getUsername().equals(username))
                {
                    found = staff;
                    s.setAttribute("staffobj", found);
                    break;
                }
            }
            if (found == null)
            {
                throw new Exception();
            }
            request.getRequestDispatcher("Staff/manage_staff_information.jsp").include(request, response);
            out.println("<table style=\"border-collapse: collapse; border: 1px solid black;\">\n" +
                        "    <tr>\n" +
                        "       <th>username</th>\n" +
                        "       <th>password</th>\n" +
                        "       <th>email</th>\n" +
                        "       <th>gender</th>\n" +
                        "       <th  colspan= 2 >choice</th>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "        <td>" + found.getUsername() + "</td>\n" +
                        "        <td>" + found.getPassword() + "</td>\n" +
                        "        <td>" + found.getEmail() + "</td>\n" +
                        "        <td>" + found.getGender() + "</td>\n" +
                        "        <td><form action= \"Edit_staff_info\" method=\"post\"><input type= \"submit\" value= \"Edit\" name= \""+String.valueOf(found.getId())+"\"></form> </td>\n" +
                        "        <td><form action=\"Delete_staff_info\" method=\"post\"><input type=\"submit\" value=\"Delete\" name= \""+String.valueOf(found.getId())+"\"></form></td>\n" +
                        "    </tr>\n" +
                        "</table>");
            }
            catch(Exception e)
            {
                request.getRequestDispatcher("Staff/manage_staff_information.jsp").include(request, response);
                out.println("The username is not found in the system");
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
