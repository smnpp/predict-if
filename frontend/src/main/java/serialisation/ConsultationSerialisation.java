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
public class ConsultationSerialisation extends Serialisation {
    
    public ConsultationSerialisation(Service service) {
        super(service);
    }

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("[TEST] Execution de ConsultationSerialisation : début");
        response.setContentType("predictif/json;charset=UTF-8");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        
        Consultation consultation = (Consultation) request.getAttribute("consultation");
        JsonObject container = new JsonObject();

        if (consultation != null) {
            container.addProperty("idConsultation", consultation.getId());
            container.addProperty("validation", consultation.getValidation());
            container.addProperty("commentaire", consultation.getCommentaire());
            container.addProperty("idClient", consultation.getClient().getId());
            container.addProperty("idEmploye", consultation.getEmploye().getId());
            container.addProperty("idMedium", consultation.getMedium().getId());
            System.out.println("\u001b[32m" + "[TEST] Execution de ConsultationSerialisation : réussie");
        } else {
            container.addProperty("idConsultation", false);
            System.out.println("\u001b[31m" + "[TEST] Execution de ConsultationSerialisation : null");
        }
        
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(container));
        out.close();
        System.out.println("[TEST] Execution de ConsultationSerialisation : fin");
    }
}

