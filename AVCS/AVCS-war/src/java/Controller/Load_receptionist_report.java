/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Receptionist;
import Model.ReceptionistFacade;
import Model.managingStaff;
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
@WebServlet(name = "Load_receptionist_report", urlPatterns = {"/Load_receptionist_report"})
public class Load_receptionist_report extends HttpServlet {

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
        
        List<Receptionist> receptionistList = receptionistFacade.findAll();
        int receptionist_count = 0;
        int male_count = 0;
        int female_count = 0;
        int confidential_count = 0;
        int unapproved_count = 0;
        int approved_count = 0;
        
        LocalTime currentTime = LocalTime.now();
        
        
        request.getRequestDispatcher("Staff/receptionist_report.jsp").include(request, response);
        
        try (PrintWriter out = response.getWriter()) {
           out.println("<p> The current local time is : " + currentTime + " </p>");
           if (receptionistList != null)
           {
               for (Receptionist receptionist: receptionistList)
               {
                   receptionist_count = receptionist_count + 1;
                   if(receptionist.getGender().equals("male"))
                   {
                       male_count = male_count +1;
                   }
                   if(receptionist.getGender().equals("female"))
                   {
                       female_count = female_count +1;
                   }
                   if(receptionist.getGender().equals("confidential"))
                   {
                       confidential_count = confidential_count +1;
                   }
                   if(receptionist.getStatus().equals("Unapproved"))
                   {
                       unapproved_count = unapproved_count+1;
                   }
                   if(receptionist.getStatus().equals("Approved"))
                   {
                       approved_count = approved_count+1;
                   }
               }
               double male_percentage = (male_count*1.0/receptionist_count*1.0)*100;
               double  female_percentage = (female_count*1.0/receptionist_count*1.0)*100;
               double  confidential_percentage = (confidential_count*1.0/receptionist_count*1.0)*100;
               double  approved_percentage = (approved_count*1.0/receptionist_count*1.0)*100;
               double  unapproved_percentage = (unapproved_count*1.0/receptionist_count*1.0)*100;
               String maleformattedPercentage = String.format("%.2f", male_percentage);
               String femaleformattedPercentage = String.format("%.2f",female_percentage);
               String confidentialformattedPercentage = String.format("%.2f", confidential_percentage);
               String approvedformattedPercentage = String.format("%.2f", approved_percentage);
               String unapprovedformattedPercentage = String.format("%.2f", unapproved_percentage);
               out.println("<p>Receptionist Summary Table: </p>");
               out.println("<table border=\"1\" width=\"80%\" style=\"margin-left: auto; margin-right: auto;\"> \n");
               out.println("<tr><th>Receptionist Count</th> <th>Male Receptionist Count</th> <th>Female Receptionist Count</th> <th>Confidential Receptionist Count</th><th>Male Percentage</th> <th>Female Percentage</th><th>Confidential Percentage</th></tr>\n");
               out.println("<tr><td>"+ receptionist_count +"</td> <td>"+ male_count +"</td> <td>"+ female_count +"</td> <td>"+ confidential_count +"</td><td>"+ maleformattedPercentage +" %</td> <td>"+ femaleformattedPercentage +" %</td><td>"+ confidentialformattedPercentage +" %</td></tr>\n");
               out.println("</table><br><br>");
               out.println("<p>Receptionist Approval Summary Table: </p>");
               out.println("<table border=\"1\" width=\"80%\" style=\"margin-left: auto; margin-right: auto;\"> \n");
               out.println("<tr><th>Unapproved Receptionist Count</th> <th>Approved Receptionist Count</th><th>Approved Receptionist Percentage</th><th>Unapproved Receptionist Percentage</th></tr>\n");
               out.println("<tr><td>"+ unapproved_count +"</td>  <td>"+ approved_count +"</td><td>"+ approvedformattedPercentage +" %</td><td>"+ unapprovedformattedPercentage +" %</td></tr>\n");
               out.println("</table><br><br>");
           }
           else
           {
              out.println("<p> No receptionist record found in the database  </p>");
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
