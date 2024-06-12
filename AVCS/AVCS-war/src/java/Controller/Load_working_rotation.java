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
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class RotaSortingComparator implements Comparator<WorkingRotation> {
 
    @Override
    public int compare(WorkingRotation rota1, WorkingRotation rota2) {
        return rota1.getStartDate().compareTo(rota2.getStartDate());
    }
}

/**
 *
 * @author ASUS
 */
@WebServlet(name = "Load_working_rotation", urlPatterns = {"/Load_working_rotation"})
public class Load_working_rotation extends HttpServlet {

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
        HttpSession s = request.getSession(false);
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day= request.getParameter("day");
        int year_int;
        int month_int;
        int day_int;
        boolean invalidDate = false;
        boolean dateNotMonday = false;
        LocalDateTime startingDate = null;
        List<Vet> vetList = vetFacade.findAll();
        try {
            year_int = Integer.parseInt(year);
            month_int = Integer.parseInt(month);
            day_int = Integer.parseInt(day);
            startingDate = LocalDateTime.of(year_int, month_int, day_int, 0, 0, 0, 0);
            if (!startingDate.getDayOfWeek().toString().equals("MONDAY")) {
                dateNotMonday = true;    
            }
        } catch (Exception e) {
            invalidDate = true;
        }
       
        try (PrintWriter out = response.getWriter()) {
            if (!invalidDate && !dateNotMonday)
            {
               request.getRequestDispatcher("create_working_rotation2.jsp").include(request, response);
               out.println("<form action=\"Create_working_rota\" action='POST'>");
               out.println("<table border=\"1\" width=\"80%\" style=\"margin-left: auto; margin-right: auto;\"> \n");
               out.println("<tr><th>Mon</th> <th>Tue</th> <th>Wed</th> <th>Thu</th> <th>Fri</th> <th>Sat</th> <th>Sun</th></tr>\n");
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
                out.println("<tr><td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td align =\"right\"><input type=\"submit\" value=\"Create\"></td><tr>");
                out.println("</table>");
                out.println("</form>");
                out.println("<br><br><br>");
                s.setAttribute("startingDate", startingDate);
//                Collections.sort(rotationList, (new RotaSortingComparator()).reversed());
//                for (WorkingRotation rotation: rotationList) {
//                    out.println("<br><br><table border=\"1\" width=\"80%\" style=\"margin-left: auto; margin-right: auto;\">");
//                    out.println("<tr><th>Mon ("+ rotation.getStartDate().format(formatter) +") </th> <th>Tue ("+ rotation.getStartDate().plusDays(1).format(formatter) +") </th> <th>Wed ("+ rotation.getStartDate().plusDays(2).format(formatter) +") </th> <th>Thu ("+ rotation.getStartDate().plusDays(3).format(formatter) +") </th> <th>Fri ("+ rotation.getStartDate().plusDays(4).format(formatter) +") </th> <th>Sat ("+ rotation.getStartDate().plusDays(5).format(formatter) +")</th> <th>Sun ("+ rotation.getStartDate().plusDays(6).format(formatter) +") </th></tr>");
//                    out.println("<tr><td>"+ rotation.getMonVet1() +"</td><td>"+ rotation.getTueVet1() +"</td><td>"+ rotation.getWedVet1() +"</td><td>"+ rotation.getThursVet1() +"</td><td>"+ rotation.getFriVet1()+"</td><td>"+ rotation.getSatVet1() +"</td><td>"+ rotation.getSunVet1() +"</td></tr>");
//                    out.println("<tr><td>"+ rotation.getMonVet2() +"</td><td>"+ rotation.getTueVet2() +"</td><td>"+ rotation.getWedVet2() +"</td><td>"+ rotation.getThursVet2() +"</td><td>"+ rotation.getFriVet2()+"</td><td>"+ rotation.getSatVet2() +"</td><td>"+ rotation.getSunVet2() +"</td></tr>");
//                    out.println("<tr><td>"+ rotation.getMonVet3() +"</td><td>"+ rotation.getTueVet3() +"</td><td>"+ rotation.getWedVet3() +"</td><td>"+ rotation.getThursVet3() +"</td><td>"+ rotation.getFriVet3()+"</td><td>"+ rotation.getSatVet3() +"</td><td>"+ rotation.getSunVet3() +"</td></tr>");
//                    out.println("</table>");
//            }
            }
            if(invalidDate)
            {
                request.getRequestDispatcher("create_working_rotation.jsp").include(request, response);
                out.println(" The date input is invalid. Please refer to the above notes for guidance" );
            }
            if(dateNotMonday)
            {
                request.getRequestDispatcher("create_working_rotation.jsp").include(request, response);
                out.println(" The date input is not Monday. You can only create working rotation starting from Monday" );
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
