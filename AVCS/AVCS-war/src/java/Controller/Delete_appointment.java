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
@WebServlet(name = "Delete_appointment", urlPatterns = {"/Delete_appointment"})
public class Delete_appointment extends HttpServlet {

    @EJB
    private VetFacade vetFacade;

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
        List<Appointment> appointmentList = appointmentFacade.findAll();
        Appointment appointmentDelete = null;
        for (Appointment appointment : appointmentList) {
            if (request.getParameter(Long.toString(appointment.getId())) != null) {
                appointmentDelete = appointment;
                break;
            }
        }
        
        List<Vet> vetList = vetFacade.findAll();
        Vet vetAppointmentDelete = null;
        for (Vet vet: vetList) {
            if (vet.getUsername().equals(appointmentDelete.getVet_username())) {
                vetAppointmentDelete = vet;
                break;
            }
        }
        
        
        
        request.getRequestDispatcher("Receptionist/manage_appointments.jsp").include(request, response);
        try (PrintWriter out = response.getWriter()) {
            if (appointmentDelete != null && vetAppointmentDelete != null) {
                vetAppointmentDelete.getAppoinments().remove(appointmentDelete);
                vetFacade.edit(vetAppointmentDelete);
                
                //update the table APPOINTMENT
                appointmentFacade.remove(appointmentDelete);
                
                out.println("The appointment has been deleted successfully!");
                List<Appointment> appointments = appointmentFacade.findAll();
                for (Appointment appointment: appointments)
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
                
                
            } else {
                // this could happen when staffs delete the vets' accounts
                // the system only remove the vet from the table Vet, but forgot to update the table Appointment (maybe we should replace it with Unknown?)
                out.println("The appointment can't be deleted! Appointment or the vet can't be found inside the database!");
                List<Appointment> updatedAppointments = appointmentFacade.findAll();
                for (Appointment appointment: updatedAppointments)
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
