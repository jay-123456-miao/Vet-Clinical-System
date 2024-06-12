/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.WorkingRotation;
import Model.WorkingRotationFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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
@WebServlet(name = "Load_working_rotation_receptionist", urlPatterns = {"/Load_working_rotation_receptionist"})
public class Load_working_rotation_receptionist extends HttpServlet {

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        request.getRequestDispatcher("Receptionist/view_working_rotations.jsp").include(request, response);
        try (PrintWriter out = response.getWriter()) {
            if (rotationList != null) {
                    Collections.sort(rotationList, (new RotaSortingComparator()).reversed());
                    for (WorkingRotation rotation: rotationList) {
                        out.println("<br><br><table border=\"1\" width=\"80%\" style=\"margin-left: auto; margin-right: auto;\">");
                        out.println("<tr><th>Mon ("+ rotation.getStartDate().format(formatter) +") </th> <th>Tue ("+ rotation.getStartDate().plusDays(1).format(formatter) +") </th> <th>Wed ("+ rotation.getStartDate().plusDays(2).format(formatter) +") </th> <th>Thu ("+ rotation.getStartDate().plusDays(3).format(formatter) +") </th> <th>Fri ("+ rotation.getStartDate().plusDays(4).format(formatter) +") </th> <th>Sat ("+ rotation.getStartDate().plusDays(5).format(formatter) +")</th> <th>Sun ("+ rotation.getStartDate().plusDays(6).format(formatter) +") </th></tr>");
                        out.println("<tr><td>"+ rotation.getMonVet1() +"</td><td>"+ rotation.getTueVet1() +"</td><td>"+ rotation.getWedVet1() +"</td><td>"+ rotation.getThursVet1() +"</td><td>"+ rotation.getFriVet1()+"</td><td>"+ rotation.getSatVet1() +"</td><td>"+ rotation.getSunVet1() +"</td></tr>");
                        out.println("<tr><td>"+ rotation.getMonVet2() +"</td><td>"+ rotation.getTueVet2() +"</td><td>"+ rotation.getWedVet2() +"</td><td>"+ rotation.getThursVet2() +"</td><td>"+ rotation.getFriVet2()+"</td><td>"+ rotation.getSatVet2() +"</td><td>"+ rotation.getSunVet2() +"</td></tr>");
                        out.println("<tr><td>"+ rotation.getMonVet3() +"</td><td>"+ rotation.getTueVet3() +"</td><td>"+ rotation.getWedVet3() +"</td><td>"+ rotation.getThursVet3() +"</td><td>"+ rotation.getFriVet3()+"</td><td>"+ rotation.getSatVet3() +"</td><td>"+ rotation.getSunVet3() +"</td></tr>");
                        out.println("<tr><td></td> <td></td> <td></td> <td></td> <td></td> <td align =\"right\"></td><tr>");
                        out.println("</table>");
                    }
                } else {
                    out.println("There is no working rota so far! Start creating some!");
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
