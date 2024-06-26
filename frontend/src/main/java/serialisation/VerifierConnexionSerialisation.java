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
import service.Service;

/**
 * Sérialisation pour vérifier l'état de la session.
 */
public class VerifierConnexionSerialisation extends Serialisation {

    public VerifierConnexionSerialisation(Service service) {
        super(service);
    }

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("[TEST] Execution de VerifierConnexionSerialisation : début");
        res.setContentType("predictif/json;charset=UTF-8");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        
        JsonObject container = new JsonObject();
        container.addProperty("idClient", (Long) req.getAttribute("idClient"));
        container.addProperty("idEmploye", (Long) req.getAttribute("idEmploye"));
        container.addProperty("connected", (Boolean) req.getAttribute("connected"));

        PrintWriter out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();
        
        System.out.println("[TEST] Execution de VerifierConnexionSerialisation : fin");
    }
}