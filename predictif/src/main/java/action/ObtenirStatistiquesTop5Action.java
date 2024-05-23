/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.google.maps.model.LatLng;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modele.Medium;
import service.Service;

/**
 *
 * @author sperret
 */
public class ObtenirStatistiquesTop5Action extends Action {
    
    public ObtenirStatistiquesTop5Action(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de ConsulterTop5Medium");
        
        
        List<Medium> listmedium = service.top5Medium();
        request.setAttribute("mediums", listmedium);  
    }
}
