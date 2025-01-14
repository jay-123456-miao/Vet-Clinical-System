/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Receptionist;
import Model.WorkingRotation;
import Model.WorkingRotationFacade;
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
@WebServlet(name = "Delete_working_rotations", urlPatterns = {"/Delete_working_rotations"})
public class Delete_working_rotations extends HttpServlet {

    @EJB
    private WorkingRotationFacade workingRotationFacade;

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
        List<WorkingRotation> rotationList = workingRotationFacade.findAll();
        long rotationIDToBeDeleted = 0l;
        for (WorkingRotation rotation: rotationList) {
            if (request.getParameter(Long.toString(rotation.getId())) != null) {
                rotationIDToBeDeleted = rotation.getId();
                break;
            }
        }
        WorkingRotation rotationToBeDeleted = workingRotationFacade.find(rotationIDToBeDeleted);
  
        try (PrintWriter out = response.getWriter()) {
            if (rotationToBeDeleted != null) {
                //do the database backend logic here
                workingRotationFacade.remove(rotationToBeDeleted );
                request.getRequestDispatcher("View_work_rotations").forward(request, response);
            } else {
                request.getRequestDispatcher("create_working_rotatons2.jsp").forward(request, response);
                out.println("Failed to find the desired receptionist, try again!");
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
