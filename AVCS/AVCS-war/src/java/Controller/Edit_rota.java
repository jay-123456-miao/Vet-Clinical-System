/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Vet;
import Model.VetFacade;
import Model.WorkingRotation;
import Model.WorkingRotationFacade;
import Utils.ChoiceMenu;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "Edit_rota", urlPatterns = {"/Edit_rota"})
public class Edit_rota extends HttpServlet {

    @EJB
    private VetFacade vetFacade;

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
        Long rotationIDTobeEdited = 0l;
        List<WorkingRotation> rotationList = workingRotationFacade.findAll();
        List<Vet> vetList = vetFacade.findAll();
        HttpSession s = request.getSession(false);
        for (WorkingRotation rotation: rotationList) {
            // check if the EDIT button of each record is clicked
            if (request.getParameter(Long.toString(rotation.getId())) != null) {
                // keep track of the index of the record to be edited
                rotationIDTobeEdited = rotation.getId();
                s.setAttribute("rotationIDTobeEdited", rotationIDTobeEdited);
                break;
            }
        }
        
        request.getRequestDispatcher("Staff/view_and_edit_work_rotations.jsp").include(request, response);
        try (PrintWriter out = response.getWriter()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (WorkingRotation rotation: rotationList) {
                if (rotation.getId() == rotationIDTobeEdited) {
                    out.println("<form action=\"Save_edited_work_rotations\" action='POST'>");
                    out.println("<table border=\"1\" width=\"80%\" style=\"margin-left: auto; margin-right: auto;\"> \n");
                    out.println("<tr><th>Mon ("+ rotation.getStartDate().format(formatter) +") </th> <th>Tue ("+ rotation.getStartDate().plusDays(1).format(formatter) +") </th> <th>Wed ("+ rotation.getStartDate().plusDays(2).format(formatter) +") </th> <th>Thu ("+ rotation.getStartDate().plusDays(3).format(formatter) +") </th> <th>Fri ("+ rotation.getStartDate().plusDays(4).format(formatter) +") </th> <th>Sat ("+ rotation.getStartDate().plusDays(5).format(formatter) +")</th> <th>Sun ("+ rotation.getStartDate().plusDays(6).format(formatter) +") </th></tr>");
                    for (int row_index=0; row_index < 3; row_index++) {
                        out.println("<tr>");
                        for (int col_index=0; col_index<7; col_index++) {
                            out.println("<td>");
                            ChoiceMenu menu = new ChoiceMenu();
                            out.println(menu.vetsDropDown_JSP(row_index, col_index, vetList));
                            out.println("</td>");
                        }
                        out.println("</tr>");
                    }
                    out.println("<tr><td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td align =\"right\"><input type=\"submit\" value=\"save\"></td><tr>");
                    out.println("</table>");
                    out.println("</form>");
                    LocalDateTime startingDate = rotation.getStartDate();
                    s.setAttribute("startingDate", startingDate);
                } else {
//                    out.println("<br><br><table border=\"1\" width=\"80%\" style=\"margin-left: auto; margin-right: auto;\">");
//                    out.println("<tr><th>Mon ("+ rotation.getStartDate().format(formatter) +") </th> <th>Tue ("+ rotation.getStartDate().plusDays(1).format(formatter) +") </th> <th>Wed ("+ rotation.getStartDate().plusDays(2).format(formatter) +") </th> <th>Thu ("+ rotation.getStartDate().plusDays(3).format(formatter) +") </th> <th>Fri ("+ rotation.getStartDate().plusDays(4).format(formatter) +") </th> <th>Sat ("+ rotation.getStartDate().plusDays(5).format(formatter) +")</th> <th>Sun ("+ rotation.getStartDate().plusDays(6).format(formatter) +") </th></tr>");                    out.println("<tr><td>"+ rotation.getMonVet1() +"</td><td>"+ rotation.getTueVet1() +"</td><td>"+ rotation.getWedVet1() +"</td><td>"+ rotation.getThursVet1() +"</td><td>"+ rotation.getFriVet1()+"</td><td>"+ rotation.getSatVet1() +"</td><td>"+ rotation.getSunVet1() +"</td></tr>");
//                    out.println("<tr><td>"+ rotation.getMonVet2() +"</td><td>"+ rotation.getTueVet2() +"</td><td>"+ rotation.getWedVet2() +"</td><td>"+ rotation.getThursVet2() +"</td><td>"+ rotation.getFriVet2()+"</td><td>"+ rotation.getSatVet2() +"</td><td>"+ rotation.getSunVet2() +"</td></tr>");
//                    out.println("<tr><td>"+ rotation.getMonVet3() +"</td><td>"+ rotation.getTueVet3() +"</td><td>"+ rotation.getWedVet3() +"</td><td>"+ rotation.getThursVet3() +"</td><td>"+ rotation.getFriVet3()+"</td><td>"+ rotation.getSatVet3() +"</td><td>"+ rotation.getSunVet3() +"</td></tr>");
//                    out.println("</table>");
                }
            
        };
            
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
