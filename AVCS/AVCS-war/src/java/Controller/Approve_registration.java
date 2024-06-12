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
@WebServlet(name = "Approve_registration", urlPatterns = {"/Approve_registration"})
public class Approve_registration extends HttpServlet {

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
        
        
        try (PrintWriter out = response.getWriter()) {
           List<Vet> UnapprovedVetList= vetFacade.searchNotApproved();
           if(UnapprovedVetList != null)
           {
               for(Vet vet:UnapprovedVetList)
               {
                   if(request.getParameter("vet "+Long.toString(vet.getId()))!=null)
                   {
                       vetFacade.approveRegistration(vet.getId(), "Approved");
                   }
               }
           }
           List<Receptionist> UnapprovedReceptionistList= receptionistFacade.searchNotApproved();
           if(UnapprovedReceptionistList != null)
           {
               for(Receptionist receptionist:UnapprovedReceptionistList)
               {
                   if(request.getParameter("rec "+Long.toString(receptionist.getId()))!=null)
                   {
                       receptionistFacade.approveRegistration(receptionist.getId(), "Approved");
                   }
               }
           }
           request.getRequestDispatcher("Load_registration_info").forward(request, response);
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
