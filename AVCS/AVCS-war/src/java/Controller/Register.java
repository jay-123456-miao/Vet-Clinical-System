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
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {



    @EJB
    private ReceptionistFacade receptionistFacade;

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
        
        String a = request.getParameter("username");
        String b = request.getParameter("password");
        String c = request.getParameter("email");
        String d = request.getParameter("gender");
        String o = request.getParameter("usertype");
        String f = request.getParameter("contactnumber");
        String h = request.getParameter("age");
        String status = "Unapproved";
        try (PrintWriter out = response.getWriter()) {
            try{
                if (o.equals("vet"))
                {
                    if (b.length()>10 || b.length()==0) //error 1 password lenghth is longer than 10 or empty
                    {
                        throw new Exception();
                    }
                    if (a.length()>10 || a.length()==0) //error 2 username lenghth is longer than 10 or empty
                    {
                        throw new Exception();
                    }
                    if (c.length()>25 || c.length()==0) //error 3 email lenghth is longer than 25 or empty
                    {
                        throw new Exception();
                    }
                    List<Vet> vetlist = vetFacade.findAll();
                    Vet found = null;
                    for (Vet x : vetlist)
                    {
                        if(a.equals(x.getUsername()))
                        {
                            found = x;
                            break;
                        }
                    }
                    if(found != null){
                        throw new Exception();      //error 4 already got same username that is found in the database
                    }
                    
                    int password = Integer.parseInt(b);        //error 5 only int is allowed for password
                    long contactnumber = Long.parseLong(f);     //error 6 only long is allowed for contact number
                    int age = Integer.parseInt(h); //error 7 only int is allowed for age
                    if (age <= 0)
                    {
                        throw new Exception(); //error 8 age is zero or negative 
                    }
                    vetFacade.create(new Vet(a,password, c, d, o, contactnumber, age, status, "-", "-"));
                    request.getRequestDispatcher("login.jsp").include(request, response);
                    out.println("<br><br><br> Welcome on board "+a+", your registration is done! Please wait for approval from staff.");
                }
                else
                {
                    if (b.length()>10 || b.length()==0) //error 1 password lenghth is longer than 10 or empty
                    {
                        throw new Exception();
                    }
                    if (a.length()>10 || a.length()==0) //error 2 username lenghth is longer than 10 or empty
                    {
                        throw new Exception();
                    }
                    if (c.length()>25 || c.length()==0) //error 3 email lenghth is longer than 25 or empty
                    {
                        throw new Exception();
                    }
                    List<Receptionist> receptionistList = receptionistFacade.findAll();
                    Receptionist found = null;
                    for (Receptionist y : receptionistList)
                    {
                        if(a.equals(y.getUsername()))
                        {
                            found = y;
                            break;
                        }
                    }
                    if(found != null){
                        throw new Exception();      //error 4 already got same username that is found in the database
                    }
                    int password = Integer.parseInt(b);        //error 5 only int is allowed for password
                    long contactnumber = Long.parseLong(f); //error 6 only long is allowed for contact number
                    int age = Integer.parseInt(h); //error 7 only int is allowed for age
                    if (age <= 0)
                    {
                        throw new Exception(); //error 8 age is zero or negative 
                    }
                    receptionistFacade.create(new Receptionist(a, password, c, d, o, contactnumber, age, status));
                    request.getRequestDispatcher("login.jsp").include(request, response);
                    out.println("<br><br><br> Welcome on board "+a+", your registration is done! Please wait for approval.");                }
            }catch(Exception e){
                request.getRequestDispatcher("register.jsp").include(request, response);
                out.println("Invalid user input, Please follow the guideline when registering");
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
