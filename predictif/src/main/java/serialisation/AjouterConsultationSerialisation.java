package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Consultation;
import service.Service;

public class AjouterConsultationSerialisation extends Serialisation {
    
    public AjouterConsultationSerialisation(Service service) {
        super(service);
    }

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("[TEST] Execution de AjouterConsultationSerialisation");
        response.setContentType("predictif/json;charset=UTF-8");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        
        Consultation consultation = (Consultation) request.getAttribute("consultation");
        JsonObject container = new JsonObject();

        if (consultation != null) {
            container.addProperty("consultation", true);
        } else {
            container.addProperty("consultation", false);
        }
        
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(container));
        out.close();
    }
}
