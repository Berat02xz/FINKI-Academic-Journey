package mk.finki.ukim.museumapp;

import mk.finki.ukim.museumapp.pipeandfilter.PipeAndFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MuseumAppApplication {

    private static PipeAndFilter museumProcessingService = new PipeAndFilter();

    public MuseumAppApplication(PipeAndFilter museumProcessingService) {
        MuseumAppApplication.museumProcessingService = museumProcessingService;
    }

    public static void main(String[] args) {
        PipeAndFilter museumProcessingService1 = museumProcessingService;
        SpringApplication.run(MuseumAppApplication.class, args);
    }

}
