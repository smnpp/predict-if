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
        System.out.println("[TEST] Execution de ObtenirStatistiquesGeoAction");
        
        
        List<LatLng> coordonnees = service.voirClientsMap();
        request.setAttribute("coordonnees", coordonnees);  
    }
}
