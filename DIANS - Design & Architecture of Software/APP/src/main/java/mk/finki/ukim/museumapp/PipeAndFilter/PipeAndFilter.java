package mk.finki.ukim.museumapp.PipeAndFilter;

import mk.finki.ukim.museumapp.PipeAndFilter.Service.Filter;
import mk.finki.ukim.museumapp.PipeAndFilter.Service.JsonFileReaderFilter;
import mk.finki.ukim.museumapp.PipeAndFilter.Service.MuseumExtractorFilter;
import mk.finki.ukim.museumapp.PipeAndFilter.model.Museum;
import mk.finki.ukim.museumapp.Repository.MuseumJPA;
import org.springframework.stereotype.Component;

import java.util.List;


//MAIN CLASS
@Component
public class PipeAndFilter {

    public static MuseumJPA museumJPA = null;

    public PipeAndFilter(MuseumJPA museumJPA) {
        this.museumJPA = museumJPA;
    }


    public static void main(String[] args) {
        Filter museumExtractorFilter = new MuseumExtractorFilter(null);
        Filter jsonFileReaderFilter = new JsonFileReaderFilter(museumExtractorFilter);

        jsonFileReaderFilter.process(null);

        List<Museum> museums = MuseumExtractorFilter.getMuseums();

        System.out.println("-------------------Museum Data extracted from the JSON document:");
        for (Museum museum : museums) {

            //ONLY UNCOMMENT IF YOU WANNA ADD TO DATABASE
            //museumJPA.save(museum);

            System.out.println(museum);
        }
    }
}