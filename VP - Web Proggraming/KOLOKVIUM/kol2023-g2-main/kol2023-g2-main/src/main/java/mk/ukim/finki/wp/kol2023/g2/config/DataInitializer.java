package mk.ukim.finki.wp.kol2023.g2.config;

import mk.ukim.finki.wp.kol2023.g2.model.Genre;
import mk.ukim.finki.wp.kol2023.g2.service.DirectorService;
import mk.ukim.finki.wp.kol2023.g2.service.MovieService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final DirectorService directorService;

    private final MovieService movieService;

    public DataInitializer(DirectorService directorService, MovieService movieService) {
        this.directorService = directorService;
        this.movieService = movieService;
    }

    private Genre randomizePosition(int i) {
        if(i % 3 == 0) return Genre.Action;
        else if(i % 3 == 1) return Genre.Comedy;
        return Genre.Horror;
    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 6; i++) {
            this.directorService.create("Director: " + i);
        }

        for (int i = 1; i < 11; i++) {
            this.movieService.create("Movie: " + i, "Desc: " + i , 20.9 * i, this.randomizePosition(i), this.directorService.listAll().get((i-1)%5).getId());
        }
    }
}
