/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.AppointmentFacade;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Edit_appointment", urlPatterns = {"/Edit_appointment"})
public class Edit_appointment extends HttpServlet {

    @EJB
    private AppointmentFacade appointmentFacade;

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
        long appointmenteditID = 0l;
        List<Appointment> appointmentlist = appointmentFacade.findAll();
        for (Appointment apt: appointmentlist) {
            // check if the EDIT button of each record is clicked
            if (request.getParameter(Long.toString(apt.getId())) != null) {
                // keep track of the index of the record to be edited
                appointmenteditID = apt.getId();
                s.setAttribute("appointmenteditID", appointmenteditID);
                break;
            }
        }
        
        request.getRequestDispatcher("Receptionist/manage_appointments.jsp").include(request, response);
        try (PrintWriter out = response.getWriter()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            for(Appointment appointment : appointmentlist)
                if(appointment.getId() == appointmenteditID)
                {
                out.println(" <form action = \"Save_appointment\" method=\"POST\">");
                out.println(" <table border=\"\" width=\"80%\">");
                out.println(  "                <tr>\n" +
                                    "                    <th colspan=\"2\">Appointment Info</th>\n" +
                                    "                    <th colspan=\"2\">Customer Info</th>\n" +
                                    "                    <th colspan=\"2\">Pet Info</th>\n" +
                                    "                </tr>\n" );
                out.println(
    
            
    "                <tr>\n" +
    "                    <td>Appointment Time: </td>\n" +
    "                    <td>"+appointment.getAppointment_time().format(formatter)+"</td>\n" +
    "                    <td>Username: </td>\n" +
    "                    <td><input type=\"text\" name=\"customerUsername\" size=\"20\"></td>\n" +
    "                    <td>Pet Name: </td>\n" +
    "                    <td><input type=\"text\" name=\"petUsername\" size=\"20\"></td>\n" +
    "                </tr>\n" +
    "                <tr>\n" +

    "                    <td>Assigned vet: </td>\n" +
    "                    <td><input type=\"text\" name=\"vetUsername\" size=\"20\"></td>\n" +
    "                    <td>Gender: </td>\n" +
    "                    <td><select id = \"gender\" name =\"gender\">\n" +
    "                            <option value = \"male\">Male</option>\n" +
    "                            <option value = \"female\">Female</option>\n" +
    "                            <option value = \"confidential\">Confidential</option>\n" +
    "                     </td></select>\n" + 
    "                    <td>Species: </td>\n" +
    "                    <td>\n" +
    "                        <select id = \"species\" name =\"species\">\n" +
    "                            <option value = \"-\">-</option>\n" +
    "                            <option value = \"Dogs\">Dogs</option>\n" +
    "                            <option value = \"Cat\">Cat</option>\n" +
    "                            <option value = \"Fish\">Fish</option>\n" +
    "                            <option value = \"Birds\">Birds</option>\n" +
    "                            <option value = \"Reptiles\">Reptiles</option>\n" +
    "                                 <option value = \"Horse\">Horse</option>\n"     +
    "                                 <option value = \"Tortoise\">Tortoise</option>\n" +  
    "                        </select>\n" +
    "                    </td>\n" +
    "\n" +
    "                </tr>\n" +
    "                <tr>\n" +
    "        <td></td>\n" +
    "        <td></td>\n" +
    "                    <td>Email Address: </td>\n" +
    "                    <td><input type=\"text\" name=\"emailAddress\" size=\"20\">\n" +
    "                </tr> \n" +
    "        <td></td>\n" +
    "        <td></td>\n" +
    "                    <td>Contact Number: </td>\n" +
    "                    <td><input type=\"text\" name=\"contactNumber\" size=\"20\"></td>\n" +
    "                <tr>\n" +
    "        <td></td>\n" +
    "        <td></td>\n" +
    "                    <td>Age: </td>\n" +
    "                    <td><input type=\"text\" name=\"age\" size=\"20\"></td>\n" +
    "                </tr>    \n" +
    "                <tr>\n" +
    "        <td><input type= \"submit\" value= \"Save\" name= \""+String.valueOf(appointment.getId())+"\"></form> </td>\n" +
    "                </tr>    \n"      
    );
    out.println("            </table>  ");
    out.println("            </form>  ");      

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
