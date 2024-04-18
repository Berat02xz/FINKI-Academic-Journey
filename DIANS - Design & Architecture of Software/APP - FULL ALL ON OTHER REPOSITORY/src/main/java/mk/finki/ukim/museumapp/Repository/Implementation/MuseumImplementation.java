package mk.finki.ukim.museumapp.Repository.Implementation;

import mk.finki.ukim.museumapp.PipeAndFilter.Service.MuseumService;
import mk.finki.ukim.museumapp.PipeAndFilter.model.Museum;
import mk.finki.ukim.museumapp.Repository.MuseumJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuseumImplementation implements MuseumService {
 private final MuseumJPA museumJPA;

    @Autowired
    public MuseumImplementation(MuseumJPA museumJPA) {
        this.museumJPA = museumJPA;
    }


    @Override
    public List<Museum> getMuseums() {
        return museumJPA.findAllBy();
    }

    @Override
    public List<Museum> searchmuseums(String search) {
        return museumJPA.findMuseumsByNameContainingIgnoreCase(search);
    }

    @Override
    public List<Museum> getOpenNow() {
        return museumJPA.findMuseumsByOpeningHoursIsNot("Unknown");
    }

    @Override
    public List<Museum> getFreeEntry() {
        return museumJPA.findMuseumsByFeeNotContaining("Unknown");
    }

    @Override
    public List<Museum> getInternetAccess() {
        return museumJPA.findMuseumsByInternetAccessContaining("yes");
    }

    @Override
    public List<Museum> getSkopje() {
        return museumJPA.findMuseumsByStreetContains("Skopje");
    }

    @Override
    public Museum createMuseum(String name, double latitude, double longitude, String street, String email, String internetAccess, String wikidata, String openingHours, String phone, String fee, String charge, String website) {
        return museumJPA.save(new Museum(name,latitude,longitude,street,email,internetAccess,wikidata,openingHours,phone,fee,charge,website));
    }

    @Override
    public void deleteMuseum(int id) {
        museumJPA.deleteById(id);
    }

    @Override
    public Museum getMuseum(int id) {
        return museumJPA.findMuseumById(id);
    }
}
