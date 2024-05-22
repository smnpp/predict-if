/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Employe;
import service.Service;

/**
 *
 * @author sperret
 */
public class DeconnexionSerialisation extends Serialisation {
    public DeconnexionSerialisation(Service service) {
        super(service);
    }

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("[TEST] Execution de DeconnexionSerialisation");
        res.setContentType("predictif/json;charset=UTF-8");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        
        JsonObject container = new JsonObject();       
        container.addProperty("idClient", (Boolean) req.getAttribute("idClient"));
        container.addProperty("idEmploye", (Boolean) req.getAttribute("idEmploye"));

        PrintWriter out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();

    }

}
