/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import modele.Client;
import modele.Consultation;
import service.Service;

/**
 *
 * @author sperret
 */
public class ObtenirPredictionsAction extends Action {

    public ObtenirPredictionsAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de ObtenirPredictionsAction");
        
        Long idConsultation = (Long)parseLong(request.getParameter("idConsultation"));
        Integer amour = (Integer)parseInt(request.getParameter("amour")) + 1 ;
        Integer sante = (Integer)parseInt(request.getParameter("sante")) + 1;
        Integer travail = (Integer)parseInt(request.getParameter("travail")) + 1;
    
        Consultation consultation = service.rechercherConsultationParId(idConsultation);
        Client client = consultation.getClient();

        List<String> predictions = service.obtenirPredictions(client, amour, sante, travail);
        
        if (predictions != null){
            request.setAttribute("liste", predictions);
            System.out.println("[TEST] Execution de ObtenirPredictionsAction réussie");
        }
        else{
            request.setAttribute("liste", null);
            System.out.println("[TEST] Execution de ObtenirPredictionsAction échouée");
        }

    }

}
