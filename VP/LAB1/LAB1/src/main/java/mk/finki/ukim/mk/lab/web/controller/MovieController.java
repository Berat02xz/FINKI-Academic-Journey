package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
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
    @PostMapping("/add")
    public String saveMovie(@RequestParam String movieTitle,
                            @RequestParam String summary,
                            @RequestParam double rating,
                            @RequestParam Long productions) {
        this.movieService.save(movieTitle, summary, rating, productions);
        return "redirect:/movies";
    }

    @PostMapping("/movies/edit/{movieId}")
    public String editMovie(@PathVariable Long movieId,
                            @RequestParam String movieTitle,
                            @RequestParam String summary,
                            @RequestParam double rating,
                            @RequestParam Long productionId) {
        Movie movie = this.movieService.movieById(movieId);
        movie.setTitle(movieTitle);
        movie.setSummary(summary);
        movie.setRating(rating);
        movie.setProduction(productionInterface.findById(productionId));
        return "redirect:/movies";
    }

        @GetMapping ("/delete/{id}")
        public String deleteMovie(@PathVariable Long id){
            movieService.delete(id);
            return "redirect:/movies";
        }

        @GetMapping("/edit-form/{id}")
        public String editMoviePage(@PathVariable Long id, Model model){
            Movie movie = movieService.movieById(id);
            model.addAttribute("movie",movie);
            List<Production> productions = productionInterface.findAll();
            model.addAttribute("productions",productions);
            return "add-movie";
        }

        @GetMapping("/add-form")
        public String addMoviePage(Model model){
            List<Production> productions = productionInterface.findAll();
            model.addAttribute("productions",productions);
            return "add-movie";
        }

}


