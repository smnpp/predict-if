/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modele.Medium;
import service.Service;

/**
 *
 * @author sperret
 */
public class ConsulterTop5MediumAction extends Action {
    
    public ConsulterTop5MediumAction(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("[TEST] Execution de ConsulterTop5Medium");
        
        
        List<Medium> listmedium = service.top5Medium();
        for (int i=0; i< listmedium.size(); ++i){
            request.setAttribute("medium"+(i+1), listmedium.get(i));
        }                  
    }
}
