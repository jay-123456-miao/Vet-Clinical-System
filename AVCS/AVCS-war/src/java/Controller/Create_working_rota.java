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
@WebServlet(name = "Create_working_rota", urlPatterns = {"/Create_working_rota"})
public class Create_working_rota extends HttpServlet {

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
        boolean duplicated = false;
        boolean expertise_num_not_reach = false;
        HttpSession s = request.getSession(false);
        LocalDateTime startingDate = (LocalDateTime) s.getAttribute("startingDate");
        String[][] rota = new String[3][7];
        // extract all the vets from the table, then put it into `rota` for further validation
        for (int row_index=0; row_index < 3; row_index++) {
            for (int col_index=0; col_index<7; col_index++) {
                String vet_username = request.getParameter(Integer.toString(row_index)+","+Integer.toString(col_index));
                rota[row_index][col_index] = vet_username;
            }
            
        }
        
        List<Vet> vetList = vetFacade.findAll();
        
        // to check if there is any duplicate vet on the same day
        // then check if the min expertise per day is not reached
        
        int col_num_error = 0; 
        for (int col_index=0; col_index<7; col_index++) {
            int min_expertise_per_day = 0;
            if (duplicated || expertise_num_not_reach) {
                break;
            }
            String[] vets_one_day = new String[3];
            for (int row_index=0; row_index<3; row_index++) {
                String vet_name = rota[row_index][col_index];
                vets_one_day[row_index] = vet_name;
                for (Vet vet: vetList) {
                    if (vet.getUsername().equals(vet_name)) {
                            if (!vet.getExpertise1().equals("-")) {
                                min_expertise_per_day = min_expertise_per_day + 1;
                            }
                            if (!vet.getExpertise2().equals("-")) {
                                min_expertise_per_day = min_expertise_per_day + 1;
                            }      
                        break;
                    }
                }
            }
            
            //check if the min expertise per day is not reached
            if (min_expertise_per_day < 5) {
               expertise_num_not_reach = true;
                col_num_error = col_index;
                break;
            }
            
            // to check if there is any duplicate vet on the same day
            for(int i = 0; i < vets_one_day.length; i++) {
                if (duplicated || expertise_num_not_reach) {
                    break;
                }
                for(int j = i + 1; j < vets_one_day.length; j++) {
                    // duplicate vet
                    if(vets_one_day[i].equals(vets_one_day[j])) {
                        duplicated = true;
                        col_num_error = col_index;
                        break;
                    } 
                }  
            }
            
            // to check if the min expertise per day is not reached
            
        }
        try (PrintWriter out = response.getWriter()) {
//            out.println("<p> Date : "+ startingDate + " </p>"  );
//            out.println("<br><br>"  );
//            out.println("<form action=\"Create_working_rota\" action='POST'>");
//            out.println("<table border=\"1\" width=\"80%\" style=\"margin-left: auto; margin-right: auto;\"> \n");
//            out.println("<tr><th>Mon</th> <th>Tue</th> <th>Wed</th> <th>Thu</th> <th>Fri</th> <th>Sat</th> <th>Sun</th></tr>\n");
//            for (int row_index=0; row_index < 3; row_index++) {
//                out.println("<tr>");
//                for (int col_index=0; col_index<7; col_index++) {
//                    out.println("<td>");
//                    ChoiceMenu menu = new ChoiceMenu();
//                    out.println(menu.vetsDropDown_JSP(row_index, col_index, vetList));
//                    out.println("</td>");
//                }
//                out.println("</tr>");
//            }
//            out.println("<tr><td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td align =\"right\"><input type=\"submit\" value=\"Create\"></td><tr>");
//            out.println("</table>");
//            out.println("</form>");
//            out.println("<br><br><br>");
//            
            if (duplicated) {
                request.getRequestDispatcher("create_working_rotation2.jsp").include(request, response);
                out.println("<p> Date : "+ startingDate + " </p>"  );
                out.println("<br><br>"  );
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
                out.println("The are duplicated vet in the same day! The error happened at column " + col_num_error + "!");
                return;
            } 
            if (expertise_num_not_reach) {
                request.getRequestDispatcher("create_working_rotation2.jsp").include(request, response);
                out.println("<p> Date : "+ startingDate + " </p>"  );
                out.println("<br><br>"  );
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
                out.println("The should be at least 5 per day! The error happened at column " + col_num_error + "!");
                return;
            }
            
            if (!duplicated && !expertise_num_not_reach) {
                // add a new working rota to the table workingRota
                
                WorkingRotation newWorkingRotation = new WorkingRotation(startingDate, 
                        rota[0][0], rota[1][0], rota[2][0], 
                        rota[0][1], rota[1][1], rota[2][1], 
                        rota[0][2], rota[1][2], rota[2][2], 
                        rota[0][3], rota[1][3], rota[2][3],
                        rota[0][4], rota[1][4], rota[2][4],
                        rota[0][5], rota[1][5], rota[2][5],
                        rota[0][6], rota[1][6], rota[2][6]
                );
                workingRotationFacade.create(newWorkingRotation);
                request.getRequestDispatcher("create_working_rotation.jsp").include(request, response);
                out.println("The working rotation has been added successfully!");

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
