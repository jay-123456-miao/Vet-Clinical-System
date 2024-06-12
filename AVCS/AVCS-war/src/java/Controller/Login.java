/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Receptionist;
import Model.ReceptionistFacade;
import Model.Vet;
import Model.VetFacade;
import Model.managingStaff;
import Model.managingStaffFacade;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    private managingStaffFacade managingStaffFacade;

    @EJB
    private VetFacade vetFacade;

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String usertype = request.getParameter("usertype");
        HttpSession s  = request.getSession();
        
        try (PrintWriter out = response.getWriter()) {
            try{
                if (usertype.equals("receptionist"))
                {
                    List<Receptionist> receptionistList =  receptionistFacade.findAll();
                    Receptionist found = null;
                    for (Receptionist receptionist: receptionistList)
                    {
                        if(username.equals(receptionist.getUsername()) && String.valueOf(receptionist.getPassword()).equals(password))
                        {
                            found = receptionist;
                            break;
                        }
                    }

                    if(found == null)
                    {
                        throw new Exception();
                    }
                    s.setAttribute("userInfo", found);
                    s.setAttribute("username", username);
                    s.setAttribute("usertype", usertype);
                    request.getRequestDispatcher("Receptionist/receptionistHome.jsp").include(request, response);
                    out.println("<br><br><br> Welcome back to the system ! " + username );
                }
                else if (usertype.equals("managingStaff"))
                {
                    if (username.equals("SuperStaff")&&password.equals("127425"))
                    {
                        request.getRequestDispatcher("Staff/staffHomePage.jsp").include(request, response);
                        out.println("<br><br><br> Welcome back to the system ! " + username );
                    }
                    else
                    {
                        List<managingStaff> managingStaffList =  managingStaffFacade.findAll();
                        managingStaff found = null;
                        for (managingStaff staff: managingStaffList)
                        {
                            if(username.equals(staff.getUsername()) && String.valueOf(staff.getPassword()).equals(password))
                            {
                                found = staff;
                                break;
                            }
                        }
                        
                        if(found == null)
                        {
                            throw new Exception();
                        }
                        s.setAttribute("userInfo", found);
                        s.setAttribute("username", username);
                        s.setAttribute("usertype", usertype);
                        request.getRequestDispatcher("Staff/staffHomePage.jsp").include(request, response);
                        out.println("<br><br><br> Welcome back to the system" + found.getUsername());
                    }
                }
                else if (usertype.equals("vet"))
                {
                    Vet found = null;
                    List<Vet> vetList =  vetFacade.findAll();
                    for (Vet vet: vetList)
                    {
                        if(username.equals(vet.getUsername())&&String.valueOf(vet.getPassword()).equals(password)&&vet.getStatus().equals("Approved"))
                        {
                            found = vet;
                            break;
                        }
                    }
                   
                    if(found == null)
                      {
                          throw new Exception();
                      }
                    s.setAttribute("userInfo", found);
                    s.setAttribute("username", username);
                    s.setAttribute("usertype", usertype);
                    request.getRequestDispatcher("Vet/vetHomePage.jsp").include(request, response);
                    out.println("<br><br><br> Welcome back to the system ! " + username );
                }
            }
            catch(Exception e)
            {
                s.invalidate();
                request.getRequestDispatcher("login.jsp").include(request, response);
                out.println("<br><br><br><p> Invalid login credentials!</p>");
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
