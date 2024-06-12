/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Vet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ChoiceMenu {
        public String vetsDropDown_JSP(int row_index, int col_index, List<Vet> vetList) {
        String dropDown_vets_JSP = "<select id=\"vet\" name=\""+ Integer.toString(row_index) + "," + Integer.toString(col_index) +"\">\n";
        for (Vet vet: vetList) {
            dropDown_vets_JSP = dropDown_vets_JSP + "<option value=\""+ vet.getUsername() +"\"> Vet: "+ vet.getUsername() + ", Expertises 1 : " + vet.getExpertise1() + ", Expertises 2 : " + vet.getExpertise2()+"</option>";
        }
        dropDown_vets_JSP  = dropDown_vets_JSP + "</select>";
        return dropDown_vets_JSP;
    }
    
}
