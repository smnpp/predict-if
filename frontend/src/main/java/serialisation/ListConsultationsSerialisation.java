package serialisation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Astrologue;
import modele.Cartomancien;
import modele.Consultation;
import modele.Spirit;
import service.Service;

public class ListConsultationsSerialisation extends Serialisation {

    public ListConsultationsSerialisation(Service service) {
        super(service);
    }

    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("[TEST] Execution de ListConsultationsSerialisation : début");
        
        response.setContentType("predictif/json;charset=UTF-8");
        List<Consultation> consultations = (List<Consultation>) request.getAttribute("consultations");
        JsonObject container = new JsonObject();
        JsonArray jsonConsultations = new JsonArray();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            if (consultations != null) {
                for (Consultation consultation : consultations) {
                    JsonObject jsonConsultation = new JsonObject();
                    jsonConsultation.addProperty("date", dateFormat.format(consultation.getDate()));
                    jsonConsultation.addProperty("denominationMedium", consultation.getMedium().getDenomination());
                    jsonConsultation.addProperty("genre", consultation.getMedium().getGenre() == false ? "homme" : "femme");
                    if (consultation.getMedium() instanceof Spirit) {
                        jsonConsultation.addProperty("type", "spirit");
                    } else if (consultation.getMedium() instanceof Cartomancien) {
                        jsonConsultation.addProperty("type", "cartomancien");
                    } else if (consultation.getMedium() instanceof Astrologue) {
                        jsonConsultation.addProperty("type", "astrologue");
                    }
                    else {
                        jsonConsultation.addProperty("type", "inconnue");
                    }
                    jsonConsultations.add(jsonConsultation);
                }
                System.out.println("\u001b[32m" + "[TEST] Execution de ListConsultationsSerialisation : réussie");
            }
            else {
                System.out.println("\u001b[31m" + "[TEST] Execution de ListConsultationsSerialisation : null");
            }
            container.add("consultations", jsonConsultations);

            try (PrintWriter out = response.getWriter()) {
                out.println(container.toString());
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la sérialisation des consultations");
        }

        PrintWriter out = response.getWriter();
        out.println(container.toString());
        out.close();
        System.out.println("[TEST] Execution de ListConsultationsSerialisation : fin");
    }
}
