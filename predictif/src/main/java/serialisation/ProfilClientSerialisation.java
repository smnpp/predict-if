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
import modele.ProfilAstral;
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

        Client client = (Client) req.getAttribute("client");
        ProfilAstral astral = (ProfilAstral) req.getAttribute("astral");
        System.out.println(client);
        System.out.println(astral);

        JsonObject clientJson = new JsonObject();
        if (client != null) {
            clientJson.addProperty("idClient", client.getId());
            clientJson.addProperty("nom", client.getNom());
            clientJson.addProperty("prenom", client.getPrenom());
            clientJson.addProperty("mail", client.getMail());
            if (astral != null) {
                clientJson.addProperty("animal", astral.getAnimal());
                clientJson.addProperty("zodiaque", astral.getSigneZodiaque());
                clientJson.addProperty("couleur", astral.getCouleur());
                clientJson.addProperty("chinois", astral.getSigneChinois());
                System.out.println("[TEST] ProfilClientSerialisation : réussie");
            }
        } else {
            clientJson.addProperty("idClient", false);
            System.out.println("[TEST] ProfilClientSerialisation : échoué");
        }
        container.add("client", clientJson);

        PrintWriter out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();

    }

}
