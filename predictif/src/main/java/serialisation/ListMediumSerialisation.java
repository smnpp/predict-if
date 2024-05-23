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

public class ListMediumSerialisation extends Serialisation {
    
    public ListMediumSerialisation(Service service) {
        super(service);
    }

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Medium> mediums = (List<Medium>) request.getAttribute("mediums");
        JsonObject container = new JsonObject();
        JsonArray jsonMediums = new JsonArray();

        if (mediums != null) {
            for (Medium medium : mediums) {
                JsonObject jsonMedium = new JsonObject();
                jsonMedium.addProperty("denomination", medium.getDenomination());
                jsonMedium.addProperty("presentation", medium.getPresentation());
                jsonMedium.addProperty("genre", medium.getGenre() == false ? "homme" : "femme"); // Assuming genre is stored as integer

                // Check type and serialize additional properties
                if (medium instanceof Spirit) {
                    jsonMedium.addProperty("type", "spirit");
                    Spirit spirit = (Spirit) medium;
                    List<String> supports = spirit.getSupport();
                    JsonArray jsonSupports = new JsonArray(); // Create a JSON array for supports
                    for (String support : supports) {
                        jsonSupports.add(support); // Add each support to the array
                    }
                    jsonMedium.add("supports", jsonSupports);
                    //jsonMedium.addProperty("support", (Number) spirite.getSupport());
                } else if (medium instanceof Cartomancien) {
                    jsonMedium.addProperty("type", "cartomancien");
                    // Cartomancien might not have additional properties specific to serialize
                } else if (medium instanceof Astrologue) {
                    jsonMedium.addProperty("type", "astrologue");
                    Astrologue astrologue = (Astrologue) medium;
                    jsonMedium.addProperty("formation", astrologue.getFormation());
                    jsonMedium.addProperty("promotion", astrologue.getPromotion());
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
