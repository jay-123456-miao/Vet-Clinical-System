/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.Vet;
import Model.VetFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
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
@WebServlet(name = "Load_vet_report", urlPatterns = {"/Load_vet_report"})
public class Load_vet_report extends HttpServlet {

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
        List<Vet> vetList = vetFacade.findAll();
        int vet_count = 0;
        int male_count = 0;
        int female_count = 0;
        int confidential_count = 0;
        int approved_count = 0;
        int unapproved_count = 0;
        int one_expertise = 0 ;
        int two_expertise = 0;
        int zero_expertise = 0;
        int appoinment_count = 0;
        int zero_appoinment = 0;
        int more_than_two_appoinment = 0;
        LocalTime currentTime = LocalTime.now();
        
        
        request.getRequestDispatcher("Staff/vet_report.jsp").include(request, response);
        
        try (PrintWriter out = response.getWriter()) {
           out.println("<p> The current local time is : " + currentTime + " </p>");
           if (vetList != null)
           {
               for (Vet vet: vetList)
               {
                   
                   vet_count = vet_count + 1;
                   if(!vet.getAppoinments().isEmpty())
                   {
                       
                       int temp = 0;
                       ArrayList<Appointment> appoinmentsArrayList = vet.getAppoinments();
                       for (Appointment appoinmtment : appoinmentsArrayList)
                       {
                           appoinment_count = appoinment_count+1;
                           temp = temp + 1;
                       }
                       if(temp > 2)
                       {
                           more_than_two_appoinment = more_than_two_appoinment + 1;
                       }
                   }
                   if(vet.getAppoinments().isEmpty())
                   {
                       zero_appoinment = zero_appoinment + 1;
                   }
                   if(vet.getGender().equals("male"))
                   {
                       male_count = male_count +1;
                   }
                   if(vet.getGender().equals("female"))
                   {
                       female_count = female_count +1;
                   }
                   if(vet.getGender().equals("confidential"))
                   {
                       confidential_count = confidential_count +1;
                   }
                   if(vet.getStatus().equals("Unapproved"))
                   {
                       unapproved_count = unapproved_count+1;
                   }
                   if(vet.getStatus().equals("Approved"))
                   {
                       approved_count = approved_count+1;
                   }
                   if((vet.getExpertise1().equals("-")&&!vet.getExpertise2().equals("-")) || (vet.getExpertise2().equals("-")&&!vet.getExpertise1().equals("-")))
                   {
                       one_expertise = one_expertise + 1;
                   }
                   if(vet.getExpertise1().equals("-")&&vet.getExpertise2().equals("-"))
                   {
                       zero_expertise = zero_expertise + 1;
                   }
                   if(!vet.getExpertise1().equals("-")&&!vet.getExpertise2().equals("-"))
                   {
                       two_expertise = two_expertise + 1;
                   }
               }
               double zero_expertise_percentage = (zero_expertise*1.0/(zero_expertise+one_expertise+two_expertise))*100;
               double one_expertise_percentage = (one_expertise*1.0/(zero_expertise+one_expertise+two_expertise))*100;
               double two_expertise_percentage = (two_expertise*1.0/(zero_expertise+one_expertise+two_expertise))*100;
               double male_percentage = (male_count*1.0/vet_count*1.0)*100;
               double  female_percentage = (female_count*1.0/vet_count*1.0)*100;
               double  confidential_percentage = (confidential_count*1.0/vet_count*1.0)*100;
               double  approved_percentage = (approved_count*1.0/vet_count*1.0)*100;
               double  unapproved_percentage = (unapproved_count*1.0/vet_count*1.0)*100;
               double  zero_appoinment_percentage = (zero_appoinment*1.0/vet_count*1.0)*100;
               double  more_than_two_appoinment_percentage = (more_than_two_appoinment*1.0/vet_count*1.0)*100;
               double rest_appoinment_percentage = 100.0-zero_appoinment_percentage-more_than_two_appoinment_percentage;
               String maleformattedPercentage = String.format("%.2f", male_percentage);
               String femaleformattedPercentage = String.format("%.2f",female_percentage);
               String confidentialformattedPercentage = String.format("%.2f", confidential_percentage);
               String approvedformattedPercentage = String.format("%.2f", approved_percentage);
               String unapprovedformattedPercentage = String.format("%.2f", unapproved_percentage);
               String zero_appoinmentformattedPercentage = String.format("%.2f", zero_appoinment_percentage);
               String more_than_two_appoinmentformattedPercentage = String.format("%.2f", more_than_two_appoinment_percentage);
               String rest_appoinment_formattedPercentage = String.format("%.2f", rest_appoinment_percentage);
               String zero_expertise_formattedPercentage = String.format("%.2f", zero_expertise_percentage);
               String one_expertise_formattedPercentage = String.format("%.2f", one_expertise_percentage);
               String two_expertise_formattedPercentage = String.format("%.2f", two_expertise_percentage);
               out.println("<p>Vet Summary Table: </p>");
               out.println("<table border=\"1\" width=\"80%\" style=\"margin-left: auto; margin-right: auto;\"> \n");
               out.println("<tr><th>Vet Count</th> <th>Male Vet Count</th> <th>Female Vet Count</th> <th>Confidential Vet Count</th><th>Male Percentage</th> <th>Female Percentage</th><th>Confidential Percentage</th></tr>\n");
               out.println("<tr><td>"+ vet_count +"</td> <td>"+ male_count +"</td> <td>"+ female_count +"</td> <td>"+ confidential_count +"</td><td>"+ maleformattedPercentage +" %</td> <td>"+ femaleformattedPercentage +" %</td><td>"+ confidentialformattedPercentage +" %</td></tr>\n");
               out.println("</table><br><br>");
               out.println("<p>Vet Approval Summary Table: </p>");
               out.println("<table border=\"1\" width=\"80%\" style=\"margin-left: auto; margin-right: auto;\"> \n");
               out.println("<tr><th>Unapproved Vet Count</th> <th>Approved Vet Count</th><th>Approved Vet Percentage</th><th>Unapproved Vet Percentage</th></tr>\n");
               out.println("<tr><td>"+ unapproved_count +"</td>  <td>"+ approved_count +"</td><td>"+ approvedformattedPercentage +" %</td><td>"+ unapprovedformattedPercentage +" %</td></tr>\n");
               out.println("</table><br><br>");
               out.println("<br><br>");
               out.println("<p>Vet Expertise Summary Table: </p>");
               out.println("<table border=\"1\" width=\"40%\" style=\"margin-left: auto; margin-right: auto;\"> \n");
               out.println("<tr><th>Vet with zero expertise</th><th>Vet with one expertise</th><th>Vet with two expertise</th><th>Vet with zero expertise percentage</th><th>Vet with one expertise percentage</th><th>Vet with two expertise percentage</th></tr>\n");
               out.println("<tr><td>"+ zero_expertise +"</td><td>"+ one_expertise +"</td><td>"+ two_expertise +"</td><td>"+ zero_expertise_formattedPercentage +"  %</td><td>"+ one_expertise_formattedPercentage +"  %</td><td>"+ two_expertise_formattedPercentage +"  %</td></tr>\n");
               out.println("</table><br><br>");
               out.println("<br><br>");
               out.println("<p>Vet Appoinment Summary Table: </p>");
               out.println("<table border=\"1\" width=\"40%\" style=\"margin-left: auto; margin-right: auto;\"> \n");
               out.println("<tr><th>Appointment Count</th><th>Vet with zero appointment</th><th>Vet with more than two appointment</th><th>Vet with zero appointment percentage</th><th>Vet with more than two appointment percentage</th><th>Remaining appointment percentage</th></tr>\n");
               out.println("<tr><td>"+ appoinment_count +"</td><td>"+ zero_appoinment +"</td><td>"+ more_than_two_appoinment +" </td><td>"+ zero_appoinmentformattedPercentage +"  %</td><td>"+ more_than_two_appoinmentformattedPercentage +"  %</td><td>"+ rest_appoinment_formattedPercentage +"  %</td></tr>\n");
               
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
