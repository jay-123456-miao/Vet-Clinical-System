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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "Save_appointment", urlPatterns = {"/Save_appointment"})
public class Save_appointment extends HttpServlet {

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
        String assigned_vet = request.getParameter("vetUsername");
        String customer_username = request.getParameter("customerUsername");
        String gender = request.getParameter("gender");
        String email = request.getParameter("emailAddress");
        String contact_number = request.getParameter("contactNumber");
        String age = request.getParameter("age");
        String pet_name = request.getParameter("petUsername");
        String species = request.getParameter("species");
        HttpSession s = request.getSession(false);
        long appointmentEdit= (long)s.getAttribute("appointmenteditID");
        
        Appointment appointmentforEdit = appointmentFacade.find(appointmentEdit);
        
        try (PrintWriter out = response.getWriter()) { 
            try {
                int ageInt = Integer.parseInt(age);
                long contact_numberLong = Long.parseLong(contact_number);
                Vet vet = vetFacade.searchByUsername(assigned_vet);
                if (customer_username.length() == 0 || customer_username.length() > 8) {
                    throw new Exception();
                }
                

                if (pet_name.length() == 0 || pet_name.length() > 8) {
                    throw new Exception();
                }
                if (ageInt <= 0) {
                    throw new Exception();
                }
                if (vet == null) {
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
                
                try {
                    if (vet.getUsername().equals(appointmentforEdit.getVet_username())) {                     
                        appointmentforEdit.setCustomer_username(customer_username);
                        appointmentforEdit.setGender(gender);
                        appointmentforEdit.setEmail(email);
                        appointmentforEdit.setContact_number(contact_numberLong);
                        appointmentforEdit.setAge(ageInt);
                        appointmentforEdit.setPet_username(pet_name);
                        appointmentforEdit.setSpecies(species);
                        appointmentFacade.edit(appointmentforEdit);
                        
                    } else {
                        // if the vet changes
                        Appointment new_appointment = new Appointment(assigned_vet, customer_username,pet_name, appointmentforEdit.getAppointment_time(), appointmentforEdit.getDiagnosis(), appointmentforEdit.getPrognosis(), appointmentforEdit.getStatus(), gender, email, contact_numberLong , ageInt,species);
                        appointmentFacade.create(new_appointment);    
                        Vet oldvet = vetFacade.searchByUsername(appointmentforEdit.getVet_username());
                        if (oldvet != null) {

                            oldvet.getAppoinments().remove(appointmentforEdit);
                            vet.getAppoinments().add(new_appointment);
                            vetFacade.edit(oldvet);
                            vetFacade.edit(vet);
                        } else {
                            vet.getAppoinments().add(new_appointment);
                            vetFacade.edit(vet);
                        }
                        
                        appointmentFacade.remove(appointmentforEdit);
                    }
                    
                    out.println("The appoinment has been edited successfully!");
                    List<Appointment> newappointmentlist = appointmentFacade.findAll();
                    for (Appointment appointment: newappointmentlist)
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
    "        <td><form action=\"Delete_appointment\" method=\"post\"><input type=\"submit\" value=\"Delete\" name= \""+String.valueOf(appointment.getId())+"\"></form></td>\n" +
    "                </tr>    \n"      
    );
    out.println("            </table>  ");}              
    
                    } catch (Exception ex) {
                        out.println("Failed to save the changes on the appointment, try again!");
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
        "        <td><form action=\"Delete_appointment\" method=\"post\"><input type=\"submit\" value=\"Delete\" name= \""+String.valueOf(appointment.getId())+"\"></form></td>\n" +
        "                </tr>    \n"      
        );
        out.println("            </table>  ");            

                    }    


                    }     
            } catch (Exception e) {
                request.getRequestDispatcher("Receptionist/manage_appointments.jsp").include(request, response);
                // Load some existing appointments from the database
                List<Appointment> appointmentList = appointmentFacade.findAll();
                out.println("<br>Invalid inputs while editing the appointment, try again!");
               for (Appointment appointment: appointmentList)
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
    "        <td><form action=\"Delete_appointment\" method=\"post\"><input type=\"submit\" value=\"Delete\" name= \""+String.valueOf(appointment.getId())+"\"></form></td>\n" +
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
