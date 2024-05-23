/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Client;
import service.Service;

/**
 *
 * @author sperret
 */
public class InscriptionAction extends Action {

    public InscriptionAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de InscriptionAction");
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String telephone = request.getParameter("telephone");
        String adresse = request.getParameter("adresse");
        String password = request.getParameter("password");
        String dateString = request.getParameter("date");
        
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException ex) {
            Logger.getLogger(InscriptionAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        String genre = request.getParameter("genre");
        String email = request.getParameter("email");

        Client client = new Client(nom, prenom, genre, email, password, telephone, adresse, date);
        Boolean success = service.inscrireClient(client);

        if (success) {
            request.setAttribute("success", success);
            System.out.println("[TEST] Client inscrit");
        } else {
            System.out.println("[TEST] Client inscrit échec");
        }
    }

}
