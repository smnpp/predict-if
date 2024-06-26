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
 *
 * @author sperret
 */
public class ReussiteSerialisation extends Serialisation {

    public ReussiteSerialisation(Service service) {
        super(service);
    }

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("[TEST] Execution de ReussiteSerialisation : début");
        res.setContentType("predictif/json;charset=UTF-8");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonObject container = new JsonObject();
        Boolean success = (Boolean) req.getAttribute("success");
        if (success != null) {
            container.addProperty("success", success);
            System.out.println("\u001b[32m" + "[TEST] Execution de ReussiteSerialisation : réussie");
        }
        else {
            container.addProperty("success", success);
            System.out.println("\u001b[31m" + "[TEST] Execution de ReussiteSerialisation : échouée");
        }
        
        PrintWriter out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();
        System.out.println("[TEST] Execution de ReussiteSerialisation : fin");

    }
}
