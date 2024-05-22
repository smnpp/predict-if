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
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Astrologue;
import modele.Cartomancien;
import modele.Consultation;
import modele.Spirit;
import service.Service;

/**
 *
 * @author sperret
 */
public class ListerCommentairesSerialisation extends Serialisation {

    public ListerCommentairesSerialisation(Service service) {
        super(service);
    }

    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("predictif/json;charset=UTF-8");
        List<String> commentaires = (List<String>) request.getAttribute("commentaires");
        JsonObject container = new JsonObject();
        JsonArray jsonCommentaires = new JsonArray();
        
        if (commentaires != null) {
            for (String commentaire : commentaires) {
                jsonCommentaires.add(commentaire);
            }
        }
        container.add("consultations", jsonCommentaires);

        PrintWriter out = response.getWriter();
        out.println(container.toString());
        out.close();
    }
}
