/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialisation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Astrologue;
import modele.Cartomancien;
import modele.Medium;
import modele.Spirit;
import service.Service;

/**
 *
 * @author sperret
 */
public class ListerTop5MediumSerialisation extends Serialisation {
    
    public ListerTop5MediumSerialisation(Service service) {
        super(service);
    }

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        JsonArray jsonMediums = new JsonArray();

        for (int i = 1; i <= 5; i++) {
            Medium medium = (Medium) request.getAttribute("medium" + i);
            if (medium != null) {
                JsonObject jsonMedium = new JsonObject();
                jsonMedium.addProperty("denomination", medium.getDenomination());
                jsonMedium.addProperty("presentation", medium.getPresentation());
                jsonMedium.addProperty("genre", medium.getGenre() ? "femme" : "homme");

                if (medium instanceof Spirit) {
                    jsonMedium.addProperty("type", "Spirit");
                } else if (medium instanceof Cartomancien) {
                    jsonMedium.addProperty("type", "Cartomancien");
                } else if (medium instanceof Astrologue) {
                    jsonMedium.addProperty("type", "Astrologue");
                }              
                jsonMediums.add(jsonMedium);
            }
        }

        container.add("mediums", jsonMediums);
        response.setContentType("predictif/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(container.toString());
        out.close();
    }
}
