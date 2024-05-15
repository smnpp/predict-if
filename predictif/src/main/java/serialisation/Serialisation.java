package serialisation;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import java.io.IOException;
import service.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sperret
 */
public abstract class Serialisation {
        Service service;
    public Serialisation(Service service){
        this.service = service;
    }
    
    public abstract void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException;

}
