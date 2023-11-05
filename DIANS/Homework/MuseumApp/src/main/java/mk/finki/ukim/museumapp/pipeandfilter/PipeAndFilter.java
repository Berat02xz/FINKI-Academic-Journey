package mk.finki.ukim.museumapp.pipeandfilter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


interface Filter {
    void process(Object data);
}

class JsonFileReaderFilter implements Filter {
    private Filter nextFilter;

    public JsonFileReaderFilter(Filter nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public void process(Object data) {
        try {
            File jsonFile = new File("C:\\Users\\berat\\Documents\\GitHub\\FINKI\\DIANS\\Homework\\MuseumApp\\src\\main\\java\\mk\\finki\\ukim\\museumapp\\data\\export.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            if (nextFilter != null) {
                nextFilter.process(rootNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MuseumExtractorFilter implements Filter {
    private Filter nextFilter;
    private List<Museum> museums = new ArrayList<>();

    public MuseumExtractorFilter(Filter nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public void process(Object data) {
        JsonNode jsonData = (JsonNode) data;
        JsonNode elements = jsonData.get("elements");

        for (JsonNode element : elements) {
            if (element.has("type")) {
                String elementType = element.get("type").asText();
                if ("node".equals(elementType) || "way".equals(elementType)) {
                    museums.add(createMuseum(element));
                }
            }
        }

        if (nextFilter != null) {
            nextFilter.process(museums);
        }
    }

    public List<Museum> getMuseums() {
        return museums;
    }

    private Museum createMuseum(JsonNode node) {
        JsonNode tags = node.get("tags");

        return new Museum(
                tags.has("name") ? tags.get("name").asText() : null,
                node.has("lat") ? node.get("lat").asDouble() : 0.0,
                node.has("lon") ? node.get("lon").asDouble() : 0.0,
                tags.has("addr:street") ? tags.get("addr:street").asText() : null,
                tags.has("email") ? tags.get("email").asText() : null,
                tags.has("internet_access") ? tags.get("internet_access").asText() : null,
                tags.has("wikidata") ? tags.get("wikidata").asText() : null,
                tags.has("opening_hours") ? tags.get("opening_hours").asText() : null,
                tags.has("contact:phone") ? tags.get("contact:phone").asText() : null,
                tags.has("fee") ? tags.get("fee").asText() : null,
                tags.has("charge") ? tags.get("charge").asText() : null,
                tags.has("website") ? tags.get("website").asText() : null
        );
    }
}

class Museum {
    @Override
    public String toString() {
        return "Museum{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", street='" + street + '\'' +
                ", email='" + email + '\'' +
                ", internetAccess='" + internetAccess + '\'' +
                ", wikidata='" + wikidata + '\'' +
                ", openingHours='" + openingHours + '\'' +
                ", phone='" + phone + '\'' +
                ", fee='" + fee + '\'' +
                ", charge='" + charge + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    private String name;
    private double latitude;
    private double longitude;
    private String street;
    private String email;
    private String internetAccess;
    private String wikidata;
    private String openingHours;
    private String phone;
    private String fee;
    private String charge;
    private String website;

    public Museum(
            String name,
            double latitude,
            double longitude,
            String street,
            String email,
            String internetAccess,
            String wikidata,
            String openingHours,
            String phone,
            String fee,
            String charge,
            String website
    ) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.street = street;
        this.email = email;
        this.internetAccess = internetAccess;
        this.wikidata = wikidata;
        this.openingHours = openingHours;
        this.phone = phone;
        this.fee = fee;
        this.charge = charge;
        this.website = website;
    }

    // Getters and setters go here
}

public class PipeAndFilter {
    public static void main(String[] args) {
        Filter museumExtractorFilter = new MuseumExtractorFilter(null);
        Filter jsonFileReaderFilter = new JsonFileReaderFilter(museumExtractorFilter);

        jsonFileReaderFilter.process(null);

        List<Museum> museums = ((MuseumExtractorFilter) museumExtractorFilter).getMuseums();

        for (Museum museum : museums) {
            System.out.println(museum);
        }
    }
}