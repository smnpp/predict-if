/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.google.maps.model.LatLng;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import service.Service;

/**
 *
 * @author sperret
 */
public class ObtenirStatistiquesGeoAction extends Action {
    
    public ObtenirStatistiquesGeoAction(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de ObtenirStatistiquesGeoAction : début");
                
        List<LatLng> coordonnees = service.voirClientsMap();
        if (coordonnees != null) {
            request.setAttribute("coordonnees", coordonnees);  
            System.out.println("\u001b[32m" + "[TEST] Execution de ObtenirStatistiquesGeoAction : réussie");
        }
        else {
            request.setAttribute("coordonnees", null);  
            System.out.println("\u001b[31m" + "[TEST] Execution de ObtenirStatistiquesGeoAction : null");
        }
        System.out.println("[TEST] Execution de ObtenirStatistiquesGeoAction : fin");
    }
}
