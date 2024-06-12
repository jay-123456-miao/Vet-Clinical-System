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
import java.time.LocalTime;
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
@WebServlet(name = "Load_managing_staff_report", urlPatterns = {"/Load_managing_staff_report"})
public class Load_managing_staff_report extends HttpServlet {

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
        List<managingStaff> staffList = managingStaffFacade.findAll();
        int staff_count = 0;
        int male_count = 0;
        int female_count = 0;

        LocalTime currentTime = LocalTime.now();
        
        
        request.getRequestDispatcher("Staff/managing_staff_report.jsp").include(request, response);
        
        try (PrintWriter out = response.getWriter()) {
           out.println("<p> The current local time is : " + currentTime + " </p>");
           if (staffList != null)
           {
               for (managingStaff staff: staffList)
               {
                   staff_count = staff_count + 1;
                   if(staff.getGender().equals("M"))
                   {
                       male_count = male_count +1;
                   }
                   if(staff.getGender().equals("F"))
                   {
                       female_count = female_count +1;
                   }
               }
               double male_percentage = (male_count*1.0/staff_count*1.0)*100;
               double  female_percentage = (female_count*1.0/staff_count*1.0)*100;
               String maleformattedPercentage = String.format("%.2f", male_percentage);
               String femaleformattedPercentage = String.format("%.2f", female_percentage);
               
               out.println("<table border=\"1\" width=\"80%\" style=\"margin-left: auto; margin-right: auto;\"> \n");
               out.println("<tr><th>Staff Count</th> <th>Male Staff Count</th> <th>Female Staff Count</th> <th>Male Percentage</th> <th>Female Percentage</th></tr>\n");
               out.println("<tr><td>"+ staff_count +"</td> <td>"+ male_count +"</td> <td>"+ female_count +"</td> <td>"+ maleformattedPercentage +" %</td> <td>"+ femaleformattedPercentage +" %</td></tr>\n");
           }
           else
           {
              out.println("<p> No staff record found in the database  </p>");
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
