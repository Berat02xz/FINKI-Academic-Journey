package mk.finki.ukim.museumapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MuseumAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuseumAppApplication.class, args);

        //ONLY UNCCOMENT IF YOU WANNA ADD TO DB
        //PipeAndFilter.main(null);
    }
}
