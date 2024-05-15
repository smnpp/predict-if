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
import modele.Employe;
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
public class ProfilEmployeSerialisation extends Serialisation {

    public ProfilEmployeSerialisation(Service service) {
        super(service);
    }

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("demarage/json;charset=UTF-8");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(date);

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        JsonObject container = new JsonObject();
        container.addProperty("date", formattedDate);

        Employe employe = (Employe) req.getAttribute("employe");
        JsonObject employeJson = new JsonObject();
        if (employe != null) {
            container.addProperty("connexion", true);
            employeJson.addProperty("id", employe.getId());
            employeJson.addProperty("nom", employe.getNom());
            employeJson.addProperty("prenom", employe.getPrenom());
            employeJson.addProperty("mail", employe.getMail());

            container.add("employe", employeJson);
            System.out.println("[TEST] ProfilEmployeSerialisation : authentifié");
        }
        else {
            container.addProperty("connexion", false);
            System.out.println("[TEST] ProfilEmployeSerialisation : échoué");
        }

        PrintWriter out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();

        System.out.println("[TEST] Execution de ProfilEmployeSerialisation");
    }

}