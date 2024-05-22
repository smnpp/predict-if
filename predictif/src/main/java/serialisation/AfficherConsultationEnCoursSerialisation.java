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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Consultation;
import service.Service;

/**
 *
 * @author sperret
 */
public class AfficherConsultationEnCoursSerialisation extends Serialisation {
    public AfficherConsultationEnCoursSerialisation(Service service) {
        super(service);
    }

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("[TEST] Execution de AfficherConsultationEnCoursSerialisation");
        res.setContentType("predictif/json;charset=UTF-8");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        
        JsonObject clientJson = new JsonObject();
        clientJson.addProperty("idEmploye", (Long) req.getAttribute("idEmploye"));
        clientJson.addProperty("idConsultation", (Long) req.getAttribute("idConsultation"));

        PrintWriter out = res.getWriter();
        out.println(gson.toJson(clientJson));
        out.close();

        System.out.println("[TEST] Execution de ProfilEmployeSerialisation");
    }
    
}