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
import service.Service;

/**
 *
 * @author sperret
 */
public class ListStringSerialisation extends Serialisation {

    public ListStringSerialisation(Service service) {
        super(service);
    }

    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("[TEST] Execution de  : début");
        System.out.println("\u001b[32m" + "[TEST] Execution de  : réussie");
        System.out.println("\u001b[31m" + "[TEST] Execution de  : échouée");
        System.out.println("[TEST] Execution de  : fin");
        response.setContentType("predictif/json;charset=UTF-8");
        List<String> liste = (List<String>) request.getAttribute("liste");
        JsonObject container = new JsonObject();
        JsonArray jsonListe = new JsonArray();
        
        if (liste != null) {
            for (String element : liste) {
                jsonListe.add(element);
            }
        }
        container.add("liste", jsonListe);

        PrintWriter out = response.getWriter();
        out.println(container.toString());
        out.close();
    }
}
