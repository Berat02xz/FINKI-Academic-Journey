package mk.finki.ukim.museumapp.PipeAndFilter.Service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import mk.finki.ukim.museumapp.PipeAndFilter.model.Museum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

//-----MUSEUM DATA EXTRACTOR FROM JSON FILE
public class MuseumExtractorFilter implements Filter{
    private final Filter nextFilter;
    @Autowired
    public MuseumExtractorFilter(Filter nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Getter
    private static List<Museum> museums = new ArrayList<>();



    public static List<Museum> searchmuseums(String input) {
        List<Museum> museums = getMuseums();
        List<Museum> filteredMuseums = new ArrayList<>();
        for (Museum museum : museums) {
            if (museum.getName().toLowerCase().contains(input.toLowerCase())) {
                filteredMuseums.add(museum);
            }
        }
        return filteredMuseums;
    }

    public static List<Museum> getOpenNow() {
        List<Museum> museums = getMuseums();
        List<Museum> filteredMuseums = new ArrayList<>();
        for (Museum museum : museums) {
            if (museum.getOpeningHours()!="Unknown"){
                filteredMuseums.add(museum);
            }
        }
        return filteredMuseums;
    }

    public static List<Museum> getFreeEntry() {
        List<Museum> museums = getMuseums();
        List<Museum> filteredMuseums = new ArrayList<>();
        for (Museum museum : museums) {
            if (museum.getFee().equals("no")){
                filteredMuseums.add(museum);
            }
        }
        return filteredMuseums;
    }

    public static List<Museum> getInternetAccess() {
        List<Museum> museums = getMuseums();
        List<Museum> filteredMuseums = new ArrayList<>();
        for (Museum museum : museums) {
            if (museum.getInternetAccess().equals("yes")){
                filteredMuseums.add(museum);
            }
        }
        return filteredMuseums;
    }

    public static List<Museum> getSkopje() {
        List<Museum> museums = getMuseums();
        List<Museum> filteredMuseums = new ArrayList<>();
        for (Museum museum : museums) {
            if (museum.getStreet().contains("Skopje")){
                filteredMuseums.add(museum);
            }
        }
        return filteredMuseums;
    }

    @Override
    public void process(Object data) {
        JsonNode jsonData = (JsonNode) data;
        JsonNode elements = jsonData.get("elements");


        for (JsonNode element : elements) {
            if (element.has("tags")) {
                JsonNode tags = element.get("tags");
                if (tags.has("tourism") && tags.get("tourism").asText().equals("museum")) {
                    museums.add(createMuseum(element));
                }
            }
        }

        if (nextFilter != null) {
            nextFilter.process(museums);
        }
    }

    private Museum createMuseum(JsonNode node) {
        JsonNode tags = node.get("tags");

        return new Museum(
                tags.has("name") ? tags.get("name").asText() : "Unknown",
                node.has("lat") ? node.get("lat").asDouble() : 0.0,
                node.has("lon") ? node.get("lon").asDouble() : 0.0,
                tags.has("addr:street") ? tags.get("addr:street").asText() : "street",
                tags.has("email") ? tags.get("email").asText() : "Unknown",
                tags.has("internet_access") ? tags.get("internet_access").asText() : "yes",
                tags.has("wikidata") ? tags.get("wikidata").asText() : "Unknown",
                tags.has("opening_hours") ? tags.get("opening_hours").asText() : "Unknown",
                tags.has("contact:phone") ? tags.get("contact:phone").asText() : "Unknown",
                tags.has("fee") ? tags.get("fee").asText() : "Unknown",
                tags.has("charge") ? tags.get("charge").asText() : "Unknown",
                tags.has("website") ? tags.get("website").asText() : "Unknown"
        );
    }

}
