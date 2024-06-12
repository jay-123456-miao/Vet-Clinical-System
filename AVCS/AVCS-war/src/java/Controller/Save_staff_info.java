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
@WebServlet(name = "Save_staff_info", urlPatterns = {"/Save_staff_info"})
public class Save_staff_info extends HttpServlet {

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
        HttpSession s = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try{   
                managingStaff staff = (managingStaff)s.getAttribute("staffobj");
                String new_password = request.getParameter("password");
                String new_email = request.getParameter("email");
                String new_gender = request.getParameter("gender");
                if (new_password.length() == 0 || new_password.length()>10)
                {
                    throw new Exception();
                }
                if (new_email.length() == 0 || new_email.length()>25)
                {
                    throw new Exception();
                }
                int intpassword = Integer.parseInt(new_password);
                staff.setPassword(intpassword);
                staff.setEmail(new_email);
                staff.setGender(new_gender);
                s.setAttribute("staffobj", staff);
                managingStaffFacade.edit(staff);
                
//                managingStaffFacade.updateById(staff.getId(), intpassword, new_email, new_gender);
                request.getRequestDispatcher("Load_staff_info").forward(request, response);
            }
            catch(Exception e)
            {
                request.getRequestDispatcher("Staff/manage_staff_information.jsp").include(request, response);
                out.println("Invalid Input");
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
