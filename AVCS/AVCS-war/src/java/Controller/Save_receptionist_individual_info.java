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
@WebServlet(name = "Save_receptionist_individual_info", urlPatterns = {"/Save_receptionist_individual_info"})
public class Save_receptionist_individual_info extends HttpServlet {

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
        HttpSession s = request.getSession(false);
        String usertype = (String)s.getAttribute("usertype");
        if(usertype.equals("receptionist"))
        {
            String age = request.getParameter("age");
            String contactnumber = request.getParameter("contactnumber");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String password = request.getParameter("password");
            Receptionist receptionistedit = (Receptionist)s.getAttribute("userInfo");

            try (PrintWriter out = response.getWriter()) {
                try{
                   if(email.length()>25 || email.length() == 0)
                   {
                       throw new Exception();
                   }
                   if(password.length()>10 || password.length()==0)
                   {
                       throw new Exception();
                   }
                   int ageInt = Integer.parseInt(age);
                   long contactnumberLong = Long.parseLong(contactnumber);
                   int passwordInt = Integer.parseInt(password);
                   if(ageInt<=0)
                   {
                       throw new Exception();
                   }
                   receptionistedit.setAge(ageInt);
                   receptionistedit.setContactnumber(contactnumberLong);
                   receptionistedit.setEmail(email);
                   receptionistedit.setGender(gender);
                   receptionistedit.setPassword(passwordInt);
                   s.setAttribute("userInfo", receptionistedit);
                   receptionistFacade.edit(receptionistedit);
                   request.getRequestDispatcher("Load_profile").include(request, response);
               }
               catch(Exception e)
               {
                   request.getRequestDispatcher("Receptionist/edit_receptionist_individual_profile.jsp").include(request, response);
                   out.println("Invalid user input! Pleaase follow the guideline above !!!");
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
