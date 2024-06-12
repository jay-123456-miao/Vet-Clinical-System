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
@WebServlet(name = "Save_receptionist_info", urlPatterns = {"/Save_receptionist_info"})
public class Save_receptionist_info extends HttpServlet {

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
        String new_password = request.getParameter("password");
        String new_email = request.getParameter("email");
        String new_gender = request.getParameter("gender");
        String new_contactnumber = request.getParameter("contactnumber");
        String new_age = request.getParameter("age");
        
        HttpSession s = request.getSession();
       
        try (PrintWriter out = response.getWriter()) {
            try
            {
                if (new_password.length()>10 || new_password.length()==0) //error 1 password lenghth is longer than 10 or empty
                    {
                        throw new Exception();
                    }
                if (new_email.length()>25 || new_email.length()==0) //error 3 email lenghth is longer than 25 or empty
                {
                    throw new Exception();
                }
                int intage = Integer.parseInt(new_age);
                int intpassword = Integer.parseInt(new_password);
                long Long_contactnumber = Long.parseLong(new_contactnumber);
                if (intage <= 0)
                {
                    throw new Exception(); //error 8 age is zero or negative 
                }
                Receptionist receptionistedit = (Receptionist)s.getAttribute("receptionistfound");
                receptionistedit.setAge(intage);
                receptionistedit.setPassword(intpassword);
                receptionistedit.setEmail(new_email);
                receptionistedit.setGender(new_gender);
                receptionistedit.setContactnumber(Long_contactnumber);
                receptionistFacade.edit(receptionistedit);
                request.getRequestDispatcher("Load_receptionist_info").forward(request, response);
            }
            
             catch(Exception e)
            {
                request.getRequestDispatcher("Staff/manage_receptionist_information.jsp").include(request, response);
                out.println("Invalid user input please follow the guideline set above !!!");
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