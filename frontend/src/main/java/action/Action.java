package action;
import service.Service;


import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sperret
 */
public abstract class Action { 
    Service service;

    public Action(Service service){
        this.service = service;
    }
    public abstract void execute(HttpServletRequest request);
    
}
