package mk.finki.ukim.museumapp.PipeAndFilter.Service;

import mk.finki.ukim.museumapp.PipeAndFilter.model.Museum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MuseumService {
    List<Museum> getMuseums();

    List<Museum> searchmuseums(String search);

    List<Museum> getOpenNow();

    List<Museum> getFreeEntry();

    List<Museum> getInternetAccess();

    List<Museum> getSkopje();

    Museum createMuseum(String name, double latitude, double longitude, String street, String email, String internetAccess, String wikidata, String openingHours, String phone, String fee, String charge, String website);

    void deleteMuseum(int id);

    Museum getMuseum(int id);
}
