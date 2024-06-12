/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.AppointmentFacade;
import Model.Vet;
import Model.VetFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
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
@WebServlet(name = "Create_appointment", urlPatterns = {"/Create_appointment"})
public class Create_appointment extends HttpServlet {

    @EJB
    private AppointmentFacade appointmentFacade;

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
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String hour = request.getParameter("hour");
        String assigned_vet = request.getParameter("vetUsername");
        String customer_username = request.getParameter("customerUsername");
        String gender = request.getParameter("gender");
        String email = request.getParameter("emailAddress");
        String contact_number = request.getParameter("contactNumber");
        String age = request.getParameter("age");
        String pet_name = request.getParameter("petUsername");
        String species = request.getParameter("species");
        LocalDateTime appointmentDateTime;
        try (PrintWriter out = response.getWriter()) {
            try
            {   if (contact_number.length() == 0 || contact_number.length() > 15) {
                    throw new Exception();
                }
                int yearInt = Integer.parseInt(year);
                int monthInt = Integer.parseInt(month);
                int dayInt = Integer.parseInt(day);
                int hourInt = Integer.parseInt(hour);
                int ageInt = Integer.parseInt(age);
                long contact_numberLong = Long.parseLong(contact_number);
                appointmentDateTime = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, 0, 0);
                                // check if the assigned vet exists
                List<Vet> vetList = vetFacade.findAll();
                Vet found = null;
                for (Vet vet: vetList) {
                    if (vet.getUsername().equals(assigned_vet)) {
                        found = vet;
                        break;
                    }
                }
                if (found == null) {
                    throw new Exception();
                }
                
                
                if (customer_username.length() == 0 || customer_username.length() > 8) {
                    throw new Exception();
                }
                

                if (pet_name.length() == 0 || pet_name.length() > 8) {
                    throw new Exception();
                }
                
                if (ageInt <= 0) {
                    throw new Exception();
                }

                request.getRequestDispatcher("Receptionist/manage_appointments.jsp").include(request, response);
                out.println("<br>The appointment has been successfully added!");
                Appointment new_appointment = new Appointment(assigned_vet, customer_username,pet_name, appointmentDateTime, "", "", "Unfinished", gender , email, contact_numberLong, ageInt, species);                
                appointmentFacade.create(new_appointment);
                found.getAppoinments().add(new_appointment);
                vetFacade.edit(found);
                List<Appointment> appointmentlist = appointmentFacade.findAll();
                for (Appointment appointment: appointmentlist)
                {
     out.println(" <table border=\"\" width=\"80%\">");
    out.println(  "                <tr>\n" +
                        "                    <th colspan=\"2\">Appointment Info</th>\n" +
                        "                    <th colspan=\"2\">Customer Info</th>\n" +
                        "                    <th colspan=\"2\">Pet Info</th>\n" +
                        "                </tr>\n" );
                out.println(
    
            
    "                <tr>\n" +
    "                    <td>Appointment Time: </td>\n" +
    "                    <td>"+appointment.getAppointment_time()+"</td>\n" +
    "                    <td>Username: </td>\n" +
    "                    <td>"+appointment.getCustomer_username()+"</td>\n" +
    "                    <td>Pet Name: </td>\n" +
    "                    <td>"+appointment.getPet_username()+"</td>\n" +
    "                </tr>\n" +
    "                <tr>\n" +

    "                    <td>Assigned vet: </td>\n" +
    "                    <td>"+appointment.getVet_username()+"</td>\n" +
    "                    <td>Gender: </td>\n" +
    "                    <td>"+appointment.getGender()+"</td>\n" + 
    "                    <td>Species: </td>\n" +
    "                    <td>"+appointment.getSpecies()+"</td>\n" +
    "                </tr>\n" +
    "                <tr>\n" +
    "        <td></td>\n" +
    "        <td></td>\n" +
    "                    <td>Email Address: </td>\n" +
    "                    <td>"+appointment.getEmail()+"</td>\n" +
    "                </tr> \n" +
    "        <td></td>\n" +
    "        <td></td>\n" +
    "                    <td>Contact Number: </td>\n" +
    "                    <td>"+appointment.getContact_number()+"</td>\n" +
    "                <tr>\n" +
    "        <td></td>\n" +
    "        <td></td>\n" +
    "                    <td>Age: </td>\n" +
    "                    <td>"+appointment.getAge()+"</td>\n" +
    "                </tr>    \n" +
    "                <tr>\n" +
    "        <td><form action= \"Edit_appointment\" method=\"post\"><input type= \"submit\" value= \"Edit\" name= \""+String.valueOf(appointment.getId())+"\"></form> </td>\n" +
    "        <td><form action=\"Delete_appoinment\" method=\"post\"><input type=\"submit\" value=\"Delete\" name= \""+String.valueOf(appointment.getId())+"\"></form></td>\n" +
    "                </tr>    \n"      
    );
    out.println("            </table>  ");            

                }    
                
                
            }
            catch(Exception e)
            {
                request.getRequestDispatcher("Receptionist/manage_appointments.jsp").include(request, response);
                out.println("<br> Invalid user input!");
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
