package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.ProductionInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final ProductionInterface productionInterface;


    public MovieController(MovieService movieService, ProductionInterface productionInterface) {
        this.movieService = movieService;
        this.productionInterface = productionInterface;
    }

    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error, Model model){
        List<Movie> movies = movieService.listAll();
        model.addAttribute("movies",movies);
        return "index";
    }
    @PostMapping("movies/add")
    public String saveMovie(@RequestParam String name,
                            @RequestParam String summary,
                            @RequestParam Double rating ,
                            @RequestParam Long id){
        movieService.save(name,summary,rating,id);
        return "redirect:/movies";
    }

    @PostMapping("movies/edit/{movieId}")
    public String editMovie(@PathVariable Long movieId,
                            @RequestParam String name,
                            @RequestParam String summary,
                            @RequestParam Double rating ,
                            @RequestParam Long id) {
        Movie movie = movieService.movieById(movieId);
        movie.title = name;
        movie.summary = summary;
        movie.rating = rating;
        movie.production = productionInterface.findById(id);

        movieService.edit(movieId, name, summary, rating, id);
        return "redirect:/movies";
    }

        @PostMapping("movies/delete/{movieId}")
        public String deleteMovie(@PathVariable Long movieId){
            movieService.delete(movieId);
            return "redirect:/movies";
        }

}


