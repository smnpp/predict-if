/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialisation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.maps.model.LatLng;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.Service;

/**
 *
 * @author sperret
 */
public class CoordonneesGeoSerialisation extends Serialisation {

    public CoordonneesGeoSerialisation(Service service) {
        super(service);
    }

     public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("[TEST] Execution de CoordonneesGeoSerialisation : début");
        response.setContentType("application/json;charset=UTF-8");

        List<LatLng> coordonnees = (List<LatLng>) request.getAttribute("coordonnees");
        JsonObject container = new JsonObject();
        JsonArray jsonListe = new JsonArray();
        
        if (coordonnees != null) {
            for (LatLng element : coordonnees) {
                JsonObject jsonCoord = new JsonObject();
                jsonCoord.addProperty("lat", element.lat);
                jsonCoord.addProperty("lng", element.lng);
                jsonListe.add(jsonCoord);
                System.out.println("\u001b[32m" + "[TEST] Execution de CoordonneesGeoSerialisation : réussie");
            }
        }
        else {
            System.out.println("\u001b[31m" + "[TEST] Execution de CoordonneesGeoSerialisation : null");
        }
        container.add("coordonnees", jsonListe);

        PrintWriter out = response.getWriter();
        out.println(container.toString());
        out.close();

        System.out.println("[TEST] Execution de CoordonneesGeoSerialisation : fin");
    }
}