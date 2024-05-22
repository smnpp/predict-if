package serialisation;


import serialisation.Serialisation;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sperret
 */
public class ProfilClientSerialisation extends Serialisation {

    public ProfilClientSerialisation(Service service) {
        super(service);
    }

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("[TEST] Execution de ProfilClientSerialisation");
        res.setContentType("predictif/json;charset=UTF-8");

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonObject container = new JsonObject();

        Long idClient = (Long) req.getAttribute("idClient");
        Client client = service.rechercherClientParId(idClient);
        
        JsonObject clientJson = new JsonObject();
        if (client != null) {
            container.addProperty("connexion", true);
            clientJson.addProperty("idClient", idClient);
            clientJson.addProperty("nom", client.getNom());
            clientJson.addProperty("prenom", client.getPrenom());
            clientJson.addProperty("mail", client.getMail());

            container.add("client", clientJson);
            System.out.println("[TEST] ProfilClientSerialisation : authentifié");
        }
        else {
            container.addProperty("connexion", false);
            System.out.println("[TEST] ProfilClientSerialisation : échoué");
        }

        PrintWriter out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();

        
    }

}