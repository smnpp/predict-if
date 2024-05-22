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
import modele.Client;
import service.Service;

/**
 *
 * @author sperret
 */
public class InscriptionSerialisation extends Serialisation {

    public InscriptionSerialisation(Service service) {
        super(service);
    }

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("[TEST] Execution de InscriptionSerialisation");
        res.setContentType("predictif/json;charset=UTF-8");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(date);

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        JsonObject container = new JsonObject();
        container.addProperty("date", formattedDate);

        Client client = (Client) req.getAttribute("client");
        if (client != null) {
            container.addProperty("idClient", client.getId());
            System.out.println("[TEST] InscriptionSerialisation : réussie");
        }
        else {
            System.out.println("[TEST] InscriptionSerialisation : échoué");
        }

        PrintWriter out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();
    }

}
